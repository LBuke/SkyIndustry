package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class GoldCable extends IC2Item {

    public GoldCable() {
        this(1);
    }

    public GoldCable(int amount) {
        super(amount, 242, "Gold Cable");
    }
}
