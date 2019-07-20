package com.destinymc.ic2.recipe.workbench.shaped.cable.gold;

import com.destinymc.ic2.item.type.cable.GoldCable;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class GoldCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public GoldCableWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.GOLD_INGOT)),
                        new BaseItem(new ItemStack(Material.GOLD_INGOT)),
                        new BaseItem(new ItemStack(Material.GOLD_INGOT)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new GoldCable(12))
        );
    }
}
