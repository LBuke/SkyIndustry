package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class IronPlate extends IC2Item {

    public IronPlate() {
        this(1);
    }

    public IronPlate(int amount) {
        super(amount, 57, "Iron Plate");
    }
}
