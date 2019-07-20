package com.destinymc.ic2.item.type.eu;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.misc.ChangableItemState;
import com.destinymc.ic2.item.misc.PoweredItem;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.util.CollectionUtil;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class REBattery extends IC2Item implements ChangableItemState, PoweredItem {//07793848046

    private static final int[] STATES = {201, 202, 203, 204, 205};

    private static final int POWER_CAP = 10000;
    private static final int INTAKE = 100, OUTTAKE = 100;

    public REBattery() {
        this(1);
    }

    public REBattery(int amount) {
        super(amount, 205, "RE Battery");

        ItemMeta meta = this.getItemMeta();
        meta.setLore(CollectionUtil.combineLists(
                Arrays.asList(
                        "{t=REBattery.lore;" + INTAKE + ";" + OUTTAKE + ";" + MachineUtil.getFormattedPower(POWER_CAP) + "}",
                        " "
                ),
                meta.getLore())
        );
        this.setItemMeta(meta);

        this.setState(this, STATES.length - 1);
        this.setPower(this, this, 0);
    }

    @Override
    public int[] getAllStates() {
        return STATES;
    }

    @Override
    public int getMaximumPowerCapacity() {
        return POWER_CAP;
    }

    @Override
    public int getPowerIntake() {
        return INTAKE;
    }

    @Override
    public int getPowerOuttake() {
        return OUTTAKE;
    }
}
