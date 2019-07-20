package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineSolarGenerator;
import org.bukkit.Location;

public final class SolarPanel extends IC2Block implements MachineBlockItem<MachineSolarGenerator> {

    public SolarPanel() {
        this(1);
    }

    public SolarPanel(int amount) {
        super(amount, 6, "Solar Panel");
    }

    @Override
    public MachineSolarGenerator getMachine(long identifier, Location location) {
        return new MachineSolarGenerator(identifier, location, new MachinePowerData(0));
    }
}
