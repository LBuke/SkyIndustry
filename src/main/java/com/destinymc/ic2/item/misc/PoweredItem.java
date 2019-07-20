package com.destinymc.ic2.item.misc;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.util.MachineUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface PoweredItem extends ItemAttribute {

    public static final String META = "IC2POWER";

    int getMaximumPowerCapacity();

    int getPowerIntake();

    int getPowerOuttake();

    default boolean isPowerEmpty(ItemStack itemStack) {
        return ItemFactory.getLongNBTTag(itemStack, META) <= 0;
    }

    default boolean isPowerFull(ItemStack itemStack) {
        return ItemFactory.getLongNBTTag(itemStack, META) >= this.getMaximumPowerCapacity();
    }

    default long getPower(ItemStack itemStack, IC2Item ic2Item) {
        long power = ItemFactory.getLongNBTTag(itemStack, META);
        if (power < 0) {
            this.setPower(itemStack, ic2Item, 0);
            return 0;
        }

        if (power > this.getMaximumPowerCapacity()) {
            this.setPower(itemStack, ic2Item, this.getMaximumPowerCapacity());
            return this.getMaximumPowerCapacity();
        }

        return power;
    }

    default void setPower(ItemStack itemStack, IC2Item ic2Item, long power) {
        if (power > this.getMaximumPowerCapacity()) power = this.getMaximumPowerCapacity();
        if (power < 0) power = 0;

        ItemStack item = ItemFactory.setNBTTag(itemStack, META, power);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(meta.getDisplayName());
//        meta.setDisplayName(String.format(ic2Item.getName(), MachineUtil.getFormattedPower(power)));
        itemStack.setItemMeta(meta);

        if (ic2Item instanceof ChangableItemState) {
            ChangableItemState changableItemState = (ChangableItemState) ic2Item;
            int state = changableItemState.getState(itemStack);

            double value = power, goal = this.getMaximumPowerCapacity();
            int textures = changableItemState.getAmountOfStates() - 1;
            int requiredState = (int) Math.round(value >= goal ? textures : textures - Math.abs((goal - value) / goal * textures));

            if (state != requiredState) {
                changableItemState.setState(itemStack, requiredState);
            }
        }
    }
}
