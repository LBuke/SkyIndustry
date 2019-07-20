package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class GoldCasing extends IC2Item {

    public GoldCasing() {
        this(1);
    }

    public GoldCasing(int amount) {
        super(amount, 12, "Gold Casing");
    }
}
