package com.destinymc.ic2.machine.item;

import com.destinymc.ic2.item.IC2Block;
import com.destinymc.ic2.item.misc.MachineBlockItem;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.types.MachineGenerator;
import org.bukkit.Location;

/**
 * Created at 06/02/2019
  * <p>
 * @author Luke Bingham
 */
public final class Generator extends IC2Block implements MachineBlockItem<MachineGenerator> {

    public Generator() {
        this(1);
    }

    public Generator(int amount) {
        super(amount, 4, "Generator");
    }

    @Override
    public MachineGenerator getMachine(long identifier, Location location) {
        return new MachineGenerator(identifier, location, new MachinePowerData(0));
    }
}
