package com.destinymc.ic2.machine.types;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.BaseMachine;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.MachineFoundationType;
import com.destinymc.ic2.machine.item.GeothermalGenerator;
import com.destinymc.ic2.machine.misc.MachineBlockState;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.fluid.FluidMachine;
import com.destinymc.ic2.machine.misc.fluid.FluidType;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.ic2.machine.visual.VisualMachine;
import com.destinymc.util.C;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public final class MachineGeothermalGenerator extends BaseMachine implements PoweredMachine, MachineBlockState, FluidMachine {

    private static final int[] BLOCKED_SLOTS = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26};
    private static final int[] STATES = {9, 10};

    private final MachinePowerData powerData;

    public MachineGeothermalGenerator(long identifier, Location location, MachinePowerData powerData) {
        super(
                identifier,
                location,
                "Geothermal Generator",
                new String[]{
                        C.DARK_GRAY + C.ITALIC + "TODO..",
                        " ",
                        C.WHITE + "...",
                        " "
                },
                3,
                9,
                7,
                MachineFoundationType.STONE
        );

        powerData.setPowerType(MachineTileType.BAR_1X3);
        powerData.setPowerSlots(new int[]{14, 15, 16});
        powerData.setIntake(0);
        powerData.setOuttake(10);
        powerData.setMaximumCapacity(4000);
        powerData.setInputSlot(24);
        this.powerData = powerData;

        addBlockedSlots(BLOCKED_SLOTS);

        addSlotData(new MachineSlot[]{
                new MachineSlot(Material.DIAMOND_PICKAXE, 206), //Single Use Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 205), //RE Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 175), //Advanced RE Battery
        }, new int[]{3});
    }

    @Override
    public void init() {
        setState(this.getVisualMachine(), 0);
        setFluid(this, 0);
    }

    @Override
    public MachinePowerData getPowerData() {
        return powerData;
    }

    @Override
    public boolean doesAllowIntakeViaPoweredItem() {
        return false;
    }

    @Override
    public boolean doesAllowOuttakeViaPoweredItem() {
        return true;
    }

    @Override
    public int[] getAllStates() {
        return STATES;
    }

    @Override
    public FluidType getFluidType() {
        return FluidType.LAVA;
    }

    @Override
    public int getMaxInputML() {
        return 10;
    }

    @Override
    public int getMaxOutputML() {
        return 0;
    }

    @Override
    public int getBarSlot() {
        return 20;
    }

    @Override
    public int getFluidInputSlot() {
        return 0;
    }

    @Override
    public int getFluidOutputSlot() {
        return 18;
    }

    @Override
    public void onDrain(Machine machine, VisualMachine visualMachine) {
        ArmorStand entity = visualMachine.getArmorStand();
        if (entity == null)
            return;

        if (machine instanceof PoweredMachine) {
            if (entity.hasMetadata(META_VAL)) {
                double transfer = entity.getMetadata(META_VAL).get(0).asDouble();
                PoweredMachine poweredMachine = (PoweredMachine) machine;
                MachinePowerData data = poweredMachine.getPowerData();

                if (data.getCurrent() >= data.getMaximumCapacity())
                    return;

                int combined = data.getCurrent() + ((int) Math.round(transfer));
                if (combined >= data.getMaximumCapacity())
                    data.setCurrent(data.getMaximumCapacity());
                else
                    data.setCurrent(combined);

                data.updateMachine(machine);
            }
        }
    }

    @Override
    public IC2Item getMachineItem() {
        return new GeothermalGenerator(1);
    }
}
