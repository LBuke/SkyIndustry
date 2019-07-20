package com.destinymc.ic2.machine.misc;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.visual.VisualMachine;
import com.destinymc.util.ServerUtil;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public interface MachineBlockState extends MachineAttribute {

    String META = "IC2STATE";

    default int getAmountOfStates() {
        return getAllStates().length;
    }

    int[] getAllStates();

    default int getState(VisualMachine visualMachine) {
        ArmorStand entity = visualMachine.getArmorStand();
        if (entity == null || !entity.isValid())
            return 0;

        if (!entity.hasMetadata(META))
            setState(visualMachine, 0);

        int state = entity.getMetadata(META).get(0).asInt();

        if (state < 0) {
            state = 0;
            this.setState(visualMachine, state);
            return state;
        }

        if (state > getAllStates().length - 1) {
            state = getAllStates().length - 1;
            this.setState(visualMachine, state);
            return state;
        }

        return getAllStates()[state];
    }

    default void setState(VisualMachine visualMachine, int state) {
        ArmorStand entity = visualMachine.getArmorStand();
        if (entity == null || !entity.isValid())
            return;

        entity.setMetadata(META, new FixedMetadataValue(ServerUtil.getPlugin(), state));

        ItemStack helmet = entity.getEquipment().getHelmet();
        if (helmet == null || helmet.getType() == Material.AIR) {
            entity.getEquipment().setHelmet(new ItemFactory(Material.DIAMOND_AXE)
                    .setDurability((short) getAllStates()[state])
                    .setUnbreaking(true).build());
            return;
        }

        helmet.setDurability((short) getAllStates()[state]);
    }
}
