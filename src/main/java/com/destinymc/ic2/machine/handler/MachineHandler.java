package com.destinymc.ic2.machine.handler;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.IC2ItemManager;
import com.destinymc.ic2.item.misc.CableBlock;
import com.destinymc.ic2.item.misc.CustomBlock;
import com.destinymc.ic2.item.misc.PlaceableItem;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.MachineManager;
import com.destinymc.ic2.machine.event.MachinePlaceEvent;
import com.destinymc.packet.out.WrapperPlayServerAnimation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineHandler implements Listener {

    private final MachineManager machineManager;
    private final IC2ItemManager itemManager;

    public MachineHandler(MachineManager machineManager, IC2ItemManager itemManager) {
        this.machineManager = machineManager;
        this.itemManager = itemManager;

//        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(ServerUtil.getPlugin(), PacketType.Play.Server.SET_SLOT) {
//            @Override
//            public void onPacketSending(PacketEvent event) {
//                WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot(event.getPacket());
//                ServerUtil.debug("WindowID : " + packet.getWindowId());
//                ServerUtil.debug("SLOT : " + packet.getSlot());
//                ServerUtil.debug("ITEM : " + packet.getSlotData().getType().name());
//            }
//        });
    }

    @EventHandler(ignoreCancelled = true)
    protected final void onCustomBlockPlace(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)
            return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null)
            return;

        if (event.getItem() == null || event.getItem().getType() != Material.DIAMOND_AXE)
            return;

        if (clickedBlock.getState() instanceof InventoryHolder && !event.getPlayer().isSneaking())
            return;

        IC2Item item = itemManager.getItem(event.getItem());
        if (item == null)
            return;

        BlockFace clickedFace = event.getBlockFace();
        Location location = clickedBlock.getRelative(clickedFace).getLocation();

        WrapperPlayServerAnimation animation = new WrapperPlayServerAnimation();
        animation.setEntityID(event.getPlayer().getEntityId());
        animation.setAnimation(0);

        if (item instanceof PlaceableItem) {
            boolean result = false;

            if (item instanceof CableBlock) {
                result = ((CableBlock) item).construct(event.getPlayer(), location, location);
            }
            else {
                result = ((PlaceableItem) item).construct(event.getPlayer(), location);
            }

            if (result) {
                animation.sendPacket(event.getPlayer());
                return;
            }
        }

//        if (item instanceof MachineBlockItem) {
//            boolean result = ((MachineBlockItem) item).construct(location);
//            animation.sendPacket(event.getPlayer());
//            return;
//        }
    }

    @EventHandler(ignoreCancelled = true)
    protected final void onMachinePlace(MachinePlaceEvent event) {
        event.setCancelled(!machineManager.addMachine(event.getLocation(), event.getMachine()));
    }

    @EventHandler(ignoreCancelled = true)
    protected final void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock() == null)
            return;

        if (event.getBlock().getType() != Material.BLACK_SHULKER_BOX)
            return;

        ShulkerBox shulkerBox = (ShulkerBox) event.getBlock().getState();
        Machine machine = machineManager.getMachineFromBlock(shulkerBox);
        if (machine == null)
            return;

        List<ItemStack> itemDropList = machineManager.getCombinedContents(machine, shulkerBox.getInventory());
        itemDropList.add(machine.getMachineItem().clone());

        Location dropLoc = machine.getLocation().clone();

        machineManager.destroyMachine(machine);

        for (ItemStack dropItem : itemDropList) {
            if (dropItem == null || dropItem.getType() == Material.AIR)
                continue;

            dropLoc.getWorld().dropItemNaturally(dropLoc.clone(), dropItem);
        }
    }
}
