package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineGenerator;
import com.destinymc.ic2.machine.types.MachineMacerator;
import org.bukkit.Location;

public final class Macerator extends IC2Block implements MachineBlockItem<MachineMacerator> {

    public Macerator() {
        this(1);
    }

    public Macerator(int amount) {
        super(amount, 11, "Macerator");
    }

    @Override
    public MachineMacerator getMachine(long identifier, Location location) {
        return new MachineMacerator(identifier, location, new MachinePowerData(0));
    }
}
