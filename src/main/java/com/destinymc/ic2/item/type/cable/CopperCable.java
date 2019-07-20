package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class CopperCable extends IC2Item {

    public CopperCable() {
        this(1);
    }

    public CopperCable(int amount) {
        super(amount, 238, "Copper Cable");
    }
}
