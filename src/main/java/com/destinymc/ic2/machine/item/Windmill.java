package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.types.MachineWaterGenerator;
import com.destinymc.ic2.machine.types.MachineWindGenerator;
import org.bukkit.Location;

public final class Windmill extends IC2Block implements MachineBlockItem<MachineWindGenerator> {

    public Windmill() {
        this(1);
    }

    public Windmill(int amount) {
        super(amount, 8, "Windmill");
    }

    @Override
    public MachineWindGenerator getMachine(long identifier, Location location) {
        return new MachineWindGenerator(identifier, location);
    }
}
