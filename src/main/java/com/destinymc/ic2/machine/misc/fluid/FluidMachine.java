package com.destinymc.ic2.machine.misc.fluid;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.misc.MachineBlockState;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.visual.VisualMachine;
import com.destinymc.util.C;
import com.destinymc.util.ServerUtil;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public interface FluidMachine {

    String META_FLUID = "IC2_FLUID";
    String META_VAL = "IC2_FLUID_VAL";

    FluidType getFluidType();

    int getMaxInputML();

    int getMaxOutputML();

    int getBarSlot();

    default int[] getBarSlotsArray() {
        return new int[]{this.getBarSlot()};
    }

    int getFluidInputSlot();

    int getFluidOutputSlot();

    void onDrain(Machine machine, VisualMachine visualMachine);

    default MachineTileType getTileType() {
        switch (this.getFluidType()) {
            case LAVA:
                return MachineTileType.FLUID_LAVA;
        }

        return MachineTileType.FLUID_LAVA;
    }

    default void drain(Machine machine) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        int fluid = this.getFluid(machine);

        if (this.isEmpty(fluid - 1)) {
            if (machine instanceof MachineBlockState) {
                MachineBlockState machineBlockState = (MachineBlockState) machine;
                machineBlockState.setState(machine.getVisualMachine(), 0);
            }
        }

        this.setFluid(machine, fluid - 1);

        this.onDrain(machine, vs);
    }

    default void refill(Machine machine, double fuelWorth) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        entity.setMetadata(META_VAL, new FixedMetadataValue(ServerUtil.getPlugin(), fuelWorth));
        setFluid(machine, getTileType().getTextures());

        if (machine instanceof MachineBlockState) {
            MachineBlockState machineBlockState = (MachineBlockState) machine;
            machineBlockState.setState(vs, 1);
        }
    }

    default boolean canRefill(Machine machine) {
        if (machine instanceof PoweredMachine) {
            PoweredMachine poweredMachine = (PoweredMachine) machine;
            MachinePowerData data = poweredMachine.getPowerData();

            return data.getCurrent() < data.getMaximumCapacity();
        }

        return false;
    }

    default boolean isEmpty(int fluid) {
        return fluid <= 0;
    }

    default int getFluid(Machine machine) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return 0;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return 0;

        if (!entity.hasMetadata(META_FLUID)) {
            entity.setMetadata(META_FLUID, new FixedMetadataValue(ServerUtil.getPlugin(), 0));
            return 0;
        }

        return entity.getMetadata(META_FLUID).get(0).asInt();
    }

    default void setFluid(Machine machine, int fuel) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        entity.setMetadata(META_FLUID, new FixedMetadataValue(ServerUtil.getPlugin(), fuel));

        ItemStack fuelItem = machine.getInventory().getItem(this.getBarSlot());

        if (fuelItem == null || fuelItem.getType() != getTileType().getMaterial()) {
            machine.getInventory().setItem(
                    this.getBarSlot(),
                    this.getTexturedItem(machine.getVisualMachine())
                            .setName(C.WHITE + C.BOLD + machine.getName())
                            .setLore(machine.getDescription())
                            .build()
            );
            return;
        }

        fuelItem.setDurability((short) (getTileType().getTextureStart() + fuel));
    }

    default ItemFactory getTexturedItem(VisualMachine visualMachine) {
        ArmorStand entity = visualMachine.getArmorStand();
        if (entity == null || !entity.isValid())
            return new ItemFactory(Material.AIR);

        int fuel = entity.hasMetadata(META_FLUID) ? entity.getMetadata(META_FLUID).get(0).asInt() : 0;

        MachineTileType type = MachineTileType.FLUID_LAVA;
        return new ItemFactory(type.getMaterial())
                .setDurability((short) (type.getTextureStart() + fuel))
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
                .setUnbreaking(true);
    }
}
