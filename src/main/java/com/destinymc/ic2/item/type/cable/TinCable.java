package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class TinCable extends IC2Item {

    public TinCable() {
        this(1);
    }

    public TinCable(int amount) {
        super(amount, 250, "Tin Cable");
    }
}
