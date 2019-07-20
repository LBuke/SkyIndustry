package com.destinymc.ic2.item.type.tool;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class ForgeHammer extends IC2Item {

    public ForgeHammer() {
        this(1);
    }

    public ForgeHammer(int amount) {
        super(amount, 283, "Forge Hammer");
    }
}
