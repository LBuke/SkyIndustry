package com.destinymc.ic2.machine.item.tile;

import com.destinymc.ic2.item.misc.UnfunctionalItem;
import org.bukkit.Material;

import java.util.List;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class LockedLeftArrowTile extends MachineTileItem implements UnfunctionalItem {

    public LockedLeftArrowTile(String name, List<String> lore) {
        super(104, Material.DIAMOND_HOE, name, lore);
    }

    public LockedLeftArrowTile(int amount, String name, List<String> lore) {
        super(amount, 104, Material.DIAMOND_HOE, name, lore);
    }
}
