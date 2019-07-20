package com.destinymc.ic2.machine.types;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.BaseMachine;
import com.destinymc.ic2.machine.MachineFoundationType;
import com.destinymc.ic2.machine.item.SolarPanel;
import com.destinymc.ic2.machine.item.tile.MoonTile;
import com.destinymc.ic2.machine.item.tile.SunTile;
import com.destinymc.ic2.machine.misc.InnerChangeMachine;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.util.C;
import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class MachineSolarGenerator extends BaseMachine implements PoweredMachine, InnerChangeMachine {

    private static final int[] BLOCKED_SLOTS = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};

    private final MachinePowerData powerData;

    public MachineSolarGenerator(long identifier, Location location, MachinePowerData powerData) {
        super(
                identifier,
                location,
                "Solar Panel",
                new String[]{
                        C.DARK_GRAY + C.ITALIC + "TODO..",
                        " ",
                        C.WHITE + "Generators are used to either",
                        C.WHITE + "supply direct power to machines",
                        C.WHITE + "or charge RE Batteries.",
                        " "
                },
                2,
                6,
                8,
                MachineFoundationType.STONE
        );

        powerData.setPowerType(MachineTileType.BAR_1X3);
        powerData.setPowerSlots(new int[]{5, 6, 7});
        powerData.setIntake(8);
        powerData.setOuttake(10);
        powerData.setMaximumCapacity(4000);
        powerData.setOutputSlot(3);
        this.powerData = powerData;

        addBlockedSlots(BLOCKED_SLOTS);

        addSlotData(new MachineSlot[]{
                new MachineSlot(Material.DIAMOND_PICKAXE, 206), //Single Use Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 205), //RE Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 175), //Advanced RE Battery
        }, new int[]{3});

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
        return false;
    }

    @Override
    public IC2Item getMachineItem() {
        return new SolarPanel(1);
    }

    @Override
    public ItemStack[][] getObjects() {
        return new ItemStack[][] {
                {
                    new SunTile("", Lists.newArrayList()),
                    new MoonTile("", Lists.newArrayList())
                }
        };
    }

    @Override
    public int[] getObjectSlots() {
        return new int[] { 12 };
    }
}
