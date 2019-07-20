package com.destinymc.ic2.machine.types;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.BaseMachine;
import com.destinymc.ic2.machine.MachineFoundationType;
import com.destinymc.ic2.machine.item.MFEUnit;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.util.C;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineMFE extends BaseMachine implements PoweredMachine {

    private static final int[] ORDERED_SLOTS = {1};
    private static final int[] BLOCKED_SLOTS = {0, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35};

    private final MachinePowerData powerData;

    public MachineMFE(long identifier, Location location, MachinePowerData powerData) {
        super(
                identifier,
                location,
                "MFE",
                new String[]{
                        C.DARK_GRAY + C.ITALIC + "EU storage block",
                        " ",
                        C.WHITE + "Power tier: 3 (Max I/O 128 EU/t)",
                        C.WHITE + "Capacity: 600k EU",
                        " "
                },
                4,
                2,
                1,
                MachineFoundationType.STONE
        );

        powerData.setPowerType(MachineTileType.BAR_1X3);
        powerData.setPowerSlots(new int[]{14, 15, 16});
        powerData.setIntake(128);
        powerData.setOuttake(128);
        powerData.setInputSlot(21);
        powerData.setOutputSlot(3);
        powerData.setMaximumCapacity(600000);
        this.powerData = powerData;

        addBlockedSlots(BLOCKED_SLOTS);

        addSlotData(new MachineSlot[]{
                new MachineSlot.VanillaItem(Material.LEATHER_HELMET),
                new MachineSlot.VanillaItem(Material.CHAINMAIL_HELMET),
                new MachineSlot.VanillaItem(Material.IRON_HELMET),
                new MachineSlot.VanillaItem(Material.GOLD_HELMET),
                new MachineSlot.VanillaItem(Material.DIAMOND_HELMET),
        }, new int[]{1});

        addSlotData(new MachineSlot[]{
                new MachineSlot.VanillaItem(Material.LEATHER_CHESTPLATE),
                new MachineSlot.VanillaItem(Material.CHAINMAIL_CHESTPLATE),
                new MachineSlot.VanillaItem(Material.IRON_CHESTPLATE),
                new MachineSlot.VanillaItem(Material.GOLD_CHESTPLATE),
                new MachineSlot.VanillaItem(Material.DIAMOND_CHESTPLATE),
        }, new int[]{10});

        addSlotData(new MachineSlot[]{
                new MachineSlot.VanillaItem(Material.LEATHER_LEGGINGS),
                new MachineSlot.VanillaItem(Material.CHAINMAIL_LEGGINGS),
                new MachineSlot.VanillaItem(Material.IRON_LEGGINGS),
                new MachineSlot.VanillaItem(Material.GOLD_LEGGINGS),
                new MachineSlot.VanillaItem(Material.DIAMOND_LEGGINGS),
        }, new int[]{19});

        addSlotData(new MachineSlot[]{
                new MachineSlot.VanillaItem(Material.LEATHER_BOOTS),
                new MachineSlot.VanillaItem(Material.CHAINMAIL_BOOTS),
                new MachineSlot.VanillaItem(Material.IRON_BOOTS),
                new MachineSlot.VanillaItem(Material.GOLD_BOOTS),
                new MachineSlot.VanillaItem(Material.DIAMOND_BOOTS),
        }, new int[]{28});

        addSlotData(new MachineSlot[]{
                new MachineSlot(Material.DIAMOND_PICKAXE, 206), //Single Use Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 205), //RE Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 175), //Advanced RE Battery
        }, new int[]{3, 21});
    }

    @Override
    public MachinePowerData getPowerData() {
        return powerData;
    }

    @Override
    public boolean doesAllowIntakeViaPoweredItem() {
        return true;
    }

    @Override
    public boolean doesAllowOuttakeViaPoweredItem() {
        return true;
    }

    @Override
    public IC2Item getMachineItem() {
        return new MFEUnit(1);
    }
}
