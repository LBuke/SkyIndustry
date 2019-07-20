package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineSolarGenerator;
import com.destinymc.ic2.machine.types.MachineWaterGenerator;
import org.bukkit.Location;

public final class Watermill extends IC2Block implements MachineBlockItem<MachineWaterGenerator> {

    public Watermill() {
        this(1);
    }

    public Watermill(int amount) {
        super(amount, 7, "Watermill");
    }

    @Override
    public MachineWaterGenerator getMachine(long identifier, Location location) {
        return new MachineWaterGenerator(identifier, location);
    }
}
