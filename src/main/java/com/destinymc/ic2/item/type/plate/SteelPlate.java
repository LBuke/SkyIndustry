package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class SteelPlate extends IC2Item {

    public SteelPlate() {
        this(1);
    }

    public SteelPlate(int amount) {
        super(amount, 61, "Steel Plate");
    }
}
