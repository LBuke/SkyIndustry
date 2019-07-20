package com.destinymc.ic2.recipe.macerator;

import com.destinymc.ic2.item.type.dust.CoalDust;
import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ClayDustMaceratorRecipe extends BaseMaceratorRecipe {

    public ClayDustMaceratorRecipe() {
        super(
                new IItem[] {
                        new BaseItem(new ItemStack(Material.CLAY))
                },

                new IItem[] {
                        new BaseItem(new ItemStack(Material.CLAY_BALL))
                }
        );
    }
}
