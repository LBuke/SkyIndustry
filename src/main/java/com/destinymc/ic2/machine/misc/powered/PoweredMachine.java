package com.destinymc.ic2.machine.misc.powered;

import com.destinymc.ic2.machine.misc.MachineAttribute;

/**
 * Created at 16/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface PoweredMachine extends MachineAttribute {

    MachinePowerData getPowerData();

    boolean doesAllowIntakeViaPoweredItem();

    boolean doesAllowOuttakeViaPoweredItem();
}
