package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class GlassFibreCable extends IC2Item {

    public GlassFibreCable() {
        this(1);
    }

    public GlassFibreCable(int amount) {
        super(amount, 241, "Glass Fibre Cable");
    }
}
