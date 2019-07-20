package com.destinymc.inventory.button.preset;

import com.destinymc.factory.ItemFactory;
import com.destinymc.inventory.BaseMenu;
import com.destinymc.inventory.button.ClickableItem;
import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created at 01/03/2019
 *
 * @author Luke Bingham
 */
public final class PreviousPageCommon extends ClickableItem {

    public PreviousPageCommon(int index, @Nonnull Profile profile, @Nullable BaseMenu parent) {
        super(
                index,
                new ItemFactory(Material.ARROW).setName(I18n.get(profile.getLocale(), parent == null ? "menu.item.exit" : "menu.item.back")).build(),
                (player, clickType) -> {

                    // If the given parent is null, just close inventory.
                    if (parent == null) {
                        player.closeInventory();
                    }

                    // Otherwise, open the given parent inventory.
                    else {
                        parent.openInventory(player);
                    }
                }
        );
    }

    public PreviousPageCommon(int index, @Nonnull Profile profile) {
        super(
                index,
                new ItemFactory(Material.ARROW).setName(I18n.get(profile.getLocale(), "menu.item.exit")).build(),
                (player, clickType) -> {

                    // No parent inventory is given, therefor close inventory.
                    player.closeInventory();
                }
        );
    }
}
