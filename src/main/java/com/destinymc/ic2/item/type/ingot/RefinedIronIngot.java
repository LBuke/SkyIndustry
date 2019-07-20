package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class RefinedIronIngot extends IC2Item {

    public RefinedIronIngot() {
        this(1);
    }

    public RefinedIronIngot(int amount) {
        super(amount, 5, "Refined Iron Ingot");
    }
}
