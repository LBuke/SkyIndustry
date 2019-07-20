package com.destinymc.ic2.item.type.cable;

import com.destinymc.ic2.item.IC2Item;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class SplitterCable extends IC2Item {

    public SplitterCable() {
        this(1);
    }

    public SplitterCable(int amount) {
        super(amount, 249, "Splitter Cable");
    }
}
