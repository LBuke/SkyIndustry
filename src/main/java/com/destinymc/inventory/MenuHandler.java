package com.destinymc.inventory;

import com.destinymc.inventory.button.ClickableItem;
import com.destinymc.inventory.button.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public class MenuHandler implements Listener {

    public MenuHandler(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    protected final void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() == null)
            return;

        if (!(event.getInventory().getHolder() instanceof BaseMenu))
            return;

        if (!(event.getWhoClicked() instanceof Player))
            return;

        BaseMenu menu = (BaseMenu) event.getInventory().getHolder();

        boolean blocked = false;
        for (int slot : event.getRawSlots()) {
            if (menu.isSlotBlocked(slot)) {
                blocked = true;
                break;
            }
        }

        menu.onInteract(menu);

        event.setCancelled(blocked);
    }

    @EventHandler
    protected final void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() == null)
            return;

        if (!(event.getInventory().getHolder() instanceof BaseMenu))
            return;

        if (!(event.getWhoClicked() instanceof Player))
            return;

        BaseMenu menu = (BaseMenu) event.getInventory().getHolder();

        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();

        if (clicked == null && menu.hasFlag(MenuFlag.CLOSE_ON_NULL_CLICK)) {
            player.closeInventory();
            return;
        }

        menu.onInteract(menu);

        MenuItem item = menu.getMenuItem(event.getRawSlot());
        if (item == null)
            return;

        if (!item.isAllowingPickup())
            event.setCancelled(true);

        if (item instanceof ClickableItem)
            ((ClickableItem) item).getClickAction().onClick(player, event.getClick());
    }

    @EventHandler
    protected final void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() == null)
            return;

        if (!(event.getInventory().getHolder() instanceof BaseMenu))
            return;

        if (!(event.getPlayer() instanceof Player))
            return;

        BaseMenu menu = (BaseMenu) event.getInventory().getHolder();
        menu.onClose(event);
    }
}
