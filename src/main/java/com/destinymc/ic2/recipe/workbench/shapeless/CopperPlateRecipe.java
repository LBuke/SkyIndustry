package com.destinymc.ic2.recipe.workbench.shapeless;

import com.destinymc.ic2.item.type.ingot.CopperIngot;
import com.destinymc.ic2.item.type.plate.CopperPlate;
import com.destinymc.ic2.item.type.tool.ForgeHammer;
import com.destinymc.ic2.recipe.RecipeUnfinished;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
@RecipeUnfinished
public final class CopperPlateRecipe extends BaseShapelessCraftingRecipe {

    public CopperPlateRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ForgeHammer()),
                        new BaseItem(new CopperIngot())
                },
                new BaseItem(new CopperPlate())
        );
    }
}
