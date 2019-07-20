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
public final class WorkbenchGuideTile extends MachineTileItem implements UnfunctionalItem {

    public WorkbenchGuideTile(String name, List<String> lore) {
        super(3, Material.DIAMOND_HOE, name, lore);
    }

    public WorkbenchGuideTile(int amount, String name, List<String> lore) {
        super(amount, 3, Material.DIAMOND_HOE, name, lore);
    }
}
