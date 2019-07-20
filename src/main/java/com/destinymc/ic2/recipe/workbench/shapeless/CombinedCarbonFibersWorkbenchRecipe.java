package com.destinymc.ic2.recipe.workbench.shapeless;

import com.destinymc.ic2.item.type.carbon.CarbonFibers;
import com.destinymc.ic2.item.type.carbon.CombinedCarbonFibers;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class CombinedCarbonFibersWorkbenchRecipe extends BaseShapelessCraftingRecipe {

    public CombinedCarbonFibersWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new CarbonFibers()),
                        new BaseItem(new CarbonFibers())
                },
                new BaseItem(new CombinedCarbonFibers())
        );
    }
}
