package com.destinymc.ic2.item.type.casing;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class LeadCasing extends IC2Item {

    public LeadCasing() {
        this(1);
    }

    public LeadCasing(int amount) {
        super(amount, 14, "Lead Casing");
    }
}
