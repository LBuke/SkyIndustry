package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedGoldCable extends IC2Item {

    public InsulatedGoldCable() {
        this(1);
    }

    public InsulatedGoldCable(int amount) {
        super(amount, 243, "Insulated Gold Cable");
    }
}
