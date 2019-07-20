package com.destinymc.ic2.recipe.workbench.shaped.cable.copper;

import com.destinymc.ic2.item.type.cable.CopperCable;
import com.destinymc.ic2.item.type.ingot.CopperIngot;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class CopperCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public CopperCableWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new CopperIngot()),
                        new BaseItem(new CopperIngot()),
                        new BaseItem(new CopperIngot()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new CopperCable(6))
        );
    }
}
