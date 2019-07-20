package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BronzePlate extends IC2Item {

    public BronzePlate() {
        this(1);
    }

    public BronzePlate(int amount) {
        super(amount, 54, "Bronze Plate");
    }
}
