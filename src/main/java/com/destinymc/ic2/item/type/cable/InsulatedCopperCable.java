package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedCopperCable extends IC2Item {

    public InsulatedCopperCable() {
        this(1);
    }

    public InsulatedCopperCable(int amount) {
        super(amount, 239, "Insulated Copper Cable");
    }
}
