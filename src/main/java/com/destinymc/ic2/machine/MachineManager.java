package com.destinymc.ic2.machine;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.IC2ItemManager;
import com.destinymc.ic2.item.misc.UnfunctionalItem;
import com.destinymc.ic2.machine.item.tile.BlankTile;
import com.destinymc.ic2.machine.handler.MachineHandler;
import com.destinymc.ic2.machine.handler.MachineInventoryHandler;
import com.destinymc.ic2.machine.misc.fueled.FueledMachine;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.task.MachineTask;
import com.destinymc.ic2.machine.visual.VisualMachine;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.util.C;
import com.destinymc.util.Log;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineManager {

    private final Cache<Long, Machine> machines;

    public MachineManager(JavaPlugin plugin, IC2ItemManager itemManager) {
        this.machines = CacheBuilder.newBuilder().expireAfterAccess(30L, TimeUnit.MINUTES).build();

        MachineHandler machineHandler = new MachineHandler(this, itemManager);
        MachineInventoryHandler inventoryHandler = new MachineInventoryHandler(this);

        Bukkit.getPluginManager().registerEvents(machineHandler, plugin);
        Bukkit.getPluginManager().registerEvents(inventoryHandler, plugin);

        Bukkit.getScheduler().runTaskTimer(plugin, new MachineTask(this, itemManager), 5, 20);
    }

    public ConcurrentMap<Long, Machine> getMachines() {
        return machines.asMap();
    }

    public boolean addMachine(Location location, Machine machine) {
        if (this.isIdentifierTaken(machine.getIdentifier())) {
            return false;
        }

        this.machines.put(machine.getIdentifier(), machine);
        return true;
    }

    private boolean isIdentifierTaken(long id) {
        return this.getMachineByIdentifier(id) != null;
    }

    public Machine getMachineByIdentifier(long id) {
        return this.getMachines().getOrDefault(id, null);
    }

    public Machine getMachineFromBlock(ShulkerBox shulkerBox) {
//        String title = shulkerBox.getInventory().getTitle();
//        if (!title.startsWith("m;"))
//            return null;
//
//        long identifier = Long.parseLong(title.split("m;")[1]);

        if (!shulkerBox.hasMetadata(MachineUtil.MACHINE_META))
            return null;

        long identifier = shulkerBox.getMetadata(MachineUtil.MACHINE_META).get(0).asLong();

        return this.getMachineByIdentifier(identifier);
    }

    public void setMachineDefaultItems(Machine machine, Inventory inventory) {

        for (int slot : machine.getBlockedSlots()) {
            machine.getInventory().setItem(slot, new BlankTile(C.WHITE + C.BOLD + machine.getName(), Arrays.asList(machine.getDescription())));
        }

        if (machine instanceof PoweredMachine) {
            PoweredMachine poweredMachine = (PoweredMachine) machine;

            machine.getInventory().setItem(
                    poweredMachine.getPowerData().getPowerSlots()[0],
                    poweredMachine.getPowerData().getTexturedItem()
                            .setLore(machine.getDescription())
                            .build()
            );
            poweredMachine.getPowerData().updateMachine(machine);
        }

        if (machine instanceof FueledMachine) {
            FueledMachine fueledMachine = (FueledMachine) machine;

            ItemStack found = machine.getInventory().getItem(fueledMachine.getFlameSlot());
            if (found == null || found.getType() == Material.AIR) {
                machine.getInventory().setItem(
                        fueledMachine.getFlameSlot(),
                        fueledMachine.getTexturedItem(machine.getVisualMachine())
                                .setLore(machine.getDescription())
                                .build()
                );
            }
        }

        machine.getInventory().setItem(0, new ItemFactory(Material.DIAMOND_HOE)
                .setName(C.WHITE + C.BOLD + machine.getName())
                .setLore(machine.getDescription())
                .setDurability(machine.getTile())
                .setUnbreaking(true)
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
                .build()
        );

        if (inventory != null) {
            updateInnerInventoryStorage(machine, inventory);
        }
    }

    public void destroyMachine(Machine machine) {
        VisualMachine visual = machine.getVisualMachine();
        if (visual != null) {
            if (visual.getArmorStand() != null)
                visual.getArmorStand().remove();
        }

        machine.getLocation().getBlock().setType(Material.AIR);
    }

    public Inventory getUpdatedInventory(Machine machine, Inventory inventory) {
        long start = System.currentTimeMillis();

        if (machine == null)
            return null;

        machine.getInventory().clear();

        ItemStack[] containerItems = new ItemStack[3];
        for (int i = 0; i < 3; i++) {
            containerItems[i] = inventory.getContents()[i];
        }

        int index = 0;
        for (ItemStack container : containerItems) {
            if (!(container.getItemMeta() instanceof BlockStateMeta))
                continue;

            BlockStateMeta blockStateMeta = (BlockStateMeta) container.getItemMeta();
            if (!(blockStateMeta.getBlockState() instanceof ShulkerBox))
                continue;

            ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();
            if (shulkerBox == null || shulkerBox.getInventory() == null)
                continue;

            ItemStack[] contents = shulkerBox.getInventory().getContents();
            for (int i = 0; i < contents.length; i++) {
                if ((i + index + 1) > machine.getInventory().getSize())
                    break;

                machine.getInventory().setItem(i + index, contents[i]);
            }

            index += 3 * 9;
        }

        Log.debug("MachineManager::getUpdatedInventory() - " + (System.currentTimeMillis() - start) + "ms");
        return machine.getInventory();
    }

    public void updateInnerInventoryStorage(Machine machine, Inventory inventory) {
        long start = System.currentTimeMillis();

        if (machine == null)
            return;

        ItemStack[] containerItems = new ItemStack[3];
        for (int i = 0; i < 3; i++) {
            containerItems[i] = inventory.getContents()[i];
        }

        int index = 0;
        for (int i = 0; i < containerItems.length; i++) {
            if (!(containerItems[i].getItemMeta() instanceof BlockStateMeta))
                continue;

            BlockStateMeta blockStateMeta = (BlockStateMeta) containerItems[i].getItemMeta();
            if (!(blockStateMeta.getBlockState() instanceof ShulkerBox))
                continue;

            ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();
            if (shulkerBox == null || shulkerBox.getInventory() == null)
                continue;

            ItemStack[] contents = new ItemStack[shulkerBox.getInventory().getSize()];
            for (int x = 0; x < 3; x++) {
                for (int z = 1; z < 10; z++) {
                    if (index + ((x * 9) + z) > machine.getInventory().getSize())
                        break;

                    contents[((x * 9) + z) - 1] = machine.getInventory().getContents()[index + ((x * 9) + z) - 1];
                }
            }

            shulkerBox.getInventory().setContents(contents);
            blockStateMeta.setBlockState(shulkerBox);
            containerItems[i].setItemMeta(blockStateMeta);
            inventory.setItem(i, containerItems[i]);

            index += 3 * 9;
        }

        Log.debug("MachineManager::updateInnerInventoryStorage() - " + (System.currentTimeMillis() - start) + "ms");
    }

    public List<ItemStack> getCombinedContents(Machine machine, Inventory inventory) {
        long start = System.currentTimeMillis();

        List<ItemStack> contents = Lists.newArrayList();

//        ItemStack[] containerItems = new ItemStack[3];
//        for (int i = 0; i < 3; i++) {
//            containerItems[i] = inventory.getContents()[i];
//        }
//
//        for (int i = 0; i < containerItems.length; i++) {
//            if (!(containerItems[i].getItemMeta() instanceof BlockStateMeta))
//                continue;
//
//            BlockStateMeta blockStateMeta = (BlockStateMeta) containerItems[i].getItemMeta();
//            if (!(blockStateMeta.getBlockState() instanceof ShulkerBox))
//                continue;
//
//            ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();
//            if (shulkerBox == null || shulkerBox.getInventory() == null)
//                continue;
//
//            System.out.println("Shulker box (" + i + ")");
//
//            ItemStack[] contents = shulkerBox.getInventory().getContents();
//            System.out.println("contents = {[items = " + contents.length + "], [size = " + shulkerBox.getInventory().getSize() + "]}");
//            for (int x = 0; x < contents.length; x++) {
//                if (contents[x] == null || contents[x].getType() == Material.AIR)
//                    continue;
//
//                System.out.println("x(" + x + ") - " + contents[i].toString());
//                items.add(contents[i]);
//            }
//        }
//
//        Log.debug("MachineManager::getCombinedContents() - " + (System.currentTimeMillis() - start) + "ms");
//        return items;

        Inventory inv = this.getUpdatedInventory(machine, inventory);
        if (inv == null) return contents;

        for (int x = 0; x < inv.getContents().length; x++) {
            ItemStack item = inv.getContents()[x];
            if (item == null || item.getType() == Material.AIR)
                continue;

            if (item.hasItemMeta() && item.getItemMeta().isUnbreakable()) {
                IC2Item ic2Item = IC2ItemManager.getItem(item);
                if (ic2Item == null)
                    continue;

                if (ic2Item instanceof UnfunctionalItem)
                    continue;
            }

            Log.debug("x(" + x + ") - " + inv.getContents()[x].toString());
            contents.add(inv.getContents()[x]);
        }

        return contents;
    }
}
