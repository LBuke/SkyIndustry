package com.destinymc.ic2.recipe.workbench.shaped;

import com.destinymc.ic2.item.type.carbon.CarbonFibers;
import com.destinymc.ic2.item.type.dust.CoalDust;
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
public final class CarbonFibersWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public CarbonFibersWorkbenchRecipe() {
        super(new Recipe[]{

                //TOP LEFT SQUARE
                new Recipe(
                        new IItem[]{
                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new ItemStack(Material.AIR)),

                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new ItemStack(Material.AIR)),

                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),
                        },
                        new BaseItem(new CarbonFibers())
                ),

                //BOTTOM LEFT SQUARE
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),

                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new ItemStack(Material.AIR)),

                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new ItemStack(Material.AIR)),
                        },
                        new BaseItem(new CarbonFibers())
                ),

                //TOP RIGHT SQUARE
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),

                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),

                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),
                        },
                        new BaseItem(new CarbonFibers())
                ),

                //BOTTOM RIGHT SQUARE
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new ItemStack(Material.AIR)),

                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),

                                new BaseItem(new ItemStack(Material.AIR)),
                                new BaseItem(new CoalDust()),
                                new BaseItem(new CoalDust()),
                        },
                        new BaseItem(new CarbonFibers())
                ),

        });
    }
}
