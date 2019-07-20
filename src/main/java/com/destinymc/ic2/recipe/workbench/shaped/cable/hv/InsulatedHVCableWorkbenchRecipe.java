package com.destinymc.ic2.recipe.workbench.shaped.cable.hv;

import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.InsulatedHVCable;
import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
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
public final class InsulatedHVCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public InsulatedHVCableWorkbenchRecipe() {
        super(new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new InsulatedHVCable(4))
        );
    }
}
