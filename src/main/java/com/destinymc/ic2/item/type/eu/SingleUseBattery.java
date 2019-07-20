package com.destinymc.ic2.item.type.eu;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.misc.PoweredItem;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.locale.I18n;
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
public final class SingleUseBattery extends IC2Item implements PoweredItem {

    private static final int POWER_CAP = 1200;
    private static final int INTAKE = 32, OUTTAKE = 32;

    public SingleUseBattery() {
        this(1);
    }

    public SingleUseBattery(int amount) {
        super(amount, 206, "Battery");

        ItemMeta meta = this.getItemMeta();
        meta.setLore(CollectionUtil.combineLists(
                Arrays.asList(
                        "{t=Battery.lore;" + INTAKE + ";" + OUTTAKE + ";" + MachineUtil.getFormattedPower(POWER_CAP) + "}",
                        " "
                ),
                meta.getLore())
        );
        this.setItemMeta(meta);

        this.setPower(this, this, 0);
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
