package com.destinymc.ic2.recipe.workbench.shaped.cable;

import com.destinymc.ic2.item.type.cable.TinCable;
import com.destinymc.ic2.item.type.ingot.TinIngot;
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
public final class TinCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public TinCableWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new TinIngot()),
                        new BaseItem(new TinIngot()),
                        new BaseItem(new TinIngot()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new TinCable(9))
        );
    }
}
