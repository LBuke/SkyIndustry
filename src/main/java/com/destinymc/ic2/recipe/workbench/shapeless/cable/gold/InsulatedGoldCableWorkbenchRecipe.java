package com.destinymc.ic2.recipe.workbench.shapeless.cable.gold;

import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.GoldCable;
import com.destinymc.ic2.item.type.cable.InsulatedGoldCable;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class InsulatedGoldCableWorkbenchRecipe extends BaseShapelessCraftingRecipe {

    public InsulatedGoldCableWorkbenchRecipe() {
        super(new IItem[]{
                        new BaseItem(new Rubber()),
                        new BaseItem(new GoldCable()),
                },
                new BaseItem(new InsulatedGoldCable(1))
        );
    }
}
