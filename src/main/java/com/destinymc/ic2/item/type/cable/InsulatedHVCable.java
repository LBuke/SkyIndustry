package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedHVCable extends IC2Item {

    public InsulatedHVCable() {
        this(1);
    }

    public InsulatedHVCable(int amount) {
        super(amount, 246, "Insulated HV Cable");
    }
}
