package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineMFE;
import org.bukkit.Location;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MFEUnit extends IC2Block implements MachineBlockItem<MachineMFE> {

    public MFEUnit() {
        this(1);
    }

    public MFEUnit(int amount) {
        super(amount, 2, "MFE Unit");
    }

    @Override
    public MachineMFE getMachine(long identifier, Location location) {
        return new MachineMFE(identifier, location, new MachinePowerData(0));
    }
}
