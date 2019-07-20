package com.destinymc.ic2.item.type;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class Rubber extends IC2Item {

    public Rubber() {
        this(1);
    }

    public Rubber(int amount) {
        super(amount, 131, "Rubber");
    }
}
