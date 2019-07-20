package com.destinymc.ic2.machine.item.tile;

import com.destinymc.ic2.item.misc.UnfunctionalItem;
import org.bukkit.Material;

import java.util.List;

public final class MoonTile extends MachineTileItem implements UnfunctionalItem {

    public MoonTile(String name, List<String> lore) {
        super(181, Material.DIAMOND_HOE, name, lore);
    }

    public MoonTile(int amount, String name, List<String> lore) {
        super(amount, 181, Material.DIAMOND_HOE, name, lore);
    }
}
