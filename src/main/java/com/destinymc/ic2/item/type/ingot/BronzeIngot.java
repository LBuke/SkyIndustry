package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BronzeIngot extends IC2Item {

    public BronzeIngot() {
        this(1);
    }

    public BronzeIngot(int amount) {
        super(amount, 2, "Bronze Ingot");
    }
}
