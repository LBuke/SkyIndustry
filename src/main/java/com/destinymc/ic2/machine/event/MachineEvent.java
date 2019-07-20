package com.destinymc.ic2.machine.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created at 29/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class MachineEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    /**
     * Construct a new Event
     *
     * @param async Async
     */
    public MachineEvent(boolean async) {
        super(async);
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
