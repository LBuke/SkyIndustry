package com.destinymc.ic2.machine.item.tile.guide;

import com.destinymc.ic2.item.misc.UnfunctionalItem;
import com.destinymc.ic2.machine.item.tile.MachineTileItem;
import org.bukkit.Material;

import java.util.List;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class FurnaceGuideTile extends MachineTileItem implements UnfunctionalItem {

    public FurnaceGuideTile(String name, List<String> lore) {
        super(4, Material.DIAMOND_HOE, name, lore);
    }

    public FurnaceGuideTile(int amount, String name, List<String> lore) {
        super(amount, 4, Material.DIAMOND_HOE, name, lore);
    }
}
