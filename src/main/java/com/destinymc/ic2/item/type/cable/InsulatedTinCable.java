package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedTinCable extends IC2Item {

    public InsulatedTinCable() {
        this(1);
    }

    public InsulatedTinCable(int amount) {
        super(amount, 251, "Insulated Tin Cable");
    }
}
