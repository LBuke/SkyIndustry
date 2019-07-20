package com.destinymc.ic2.machine.handler;

import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.MachineManager;
import com.destinymc.ic2.machine.event.MachineInnerInitialiseEvent;
import com.destinymc.util.InventoryUtil;
import com.destinymc.util.Log;
import com.destinymc.util.Pair;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineInventoryHandler implements Listener {

    private final MachineManager machineManager;

    public MachineInventoryHandler(MachineManager machineManager) {
        this.machineManager = machineManager;
    }

    @EventHandler
    protected final void onMachineInnerInit(MachineInnerInitialiseEvent event) {
        if (event.getMachine() == null)
            return;

        machineManager.setMachineDefaultItems(event.getMachine(), null);
    }

    @EventHandler(ignoreCancelled = true)
    protected final void onBlockInventoryOpen(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (event.getClickedBlock() == null)
            return;

        if (event.getClickedBlock().getType() != Material.BLACK_SHULKER_BOX)
            return;

        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (itemInHand != null && itemInHand.getType() == Material.DIAMOND_AXE && itemInHand.getItemMeta().isUnbreakable()) {
            if (event.getPlayer().isSneaking())
                return;
        }

        ShulkerBox shulkerBox = (ShulkerBox) event.getClickedBlock().getState();
        Machine machine = machineManager.getMachineFromBlock(shulkerBox);
        if (machine == null)
            return;

        machine.setBlockState(shulkerBox);

//        machineManager.setMachineDefaultItems(machine, shulkerBox.getInventory());
        machineManager.updateInnerInventoryStorage(machine, shulkerBox.getInventory());

        event.setCancelled(true);

        event.getPlayer().openInventory(machineManager.getUpdatedInventory(machine, shulkerBox.getInventory()));
    }

    @EventHandler
    protected final void onInventoryClick(InventoryClickEvent event) {

        // First of all, if the clicked inventory is null,
        // Stop all other checks.
        if (event.getClickedInventory() == null)
            return;

        // Don't run Machine checks if nothing is performed.
        if (event.getAction() == InventoryAction.NOTHING)
            return;

        // Don't run Machine checks if inventory-type isn't a chest.
        if (event.getInventory().getType() != InventoryType.CHEST)
            return;

        // Dont run Machine checks if the inventory holder is null.
        if (event.getInventory().getHolder() == null)
            return;

        // Make sure the inventory holder is a Shulker-Box.
        if (!(event.getInventory().getHolder() instanceof ShulkerBox))
            return;

        ShulkerBox shulkerBox = (ShulkerBox) event.getInventory().getHolder();
        Machine machine = machineManager.getMachineFromBlock(shulkerBox);
        if (machine == null)
            return;

        Inventory topInventory = event.getView().getTopInventory();
        boolean clickedTopInventory = event.getClickedInventory().equals(topInventory);

//        Log.debug("[>---------------------------<]");
//        Log.debug("Action: " + event.getAction().name());
//        Log.debug("Slot: " + event.getSlot());
//        Log.debug("Raw Slot: " + event.getRawSlot());
//        Log.debug("Click Type: " + event.getClick().name());
//        Log.debug("Clicked Inv: " + (clickedTopInventory ? "TOP" : "BOTTOM"));
//        Log.debug("Clicked Item: " + (event.getCurrentItem() == null ? "." : event.getCurrentItem().getType()));
//        Log.debug("Cursor Item: " + (event.getCursor() == null ? "." : event.getCursor().getType()));
//        Log.debug("Slot Type: " + event.getSlotType().name());
//        Log.debug("Hotbar Button: " + event.getHotbarButton());
//        Log.debug("[>---------------------------<]");
//        Log.debug(" ");

        // If the clicked inventory is the top container
        // and the slot is blocked, cancel the event.
        if (clickedTopInventory && machine.isBlockedSlot(event.getRawSlot())) {
            event.setCancelled(true);
            return;
        }

        // If SHIFT is held down during this event.
        if (!clickedTopInventory && event.isShiftClick()) {

            // todo
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                int[] acceptedSlots = machine.getAcceptSlots(event.getCurrentItem());
                if (acceptedSlots == null) {
                    event.setCancelled(true);
                    ((Player) event.getWhoClicked()).updateInventory();
                    return;
                }

                Pair<Integer, Integer> pair = InventoryUtil.getNextSingularSlot(topInventory, event.getCurrentItem(), acceptedSlots);
                if (pair.getKey() == -1) {
                    Log.debug("Unable to locate a slot");
                    event.setCancelled(true);
                    ((Player) event.getWhoClicked()).updateInventory();
                    return;
                }

                Log.debug("Next suitable slot : " + pair.getKey());
                Log.debug("Amount to take : " + pair.getValue());

                ItemStack clickedItem = event.getCurrentItem().clone();
                if (clickedItem.getAmount() - pair.getValue() > 1) {
                    event.getCurrentItem().setAmount(clickedItem.getAmount() - pair.getValue());
                } else {
                    event.setCurrentItem(new ItemStack(Material.AIR));
                }

                ItemStack existingItem = topInventory.getItem(pair.getKey());
                if (existingItem != null && existingItem.getType() != Material.AIR) {
                    existingItem.setAmount(existingItem.getAmount() + pair.getValue());
                } else {
                    topInventory.setItem(pair.getKey(), clickedItem);
                }

                event.setCancelled(true);
                ((Player) event.getWhoClicked()).updateInventory();
            }
        }

        machineManager.updateInnerInventoryStorage(machine, shulkerBox.getInventory());
    }

    @EventHandler
    protected final void test(InventoryDragEvent event) {
        Log.debug("InventoryDragEvent");
    }

    @EventHandler
    protected final void test(InventoryMoveItemEvent event) {
        Log.debug("InventoryMoveItemEvent");
    }

    @EventHandler
    protected final void test(InventoryPickupItemEvent event) {
        Log.debug("InventoryPickupItemEvent");
    }

    @EventHandler
    protected final void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() == null)
            return;

        if (event.getInventory().getType() != InventoryType.CHEST)
            return;

        if (event.getInventory().getHolder() == null)
            return;

        if (!(event.getInventory().getHolder() instanceof ShulkerBox))
            return;

        ShulkerBox shulkerBox = (ShulkerBox) event.getInventory().getHolder();
        Machine machine = machineManager.getMachineFromBlock(shulkerBox);
        if (machine == null)
            return;

        machineManager.updateInnerInventoryStorage(machine, shulkerBox.getInventory());
    }
}
