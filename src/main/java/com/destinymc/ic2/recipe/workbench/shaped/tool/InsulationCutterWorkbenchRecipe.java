package com.destinymc.ic2.recipe.workbench.shaped.tool;

import com.destinymc.ic2.item.type.plate.IronPlate;
import com.destinymc.ic2.item.type.tool.InsulationCutter;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulationCutterWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public InsulationCutterWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new IronPlate()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new IronPlate()),

                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new IronPlate()),
                        new BaseItem(new ItemStack(Material.AIR)),

                        new BaseItem(new ItemStack(Material.IRON_INGOT)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.IRON_INGOT)),
                },
                new BaseItem(new InsulationCutter())
        );
    }
}
