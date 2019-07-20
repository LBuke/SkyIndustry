package com.destinymc.ic2.util.item;

import com.destinymc.ic2.item.IC2Item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Collection;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface IItem {

    ItemStack getItemStack();

    IC2Item getIc2Item();

    ItemType getItemType();

    boolean isMatch(ItemStack var);

    ItemStack toItem();

    default String getDisplayName() {
        if (getItemType() == ItemType.VANILLA) {
            return toItem().getType().name();
        }

        if (getItemType() == ItemType.IC2)
            return getIc2Item().getName();

        return "Unknown";
    }

    public static IItem[] toIItemArray(Collection<ItemStack> itemStackCollection) {
        IItem[] array = new IItem[itemStackCollection.size()];
        int index = 0;
        for (ItemStack itemStack : itemStackCollection) {
            array[index] = new BaseItem(sanitiseItemStack(itemStack));
            index += 1;
        }

        return array;
    }

    static ItemStack sanitiseItemStack(ItemStack brokenStack) {
        if (brokenStack != null && brokenStack.getType() != null) {
            if (brokenStack.getData() != null && brokenStack.getData().getData() < 0) {
                ItemStack newItem = new ItemStack(brokenStack.getType());
                newItem.setData(brokenStack.getData());
                newItem.setAmount(brokenStack.getAmount());
                return newItem;
            }
            return brokenStack;
        } else {
            return new ItemStack(Material.AIR);
        }
    }

    public static IItem[] correctShapedRecipe(ShapedRecipe shapedRecipe) {
        IItem[] array = new IItem[9];
        String[] shape = shapedRecipe.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int x = 0; x < shape[i].length(); x++) {
                ItemStack ingredient = sanitiseItemStack(shapedRecipe.getIngredientMap().get(shape[i].toCharArray()[x]));
                array[i * 3 + x] = new BaseItem(ingredient);
            }
        }

        return array;
    }
}
