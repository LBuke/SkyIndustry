package com.destinymc.inventory.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface MenuClickAction {

    /**
     * This is fired when an item is interacted with.
     *
     * @param player    Player who interacted
     * @param clickType interaction type
     */
    void onClick(Player player, ClickType clickType);
}
