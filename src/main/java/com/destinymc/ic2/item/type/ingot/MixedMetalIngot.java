package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MixedMetalIngot extends IC2Item {

    public MixedMetalIngot() {
        this(1);
    }

    public MixedMetalIngot(int amount) {
        super(amount, 1, "Mixed Metal Ingot");
    }
}
