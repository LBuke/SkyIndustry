package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class TinCasing extends IC2Item {

    public TinCasing() {
        this(1);
    }

    public TinCasing(int amount) {
        super(amount, 16, "Tin Casing");
    }
}
