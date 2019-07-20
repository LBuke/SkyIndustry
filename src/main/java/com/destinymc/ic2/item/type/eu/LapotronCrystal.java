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
public final class LapotronCrystal extends IC2Item implements ChangableItemState, PoweredItem {

    private static final int[] STATES = {196, 197, 198, 199, 200};

    private static final int POWER_CAP = 1000000;
    private static final int INTAKE = 1024, OUTTAKE = 1024;

    public LapotronCrystal() {
        this(1);
    }

    public LapotronCrystal(int amount) {
        super(amount, 200, "Lapotron Crystal");

        ItemMeta meta = this.getItemMeta();
        meta.setLore(CollectionUtil.combineLists(
                Arrays.asList(
                        "{t=LapotronCrystal.lore;" + INTAKE + ";" + OUTTAKE + ";" + MachineUtil.getFormattedPower(POWER_CAP) + "}",
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
