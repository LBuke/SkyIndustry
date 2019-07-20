package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class LapisPlate extends IC2Item {

    public LapisPlate() {
        this(1);
    }

    public LapisPlate(int amount) {
        super(amount, 58, "Lapis Plate");
    }
}
