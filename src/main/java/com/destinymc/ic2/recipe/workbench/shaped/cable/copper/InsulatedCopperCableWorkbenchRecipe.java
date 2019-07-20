package com.destinymc.ic2.recipe.workbench.shaped.cable.copper;

import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.InsulatedCopperCable;
import com.destinymc.ic2.item.type.ingot.CopperIngot;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedCopperCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public InsulatedCopperCableWorkbenchRecipe() {
        super(new IItem[]{
                        new BaseItem(new Rubber()),
                        new BaseItem(new Rubber()),
                        new BaseItem(new Rubber()),
                        new BaseItem(new CopperIngot()),
                        new BaseItem(new CopperIngot()),
                        new BaseItem(new CopperIngot()),
                        new BaseItem(new Rubber()),
                        new BaseItem(new Rubber()),
                        new BaseItem(new Rubber()),
                },
                new BaseItem(new InsulatedCopperCable(6))
        );
    }
}
