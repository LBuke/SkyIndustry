package com.destinymc.ic2.item;

import com.destinymc.ic2.item.misc.PlaceableItem;
import org.bukkit.Material;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class IC2Block extends IC2Item implements PlaceableItem {

    public IC2Block(int amount, int identifier, String name) {
        super(amount, identifier, name);

        setType(Material.DIAMOND_AXE);
    }

    @Override
    public int getIdentifier() {
        return super.getIdentifier();
    }

    @Override
    public IC2Block getBlockItem() {
        return this;
    }
}
