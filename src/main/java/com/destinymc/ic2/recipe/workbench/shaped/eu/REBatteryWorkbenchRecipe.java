package com.destinymc.ic2.recipe.workbench.shaped.eu;

import com.destinymc.ic2.item.type.cable.InsulatedCopperCable;
import com.destinymc.ic2.item.type.eu.REBattery;
import com.destinymc.ic2.item.type.ingot.TinIngot;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 05/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class REBatteryWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public REBatteryWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new InsulatedCopperCable()),
                        new BaseItem(new ItemStack(Material.AIR)),

                        new BaseItem(new TinIngot()),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new TinIngot()),

                        new BaseItem(new TinIngot()),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new TinIngot()),
                },
                new BaseItem(new REBattery())
        );
    }
}
