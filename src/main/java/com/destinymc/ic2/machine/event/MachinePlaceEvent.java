package com.destinymc.ic2.machine.event;

import com.destinymc.ic2.machine.Machine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachinePlaceEvent extends MachineEvent implements Cancellable {

    private final Player player;
    private final Machine machine;
    private final Location location;

    private boolean cancelled;

    public MachinePlaceEvent(Player player, Machine machine, Location location) {
        super(false);

        this.player = player;
        this.machine = machine;
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public Machine getMachine() {
        return machine;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
