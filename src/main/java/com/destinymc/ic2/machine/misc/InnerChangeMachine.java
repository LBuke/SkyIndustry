package com.destinymc.ic2.machine.misc;

import com.destinymc.ic2.machine.Machine;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface InnerChangeMachine {

    ItemStack[][] getObjects();

    int[] getObjectSlots();

    default ItemStack[] getObjectsFromSlot(int slot) {
        for (int i = 0; i < this.getObjectSlots().length; i++) {
            if (this.getObjectSlots()[i] == slot) {
                return this.getObjects()[i];
            }
        }

        return new ItemStack[] {};
    }

    default boolean setObjectItem(Machine machine, int slot, int index) {
        if (machine == null) return false;

        Inventory inventory = machine.getInventory();
        if (inventory == null) return false;

        ItemStack item = inventory.getItem(slot);
        if (item != null && item.getType() == Material.AIR)
            return false;

        ItemStack newItem = this.getObjectsFromSlot(slot)[index].clone();
        if (newItem == null) return false;

        if (item == null) {
            inventory.setItem(slot, newItem);
            return true;
        }

        if (item.getDurability() == newItem.getDurability())
            return false;

        inventory.setItem(slot, newItem);
        return true;
    }
}
