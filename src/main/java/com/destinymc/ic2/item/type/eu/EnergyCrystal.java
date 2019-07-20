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
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class EnergyCrystal extends IC2Item implements ChangableItemState, PoweredItem {

    private static final int[] STATES = {191, 192, 193, 194, 195};

    private static final int POWER_CAP = 100000;
    private static final int INTAKE = 250, OUTTAKE = 250;

    public EnergyCrystal() {
        this(1);
    }

    public EnergyCrystal(int amount) {
        super(amount, 195, "Energy Crystal");

        ItemMeta meta = this.getItemMeta();
        meta.setLore(CollectionUtil.combineLists(
                Arrays.asList(
                        "{t=EnergyCrystal.lore;" + INTAKE + ";" + OUTTAKE + ";" + MachineUtil.getFormattedPower(POWER_CAP) + "}",
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
