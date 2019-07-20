package com.destinymc.ic2.item.type.tool;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulationCutter extends IC2Item {

    public InsulationCutter() {
        this(1);
    }

    public InsulationCutter(int amount) {
        super(amount, 280, "Insulation Cutter");
    }
}
