package com.destinymc.ic2.machine.item.tile;

import com.destinymc.ic2.item.misc.UnfunctionalItem;
import org.bukkit.Material;

import java.util.List;

public final class SunTile extends MachineTileItem implements UnfunctionalItem {

    public SunTile(String name, List<String> lore) {
        super(180, Material.DIAMOND_HOE, name, lore);
    }

    public SunTile(int amount, String name, List<String> lore) {
        super(amount, 180, Material.DIAMOND_HOE, name, lore);
    }
}
