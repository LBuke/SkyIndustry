package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class HVCable extends IC2Item {

    public HVCable() {
        this(1);
    }

    public HVCable(int amount) {
        super(amount, 245, "HV Cable");
    }
}
