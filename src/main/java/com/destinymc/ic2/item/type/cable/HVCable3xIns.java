package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class HVCable3xIns extends IC2Item {

    public HVCable3xIns() {
        this(1);
    }

    public HVCable3xIns(int amount) {
        super(amount, 248, "3xIns. HV Cable");
    }
}
