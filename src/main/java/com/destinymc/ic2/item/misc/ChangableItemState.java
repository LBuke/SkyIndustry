package com.destinymc.ic2.item.misc;

import com.destinymc.factory.ItemFactory;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface ChangableItemState extends ItemAttribute {

    String META = "IC2STATE";

    default int getAmountOfStates() {
        return getAllStates().length;
    }

    int[] getAllStates();

    default int getState(ItemStack itemStack) {
        int state = ItemFactory.getIntNBTTag(itemStack, META);

        if (state < 0) {
            state = 0;
            this.setState(itemStack, state);
            return state;
        }

        if (state > getAllStates().length - 1) {
            state = getAllStates().length - 1;
            this.setState(itemStack, state);
            return state;
        }

        return getAllStates()[state];
    }

    default void setState(ItemStack itemStack, int state) {
        ItemStack item = ItemFactory.setNBTTag(itemStack, META, state);
        itemStack.setItemMeta(item.getItemMeta());
        itemStack.setDurability((short) getAllStates()[state]);
    }
}
