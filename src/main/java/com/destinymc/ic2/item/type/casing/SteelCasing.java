package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class SteelCasing extends IC2Item {

    public SteelCasing() {
        this(1);
    }

    public SteelCasing(int amount) {
        super(amount, 15, "Steel Casing");
    }
}
