package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class CopperIngot extends IC2Item {

    public CopperIngot() {
        this(1);
    }

    public CopperIngot(int amount) {
        super(amount, 3, "Copper Ingot");
    }
}
