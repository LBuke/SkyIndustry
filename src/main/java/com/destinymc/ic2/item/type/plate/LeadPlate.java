package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class LeadPlate extends IC2Item {

    public LeadPlate() {
        this(1);
    }

    public LeadPlate(int amount) {
        super(amount, 59, "Lead Plate");
    }
}
