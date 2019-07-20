package com.destinymc.ic2.machine.misc.process;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.misc.MachineAttribute;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.visual.VisualMachine;
import com.destinymc.ic2.recipe.RecipeManager;
import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import com.destinymc.util.C;
import com.destinymc.util.ServerUtil;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public interface ProcessMachine extends MachineAttribute {
    
    String META_PROCESS = "IC2_PROCESS";

    int getProcessIconSlot();
    int getPowerIconSlot();

    int getProcessSlot();
    int getPowerSlot();

    int[] getOutputSlots();

    default MachineTileType getTileType() {
        return MachineTileType.PROCESS;
    }

    BaseMaceratorRecipe getRecipe();
    boolean setInput(IItem item);

    default boolean start(Machine machine) {
        for (int i = 0; i < getOutputSlots().length; i++) {

            int slot = getOutputSlots()[i];
            if (slot == -1)
                continue;

            ItemStack currentItem = machine.getInventory().getItem(slot);
            if (currentItem != null && currentItem.getAmount() >= 64) {
                setInput(null);
                return false;
            }
        }

        ItemStack input = machine.getInventory().getItem(getProcessSlot());
        if (input == null || input.getType() == Material.AIR) {
            setInput(null);
            return false;
        }

        return setInput(new BaseItem(input));
    }

    default void next(Machine machine) {
        if (this.getRecipe() == null)
            return;

        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        int process = this.getProcess(machine);
        if (isFull(process)) {
            complete(machine);
            return;
        }

        if (machine instanceof PoweredMachine) {
            PoweredMachine poweredMachine = (PoweredMachine) machine;
            MachinePowerData data = poweredMachine.getPowerData();

            if (data.getCurrent() < 8)
                return;

            data.setCurrent(data.getCurrent() - 8);
            data.updateMachine(machine);
        }

        this.setProcess(machine, process + 1);
    }

    default void complete(Machine machine) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        setProcess(machine, 0);

        IItem[] result = getRecipe().getOutput();
        for (int i = 0; i < result.length; i++) {
            IItem item = result[i];
            if (item == null)
                continue;

            int slot = getOutputSlots()[i];
            if (slot == -1)
                continue;

            ItemStack currentItem = machine.getInventory().getItem(slot);
            if (currentItem == null || currentItem.getType() == Material.AIR) {
                machine.getInventory().setItem(slot, item.toItem());
                continue;
            }

            if (item.isMatch(currentItem)) {
                if (currentItem.getAmount() + item.toItem().getAmount() > 64) {
                    currentItem.setAmount(64);
                    continue;
                }

                currentItem.setAmount(currentItem.getAmount() + item.toItem().getAmount());
            }
        }
    }

    default boolean isFull(int process) {
        return process >= getTileType().getTextures();
    }

    default int getProcess(Machine machine) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return 0;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return 0;

        if (!entity.hasMetadata(META_PROCESS)) {
            entity.setMetadata(META_PROCESS, new FixedMetadataValue(ServerUtil.getPlugin(), 0));
            return 0;
        }

        return entity.getMetadata(META_PROCESS).get(0).asInt();
    }

    default void setProcess(Machine machine, int process) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        entity.setMetadata(META_PROCESS, new FixedMetadataValue(ServerUtil.getPlugin(), process));

        ItemStack item = machine.getInventory().getItem(this.getProcessSlot());

        if (item == null || item.getType() != getTileType().getMaterial()) {
            machine.getInventory().setItem(
                    this.getProcessIconSlot(),
                    this.getTexturedItem(machine.getVisualMachine())
                            .setName(C.WHITE + C.BOLD + machine.getName())
                            .setLore(machine.getDescription())
                            .build()
            );
            return;
        }

        item.setDurability((short) (getTileType().getTextureStart() + process));
    }

    default ItemFactory getTexturedItem(VisualMachine visualMachine) {
        ArmorStand entity = visualMachine.getArmorStand();
        if (entity == null || !entity.isValid())
            return new ItemFactory(Material.AIR);

        int process = entity.hasMetadata(META_PROCESS) ? entity.getMetadata(META_PROCESS).get(0).asInt() : 0;

        return new ItemFactory(getTileType().getMaterial())
                .setDurability((short) (getTileType().getTextureStart() + process))
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
                .setUnbreaking(true);
    }
}
