package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class DetectorCable extends IC2Item {

    public DetectorCable() {
        this(1);
    }

    public DetectorCable(int amount) {
        super(amount, 240, "Detector Cable");
    }
}
