package com.destinymc.ic2.machine.event;

import com.destinymc.ic2.machine.Machine;

/**
 * Created at 07/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineInnerInitialiseEvent extends MachineEvent {

    private final Machine machine;

    public MachineInnerInitialiseEvent(Machine machine) {
        super(false);
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }
}
