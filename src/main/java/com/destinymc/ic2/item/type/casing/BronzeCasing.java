package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BronzeCasing extends IC2Item {

    public BronzeCasing() {
        this(1);
    }

    public BronzeCasing(int amount) {
        super(amount, 10, "Bronze Casing");
    }
}
