package com.destinymc.ic2.recipe.workbench.shaped.machine;

import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
import com.destinymc.ic2.machine.item.BasicMachineCasing;
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
public final class MachineBlockWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public MachineBlockWorkbenchRecipe() {
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

    /**
     * INPUT:
     *   Refined Iron Ingot
     *   Refined Iron Ingot
     *   Refined Iron Ingot
     *   Refined Iron Ingot
     *   null
     *   Refined Iron Ingot
     *   Refined Iron Ingot
     *   Refined Iron Ingot
     *   Refined Iron Ingot
     *
     * OUTPUT:
     *   Machine Block
     */
}
