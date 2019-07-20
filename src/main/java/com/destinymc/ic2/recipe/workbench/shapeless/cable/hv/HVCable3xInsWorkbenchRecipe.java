package com.destinymc.ic2.recipe.workbench.shapeless.cable.hv;

import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.HVCable;
import com.destinymc.ic2.item.type.cable.HVCable2xIns;
import com.destinymc.ic2.item.type.cable.HVCable3xIns;
import com.destinymc.ic2.item.type.cable.InsulatedHVCable;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class HVCable3xInsWorkbenchRecipe extends BaseShapelessCraftingRecipe {

    public HVCable3xInsWorkbenchRecipe() {
        super(new Recipe[]{
                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new Rubber()),
                                        new BaseItem(new Rubber()),
                                        new BaseItem(new Rubber()),
                                        new BaseItem(new HVCable()),
                                },
                                new BaseItem(new HVCable3xIns(1))
                        ),
                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new Rubber()),
                                        new BaseItem(new Rubber()),
                                        new BaseItem(new InsulatedHVCable()),
                                },
                                new BaseItem(new HVCable3xIns(1))
                        ),
                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new Rubber()),
                                        new BaseItem(new HVCable2xIns()),
                                },
                                new BaseItem(new HVCable3xIns(1))
                        )
                }
        );
    }
}
