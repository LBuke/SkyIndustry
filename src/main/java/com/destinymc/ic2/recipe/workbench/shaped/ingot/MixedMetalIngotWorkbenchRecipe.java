package com.destinymc.ic2.recipe.workbench.shaped.ingot;

import com.destinymc.ic2.item.type.ingot.MixedMetalIngot;
import com.destinymc.ic2.item.type.plate.BronzePlate;
import com.destinymc.ic2.item.type.plate.IronPlate;
import com.destinymc.ic2.item.type.plate.TinPlate;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class MixedMetalIngotWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public MixedMetalIngotWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new IronPlate()),
                        new BaseItem(new IronPlate()),
                        new BaseItem(new IronPlate()),

                        new BaseItem(new BronzePlate()),
                        new BaseItem(new BronzePlate()),
                        new BaseItem(new BronzePlate()),

                        new BaseItem(new TinPlate()),
                        new BaseItem(new TinPlate()),
                        new BaseItem(new TinPlate()),
                },
                new BaseItem(new MixedMetalIngot(2))
        );
    }
}
