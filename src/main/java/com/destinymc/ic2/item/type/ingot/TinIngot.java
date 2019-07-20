package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class TinIngot extends IC2Item {

    public TinIngot() {
        this(1);
    }

    public TinIngot(int amount) {
        super(amount, 8, "Tin Ingot");
    }
}
