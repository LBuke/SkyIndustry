package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class GoldCable2xIns extends IC2Item {

    public GoldCable2xIns() {
        this(1);
    }

    public GoldCable2xIns(int amount) {
        super(amount, 244, "2xIns. Gold Cable");
    }
}
