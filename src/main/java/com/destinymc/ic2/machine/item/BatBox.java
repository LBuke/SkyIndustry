package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineBatBox;
import org.bukkit.Location;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BatBox extends IC2Block implements MachineBlockItem<MachineBatBox> {

    public BatBox() {
        this(1);
    }

    public BatBox(int amount) {
        super(amount, 1, "BatBox");
    }

    @Override
    public MachineBatBox getMachine(long identifier, Location location) {
        return new MachineBatBox(identifier, location, new MachinePowerData(0));
    }
}
