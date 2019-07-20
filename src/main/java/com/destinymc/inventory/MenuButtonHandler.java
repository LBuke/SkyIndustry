package com.destinymc.inventory;

import com.destinymc.inventory.button.MenuItem;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface MenuButtonHandler {

    /**
     * Add an item to the inventory.
     *
     * @param menuItem Item
     */
    void addItem(MenuItem menuItem);

    /**
     * Delete an item from the inventory.
     *
     * @param index inventory index
     */
    void deleteItem(int index);

    /**
     * Check if the inventory contains a specific item.
     *
     * @param itemStack Item to search for
     * @return true if item is found
     */
    boolean containsItem(ItemStack itemStack);

    /**
     * Check if the inventory slot is in use.
     *
     * @param index slot index
     * @return found status
     */
    boolean containsItem(int index);

    /**
     * Get the MenuItem from an input ItemStack.
     *
     * @param itemStack Item to search for
     * @return MenuItem version of the found ItemStack
     */
    MenuItem getMenuItem(ItemStack itemStack);

    /**
     * Get the MenuItem from an index input.
     *
     * @param index slot to search
     * @return MenuItem version of the found ItemStack
     */
    MenuItem getMenuItem(int index);


}
