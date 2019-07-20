package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class AlloyIngot extends IC2Item {

    public AlloyIngot() {
        this(1);
    }

    public AlloyIngot(int amount) {
        super(amount, 1, "Alloy Ingot");
    }
}
