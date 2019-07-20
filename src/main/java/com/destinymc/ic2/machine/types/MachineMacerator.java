package com.destinymc.ic2.machine.types;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.BaseMachine;
import com.destinymc.ic2.machine.MachineFoundationType;
import com.destinymc.ic2.machine.item.Macerator;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.process.ProcessMachine;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.ic2.recipe.RecipeManager;
import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineMacerator extends BaseMachine implements ProcessMachine, PoweredMachine {

    private static final int[] BLOCKED_SLOTS = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26};

    private MachinePowerData powerData;
    private BaseMaceratorRecipe recipe = null;

    public MachineMacerator(long identifier, Location location, MachinePowerData powerData) {
        super(
                identifier,
                location,
                "Macerator",
                new String[] {
                        " "
                },
                3,
                11,
                6,
                MachineFoundationType.STONE
        );

        powerData.setPowerType(MachineTileType.POWER);
        powerData.setPowerSlots(new int[]{this.getPowerIconSlot()});
        powerData.setIntake(32);
        powerData.setOuttake(8);
        powerData.setMaximumCapacity(1200);
        powerData.setInputSlot(this.getPowerSlot());
        this.powerData = powerData;

        addBlockedSlots(BLOCKED_SLOTS);

        addSlotData(new MachineSlot[]{
                new MachineSlot(Material.DIAMOND_PICKAXE, 206), //Single Use Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 205), //RE Battery
                new MachineSlot(Material.DIAMOND_PICKAXE, 175), //Advanced RE Battery
        }, new int[]{21});
    }

    @Override
    public void init() {

    }

    @Override
    public int getProcessIconSlot() {
        return 13;
    }

    @Override
    public int getPowerIconSlot() {
        return 12;
    }

    @Override
    public int getProcessSlot() {
        return 3;
    }

    @Override
    public int getPowerSlot() {
        return 21;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {15};
    }

    @Override
    public BaseMaceratorRecipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean setInput(IItem item) {
        if (item == null) {
            recipe = null;
            return false;
        }

        List<BaseRecipe.Recipe> recipeList = RecipeManager.getMaceratorRecipesViaInput(item.toItem());
        for (BaseRecipe.Recipe recipe : recipeList) {
            if (recipe.getBaseRecipe() instanceof BaseMaceratorRecipe) {
                this.recipe = (BaseMaceratorRecipe) recipe.getBaseRecipe();
                return true;
            }
        }

        return false;
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
        return false;
    }

    @Override
    public IC2Item getMachineItem() {
        return new Macerator(1);
    }
}
