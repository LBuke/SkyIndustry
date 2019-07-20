package com.destinymc.inventory.button;

import org.bukkit.inventory.ItemStack;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public class ClickableItem extends MenuItem {

    private MenuClickAction clickAction;

    /**
     * Construct a new MenuItem
     *
     * @param index     menu slot index
     * @param itemStack menu itemstack
     * @param clickAction action to run once clicked
     */
    public ClickableItem(int index, ItemStack itemStack, MenuClickAction clickAction) {
        super(index, itemStack, false);
        this.clickAction = clickAction;
    }

    /**
     * Construct a new MenuItem
     *
     * @param index     menu slot index
     * @param itemStack menu itemstack
     * @param allowPickup pickable item
     * @param clickAction action to run once clicked
     */
    public ClickableItem(int index, ItemStack itemStack, boolean allowPickup, MenuClickAction clickAction) {
        super(index, itemStack, allowPickup);
        this.clickAction = clickAction;
    }

    /**
     * Construct a new MenuItem
     *
     * @param index     menu slot index
     * @param itemStack menu itemstack
     * @param clickAction action to run once clicked
     * @param allowPickup pickable item
     */
    public ClickableItem(int index, ItemStack itemStack, MenuClickAction clickAction, boolean allowPickup) {
        super(index, itemStack, allowPickup);
        this.clickAction = clickAction;
    }

    public MenuClickAction getClickAction() {
        return clickAction;
    }
}
