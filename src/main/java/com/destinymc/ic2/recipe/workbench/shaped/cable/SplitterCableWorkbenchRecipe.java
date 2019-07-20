package com.destinymc.ic2.recipe.workbench.shaped.cable;

import com.destinymc.ic2.item.type.cable.HVCable3xIns;
import com.destinymc.ic2.item.type.cable.SplitterCable;
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
public final class SplitterCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public SplitterCableWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new HVCable3xIns()),
                        new BaseItem(new ItemStack(Material.LEVER)),
                        new BaseItem(new HVCable3xIns()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.REDSTONE)),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new SplitterCable(1))
        );
    }
}
