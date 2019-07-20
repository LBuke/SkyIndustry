package com.destinymc.ic2.recipe.workbench.shapeless.eu;

import com.destinymc.ic2.item.type.cable.InsulatedCopperCable;
import com.destinymc.ic2.item.type.dust.CoalDust;
import com.destinymc.ic2.item.type.eu.SingleUseBattery;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class SingleUseBatteryWorkbenchRecipe extends BaseShapelessCraftingRecipe {

    public SingleUseBatteryWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new InsulatedCopperCable()),
                        new BaseItem(new CoalDust()),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                },
                new BaseItem(new SingleUseBattery(5))
        );
    }
}
