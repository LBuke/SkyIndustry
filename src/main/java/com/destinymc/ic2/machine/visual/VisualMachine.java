package com.destinymc.ic2.machine.visual;

import com.destinymc.ic2.machine.Machine;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface VisualMachine {

    Machine getBaseMachine();

    Location getEntityLocation();

    ArmorStand getArmorStand();
}
