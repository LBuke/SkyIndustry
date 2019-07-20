package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class GoldPlate extends IC2Item {

    public GoldPlate() {
        this(1);
    }

    public GoldPlate(int amount) {
        super(amount, 56, "Gold Plate");
    }
}
