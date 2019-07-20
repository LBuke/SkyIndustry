package com.destinymc.ic2.machine.visual;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.util.ServerUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BaseVisualMachine implements VisualMachine {

    private Machine baseMachine;
    private Location location;
    private ArmorStand armorStand;

    public BaseVisualMachine(Machine baseMachine) {
        this.baseMachine = baseMachine;

        this.location = baseMachine.getLocation().clone().add(0.5, -1.19, 0.5);//225

        this.armorStand = this.location.getWorld().spawn(this.location, ArmorStand.class);
        this.armorStand.setGravity(false);
        this.armorStand.setVisible(true);//TODO false
        this.armorStand.setMarker(false);//TODO true
        this.armorStand.setBasePlate(false);

        this.armorStand.setRemoveWhenFarAway(false);
        this.armorStand.setCustomName(baseMachine.getName());
        this.armorStand.setCustomNameVisible(true);
        this.armorStand.setMetadata(MachineUtil.MACHINE_META, new FixedMetadataValue(ServerUtil.getPlugin(), baseMachine.getIdentifier()));

        // Visual Machine block
        this.armorStand.setHelmet(new ItemFactory(Material.DIAMOND_AXE)
                .setDurability(baseMachine.getTexture())
                .setUnbreaking(true).build());
    }

    @Override
    public Machine getBaseMachine() {
        return baseMachine;
    }

    @Override
    public Location getEntityLocation() {
        return location;
    }

    @Override
    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
