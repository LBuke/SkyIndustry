package com.destinymc.ic2.machine.misc.fueled;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.misc.MachineAttribute;
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

/**
 * Created at 29/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface FueledMachine extends MachineAttribute {

    String META_FUEL = "IC2_FUEL";
    String META_VAL = "IC2_FUEL_VAL";

    int getFlameSlot();

    int getFuelSlot();

    default void drain(Machine machine) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        int fuel = this.getFuel(machine);

        if (this.isEmpty(fuel - 1)) {
            if (machine instanceof MachineBlockState) {
                MachineBlockState machineBlockState = (MachineBlockState) machine;
                machineBlockState.setState(machine.getVisualMachine(), 0);
            }
        }

        this.setFuel(machine, fuel - 1);

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

    default void refill(Machine machine, double fuelWorth) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        entity.setMetadata(META_VAL, new FixedMetadataValue(ServerUtil.getPlugin(), fuelWorth));
        setFuel(machine, MachineTileType.FUEL_FLAME.getTextures());

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

    default boolean isEmpty(int fuel) {
        return fuel <= 0;
    }

    default int getFuel(Machine machine) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return 0;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return 0;

        if (!entity.hasMetadata(META_FUEL)) {
            entity.setMetadata(META_FUEL, new FixedMetadataValue(ServerUtil.getPlugin(), 0));
            return 0;
        }

        return entity.getMetadata(META_FUEL).get(0).asInt();
    }

    default void setFuel(Machine machine, int fuel) {
        VisualMachine vs = machine.getVisualMachine();
        if (vs == null)
            return;

        ArmorStand entity = vs.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        entity.setMetadata(META_FUEL, new FixedMetadataValue(ServerUtil.getPlugin(), fuel));

        ItemStack fuelItem = machine.getInventory().getItem(this.getFuelSlot());
        MachineTileType type = MachineTileType.FUEL_FLAME;

        if (fuelItem == null || fuelItem.getType() != type.getMaterial()) {
            machine.getInventory().setItem(
                    this.getFlameSlot(),
                    this.getTexturedItem(machine.getVisualMachine())
                            .setName(C.WHITE + C.BOLD + machine.getName())
                            .setLore(machine.getDescription())
                            .build()
            );
            return;
        }

        fuelItem.setDurability((short) (type.getTextureStart() + fuel));
    }

    default ItemFactory getTexturedItem(VisualMachine visualMachine) {
        ArmorStand entity = visualMachine.getArmorStand();
        if (entity == null || !entity.isValid())
            return new ItemFactory(Material.AIR);

        int fuel = entity.hasMetadata(META_FUEL) ? entity.getMetadata(META_FUEL).get(0).asInt() : 0;

        MachineTileType type = MachineTileType.FUEL_FLAME;
        return new ItemFactory(type.getMaterial())
                .setDurability((short) (type.getTextureStart() + fuel))
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
                .setUnbreaking(true);
    }
}
