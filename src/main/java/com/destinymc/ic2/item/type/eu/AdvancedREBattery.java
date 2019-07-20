package com.destinymc.ic2.item.type.eu;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.misc.ChangableItemState;
import com.destinymc.ic2.item.misc.PoweredItem;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.util.C;
import com.destinymc.util.CollectionUtil;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class AdvancedREBattery extends IC2Item implements ChangableItemState, PoweredItem {

    private static final int[] STATES = {171, 172, 173, 174, 175};

    private static final int POWER_CAP = 10000;
    private static final int INTAKE = 100, OUTTAKE = 100;

    public AdvancedREBattery() {
        this(1);
    }

    public AdvancedREBattery(int amount) {
        super(amount, 175, "Advanced RE Battery");

        ItemMeta meta = this.getItemMeta();
        meta.setLore(CollectionUtil.combineLists(
                Arrays.asList(
                        "{t=AdvancedREBattery.lore;" + INTAKE + ";" + OUTTAKE + ";" + MachineUtil.getFormattedPower(POWER_CAP) + "}",
                        " "
                ),
                meta.getLore())
        );
        this.setItemMeta(meta);

        this.setState(this, STATES.length - 1);
        this.setPower(this, this, this.getMaximumPowerCapacity());
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
