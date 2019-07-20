package com.destinymc.ic2.recipe.workbench.shapeless.ingot;

import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
import com.destinymc.ic2.machine.item.BasicMachineCasing;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class RefinedIronWorkbenchRecipe extends BaseShapelessCraftingRecipe {

    public RefinedIronWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new BasicMachineCasing())
                },
                new BaseItem(new RefinedIronIngot(8))
        );
    }
}
