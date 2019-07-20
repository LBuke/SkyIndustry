package com.destinymc.ic2.machine.types;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.BaseMachine;
import com.destinymc.ic2.machine.MachineFoundationType;
import com.destinymc.ic2.machine.item.Windmill;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.util.C;
import org.bukkit.Location;
import org.bukkit.Material;

public final class MachineWindGenerator extends BaseMachine {

    private static final int[] BLOCKED_SLOTS = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26};

    public MachineWindGenerator(long identifier, Location location) {
        super(
                identifier,
                location,
                "Windmill",
                new String[]{
                        C.DARK_GRAY + C.ITALIC + "TODO..",
                        " ",
                        C.WHITE + "...",
                        " "
                },
                3,
                7,
                5,
                MachineFoundationType.STONE
        );

        addBlockedSlots(BLOCKED_SLOTS);

        addSlotData(new MachineSlot[]{
                new MachineSlot(Material.DIAMOND_PICKAXE, 206), //Single Use Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 205), //RE Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 175), //Advanced RE Battery
        }, new int[]{3});

    }

    @Override
    public IC2Item getMachineItem() {
        return new Windmill(1);
    }
}
