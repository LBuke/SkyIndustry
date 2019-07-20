package com.destinymc.ic2.recipe.workbench.shapeless.cable;

import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.CopperCable;
import com.destinymc.ic2.item.type.cable.InsulatedCopperCable;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedCopperCableWorkbenchRecipe extends BaseShapelessCraftingRecipe {

    public InsulatedCopperCableWorkbenchRecipe() {
        super(new IItem[]{
                        new BaseItem(new Rubber()),
                        new BaseItem(new CopperCable()),
                },
                new BaseItem(new InsulatedCopperCable(1))
        );
    }
}
