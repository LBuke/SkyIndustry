package com.destinymc.ic2.item.type.ingot;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class LeadIngot extends IC2Item {

    public LeadIngot() {
        this(1);
    }

    public LeadIngot(int amount) {
        super(amount, 4, "Lead Ingot");
    }
}
