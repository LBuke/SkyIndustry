package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class SteelIngot extends IC2Item {

    public SteelIngot() {
        this(1);
    }

    public SteelIngot(int amount) {
        super(amount, 7, "Steel Ingot");
    }
}
