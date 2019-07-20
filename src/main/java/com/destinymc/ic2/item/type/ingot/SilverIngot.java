package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class SilverIngot extends IC2Item {

    public SilverIngot() {
        this(1);
    }

    public SilverIngot(int amount) {
        super(amount, 6, "Silver Ingot");
    }
}
