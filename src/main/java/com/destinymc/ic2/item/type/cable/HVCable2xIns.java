package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class HVCable2xIns extends IC2Item {

    public HVCable2xIns() {
        this(1);
    }

    public HVCable2xIns(int amount) {
        super(amount, 247, "2xIns. HV Cable");
    }
}
