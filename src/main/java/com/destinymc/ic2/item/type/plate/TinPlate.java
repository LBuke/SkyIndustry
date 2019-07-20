package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class TinPlate extends IC2Item {

    public TinPlate() {
        this(1);
    }

    public TinPlate(int amount) {
        super(amount, 62, "Tin Plate");
    }
}
