package com.destinymc.ic2.recipe.workbench.shaped.machine;

import com.destinymc.ic2.item.type.cable.InsulatedCopperCable;
import com.destinymc.ic2.item.type.eu.REBattery;
import com.destinymc.ic2.machine.item.BatBox;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BatBoxWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public BatBoxWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.WOOD)),
                        new BaseItem(new InsulatedCopperCable()),
                        new BaseItem(new ItemStack(Material.WOOD)),

                        new BaseItem(new REBattery()),
                        new BaseItem(new REBattery()),
                        new BaseItem(new REBattery()),

                        new BaseItem(new ItemStack(Material.WOOD)),
                        new BaseItem(new ItemStack(Material.WOOD)),
                        new BaseItem(new ItemStack(Material.WOOD)),
                },
                new BaseItem(new BatBox())
        );
    }
}
