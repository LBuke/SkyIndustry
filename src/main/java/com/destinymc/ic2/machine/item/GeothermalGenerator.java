package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineGeothermalGenerator;
import com.destinymc.misc.Unfinished;
import org.bukkit.Location;

@Unfinished
public class GeothermalGenerator extends IC2Block implements MachineBlockItem<MachineGeothermalGenerator> {

    public GeothermalGenerator() {
        this(1);
    }

    public GeothermalGenerator(int amount) {
        super(amount, 9, "Geothermal Generator");
    }

    @Override
    public MachineGeothermalGenerator getMachine(long identifier, Location location) {
        return new MachineGeothermalGenerator(identifier, location, new MachinePowerData(0));
    }
}
