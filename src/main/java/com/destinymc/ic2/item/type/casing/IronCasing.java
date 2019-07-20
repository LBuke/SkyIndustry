package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class IronCasing extends IC2Item {

    public IronCasing() {
        this(1);
    }

    public IronCasing(int amount) {
        super(amount, 13, "Iron Casing");
    }
}
