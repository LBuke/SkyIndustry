package com.destinymc.ic2.machine.item.tile;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.misc.UnfunctionalItem;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class MachineTileItem extends IC2Item implements UnfunctionalItem {

    public MachineTileItem(int identifier, Material material, String name, List<String> lore) {
        this(1, identifier, material, name, lore);
    }

    public MachineTileItem(int amount, int identifier, Material material, String name, List<String> lore) {
        super(amount, identifier, name);

        this.setType(material);

        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        this.setItemMeta(meta);
    }
}
