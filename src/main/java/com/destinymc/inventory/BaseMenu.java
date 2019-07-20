package com.destinymc.inventory;

import com.destinymc.inventory.button.MenuItem;
import com.destinymc.util.C;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class BaseMenu implements InventoryHolder, MenuButtonHandler {

    private final HashMap<Integer, MenuItem> mapItems;
//    private final List<MenuItem> items;

    private Inventory inventory;

    private final int rows;
    private final String title;
    private MenuFlag[] menuFlags;
    protected boolean selfHandle = false;

    /**
     * Construct a new Menu.
     *
     * @param rows rows within the container
     * @param title title of the container
     * @param menuFlags array of flags
     */
    public BaseMenu(int rows, String title, MenuFlag... menuFlags) {
        this(rows, title);
        this.menuFlags = menuFlags;
    }

    /**
     * Construct a new Menu.
     *
     * @param rows rows within the container
     * @param title title of the container
     */
    public BaseMenu(int rows, String title) {
        if (rows <= 0 || rows > 9) {
            throw new IndexOutOfBoundsException("Menu rows out of bounds, choose value between 1 - 9");
        }

        this.mapItems = Maps.newHashMap();

        this.rows = rows;
        this.title = C.translate(title);
        this.inventory = Bukkit.createInventory(this, rows * 9, title);

    }

    /**
     * Construct a new Menu.
     *
     * @param rows rows within the container
     * @param title title of the container
     * @param type container type
     * @param menuFlags array of flags
     */
    public BaseMenu(String title, int rows, InventoryType type, MenuFlag... menuFlags) {
        this.mapItems = Maps.newHashMap();

        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, type, title);
        this.menuFlags = menuFlags;
    }

    @Override
    public final Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Open the inventory to the specified player.
     *
     * @param player Specified Player
     */
    public void openInventory(Player player) {
        if (hasFlag(MenuFlag.RESET_CURSOR_ON_OPEN)) player.closeInventory();
        player.openInventory(this.inventory);
    }

    public final boolean hasFlag(MenuFlag menuFlag) {
        if (this.menuFlags == null || this.menuFlags.length == 0) return false;
        boolean result = false;
        for (int i = 0; i < this.menuFlags.length; i++) {
            if (this.menuFlags[i] == menuFlag) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void onClose(InventoryCloseEvent event) {
    }

    public void onInteract(BaseMenu menu) {
    }

    /**
     * Add an item to the inventory.
     *
     * @param menuItem Item
     */
    @Override
    public void addItem(MenuItem menuItem) {
        this.inventory.setItem(menuItem.getIndex(), menuItem.getItemStack());
        this.mapItems.put(menuItem.getIndex(), menuItem);
    }

    /**
     * Delete an item from the inventory.
     *
     * @param index inventory index
     */
    @Override
    public void deleteItem(int index) {
        this.inventory.setItem(index, null);
        this.mapItems.remove(index);
    }

    /**
     * Check if the inventory contains a specific item.
     *
     * @param itemStack Item to search for
     * @return true if item is found
     */
    @Override
    public boolean containsItem(ItemStack itemStack) {
//        return this.items.stream().anyMatch(item -> item.getItemStack().equals(itemStack));
        return this.mapItems.values().stream().anyMatch(item -> item.getItemStack().equals(itemStack));
    }

    /**
     * Check if the inventory slot is in use.
     *
     * @param index slot index
     * @return found status
     */
    @Override
    public boolean containsItem(int index) {
        return this.inventory.getItem(index) != null;
    }

    /**
     * Get the MenuItem from an input ItemStack.
     *
     * @param itemStack Item to search for
     * @return MenuItem version of the found ItemStack
     */
    @Override
    public MenuItem getMenuItem(ItemStack itemStack) {
//        return this.items.stream().filter(item -> item.getItemStack().equals(itemStack)).findFirst().orElse(null);
        return this.mapItems.values().stream().filter(item -> item.getItemStack().equals(itemStack)).findFirst().orElse(null);
    }

    /**
     * Get the MenuItem from an index input.
     *
     * @param index slot to search
     * @return MenuItem version of the found ItemStack
     */
    @Override
    public MenuItem getMenuItem(int index) {
        return this.mapItems.getOrDefault(index, null);
//        return this.mapItems.values().stream().filter(item -> item.getIndex() == index).findFirst().orElse(null);
    }

    /**
     * Get the amount of rows in the inventory.
     *
     * @return amount of rows
     */
    public final int getRows() {
        return rows;
    }

    /**
     * Get the display title of the inventory ui.
     *
     * @return display title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Get the flags assigned to said inventory.
     *
     * @return array of menu flags
     */
    public final MenuFlag[] getMenuFlags() {
        return menuFlags;
    }

    public boolean isSlotBlocked(int slot) {
        for (int i : this.mapItems.keySet()) {
            if (i != slot) continue;
            MenuItem item = this.mapItems.get(i);
            if (!item.isAllowingPickup()) {
                return true;
            }
        }

        return false;
    }
}
