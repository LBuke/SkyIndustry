package com.destinymc.util;

import com.destinymc.packet.out.WrapperPlayServerWindowItems;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InventoryUtil {

    /**
     * @return This method returns a Pair of Integers.
     * The first integer is the found slot in the inventory.
     * The second integer is the amount of items that can be added to said slot.
     */
    public static Pair<Integer, Integer> getNextSingularSlot(Inventory inventory, ItemStack itemStack, int... whitelist) {
        int next = -1, amount = itemStack.getAmount();
        for (int i = 0; i < inventory.getSize(); i++) {
            if (whitelist != null && whitelist.length > 0 && !ArrayUtil.containsElement(i, whitelist))
                continue;

            ItemStack item = inventory.getContents()[i];
            if (next == -1 && (item == null || item.getType() == Material.AIR)) {
                next = i;
                continue;
            }

            if (item != null && item.getType() == itemStack.getType()) {
                if (item.getAmount() < item.getMaxStackSize() && item.isSimilar(itemStack)) {
                    next = i;

                    int maxTake = item.getMaxStackSize() - item.getAmount();
                    if (maxTake <= 0)
                        continue;

                    amount = itemStack.getAmount() >= maxTake ? maxTake : itemStack.getAmount();
                    break;
                }
            }
        }

        return new Pair<>(next, amount);
    }

    public static void updateInventory(Inventory inventory) {
        for (HumanEntity entity : inventory.getViewers()) {
            int windowId = ((CraftHumanEntity) entity).getHandle().activeContainer.windowId;

            WrapperPlayServerWindowItems windowItems = new WrapperPlayServerWindowItems();
            windowItems.setWindowId(windowId);
            windowItems.setSlotData(Arrays.asList(inventory.getContents()));


        }
    }

    public static void reduceAmount(Inventory inventory, int index, int amount) {
        ItemStack itemStack = inventory.getItem(index);
        if (itemStack.getAmount() - amount > 1)
            itemStack.setAmount(itemStack.getAmount() - amount);
        else
            inventory.setItem(index, new ItemStack(Material.AIR));
    }
}
