package com.destinymc.ic2.item.type.plate;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class ObsidianPlate extends IC2Item {

    public ObsidianPlate() {
        this(1);
    }

    public ObsidianPlate(int amount) {
        super(amount, 60, "Obsidian Plate");
    }
}
