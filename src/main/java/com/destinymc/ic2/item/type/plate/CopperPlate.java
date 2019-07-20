package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class CopperPlate extends IC2Item {

    public CopperPlate() {
        this(1);
    }

    public CopperPlate(int amount) {
        super(amount, 55, "Copper Plate");
    }
}
