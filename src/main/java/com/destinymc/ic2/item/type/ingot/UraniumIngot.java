package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class UraniumIngot extends IC2Item {

    public UraniumIngot() {
        this(1);
    }

    public UraniumIngot(int amount) {
        super(amount, 9, "Uranium Ingot");
    }
}
