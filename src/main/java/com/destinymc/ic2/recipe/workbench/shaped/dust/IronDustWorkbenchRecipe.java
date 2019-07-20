package com.destinymc.ic2.recipe.workbench.shaped.dust;

import com.destinymc.ic2.item.type.tool.InsulationCutter;
import com.destinymc.ic2.recipe.RecipeUnfinished;
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
@RecipeUnfinished
public final class IronDustWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public IronDustWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),//Copper dust
                        new BaseItem(new ItemStack(Material.AIR)),//Tin dust
                        new BaseItem(new ItemStack(Material.AIR)),//Copper dust

                        new BaseItem(new ItemStack(Material.AIR)),//Tin dust
                        new BaseItem(new ItemStack(Material.AIR)),//Copper dust
                        new BaseItem(new ItemStack(Material.AIR)),//Tin dust

                        new BaseItem(new ItemStack(Material.AIR)),//Copper dust
                        new BaseItem(new ItemStack(Material.AIR)),//Tin dust
                        new BaseItem(new ItemStack(Material.AIR)),//Copper dust
                },
                new BaseItem(new InsulationCutter())//Iron Dust
        );
    }
}
