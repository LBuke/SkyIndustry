package com.destinymc.ic2.recipe.macerator;

import com.destinymc.ic2.item.type.dust.CoalDust;
import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class CoalDustMaceratorRecipe extends BaseMaceratorRecipe {

    public CoalDustMaceratorRecipe() {
        super(
                new IItem[] {
                        new BaseItem(new ItemStack(Material.COAL))
                },

                new IItem[] {
                        new BaseItem(new CoalDust())
                }
        );
    }
}
