package com.destinymc.ic2.machine.item.tile;

import com.destinymc.ic2.item.misc.UnfunctionalItem;
import org.bukkit.Material;

import java.util.List;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class RightArrowTile extends MachineTileItem implements UnfunctionalItem {

    public RightArrowTile(String name, List<String> lore) {
        super(103, Material.DIAMOND_HOE, name, lore);
    }

    public RightArrowTile(int amount, String name, List<String> lore) {
        super(amount, 103, Material.DIAMOND_HOE, name, lore);
    }
}
