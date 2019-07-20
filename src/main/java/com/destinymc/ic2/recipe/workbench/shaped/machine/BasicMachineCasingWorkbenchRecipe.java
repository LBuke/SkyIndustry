package com.destinymc.ic2.recipe.workbench.shaped.machine;

import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
import com.destinymc.ic2.machine.item.BasicMachineCasing;
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
public final class BasicMachineCasingWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public BasicMachineCasingWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new RefinedIronIngot()),

                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new RefinedIronIngot()),

                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new RefinedIronIngot()),
                },
                new BaseItem(new BasicMachineCasing())
        );
    }
}
