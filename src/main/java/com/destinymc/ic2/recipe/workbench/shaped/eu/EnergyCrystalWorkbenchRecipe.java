package com.destinymc.ic2.recipe.workbench.shaped.eu;

import com.destinymc.ic2.item.type.eu.EnergyCrystal;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class EnergyCrystalWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public EnergyCrystalWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),

                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.DIAMOND)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),

                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                },
                new BaseItem(new EnergyCrystal())
        );
    }
}
