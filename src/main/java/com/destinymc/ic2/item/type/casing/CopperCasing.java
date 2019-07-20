package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class CopperCasing extends IC2Item {

    public CopperCasing() {
        this(1);
    }

    public CopperCasing(int amount) {
        super(amount, 11, "Copper Casing");
    }
}
