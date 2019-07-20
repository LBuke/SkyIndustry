package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineMFS;
import org.bukkit.Location;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MFSUnit extends IC2Block implements MachineBlockItem<MachineMFS> {

    public MFSUnit() {
        this(1);
    }

    public MFSUnit(int amount) {
        super(amount, 3, "MFS Unit");
    }

    @Override
    public MachineMFS getMachine(long identifier, Location location) {
        return new MachineMFS(identifier, location, new MachinePowerData(0));
    }
}
