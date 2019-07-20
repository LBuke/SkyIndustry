package com.destinymc.ic2.guide;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.item.Macerator;
import org.bukkit.Material;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public enum GuideType {

    WORKBENCH(
            new ItemFactory(Material.WORKBENCH)
    ),

    FURNACE(
            new ItemFactory(Material.FURNACE)
    ),

    MACERATOR(
            new ItemFactory(new Macerator())
    ),

    ;

    private final ItemFactory itemFactory;

    GuideType(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public ItemFactory getItemFactory() {
        return itemFactory.clone();
    }
}
