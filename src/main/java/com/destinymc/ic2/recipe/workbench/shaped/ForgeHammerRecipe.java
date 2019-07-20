package com.destinymc.ic2.recipe.workbench.shaped;

import com.destinymc.ic2.item.type.tool.ForgeHammer;
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
public final class ForgeHammerRecipe extends BaseShapedCraftingRecipe {

    public ForgeHammerRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.IRON_INGOT)),
                        new BaseItem(new ItemStack(Material.IRON_INGOT)),
                        new BaseItem(new ItemStack(Material.IRON_INGOT)),

                        new BaseItem(new ItemStack(Material.IRON_INGOT)),
                        new BaseItem(new ItemStack(Material.STICK)),
                        new BaseItem(new ItemStack(Material.IRON_INGOT)),

                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new ItemStack(Material.STICK)),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new ForgeHammer())
        );
    }
}
