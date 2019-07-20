package com.destinymc.inventory.button;

import org.bukkit.inventory.ItemStack;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public class MenuItem {

    private final int index;
    private final ItemStack itemStack;

    private final boolean allowPickup;

    /**
     * Construct a new MenuItem
     *
     * @param index       menu slot index
     * @param itemStack   menu itemstack
     * @param allowPickup movable status
     */
    public MenuItem(int index, ItemStack itemStack, boolean allowPickup) {
        this.index = index;
        this.itemStack = itemStack;
        this.allowPickup = allowPickup;
    }

    /**
     * Construct a new MenuItem
     *
     * @param index       menu slot index
     * @param itemStack   menu itemstack
     */
    public MenuItem(int index, ItemStack itemStack) {
        this.index = index;
        this.itemStack = itemStack;
        this.allowPickup = false;
    }

    /**
     * Get the index of the set item.
     *
     * @return index
     */
    public final int getIndex() {
        return index;
    }

    /**
     * Get the ItemStack of the set item.
     *
     * @return itemstack
     */
    public final ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * If true, the item can be moved.
     * If false, the item is stationary.
     *
     * @return movable status
     */
    public final boolean isAllowingPickup() {
        return allowPickup;
    }
}
