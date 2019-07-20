package com.destinymc.ic2.recipe.workbench.shaped.machine;

import com.destinymc.ic2.item.type.eu.REBattery;
import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
import com.destinymc.ic2.machine.item.BasicMachineCasing;
import com.destinymc.ic2.machine.item.Generator;
import com.destinymc.ic2.recipe.RecipeUnfinished;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RecipeUnfinished
public final class GeneratorWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public GeneratorWorkbenchRecipe() {
        super(
                new Recipe[]{
                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new REBattery()),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),

                                        new BaseItem(new BasicMachineCasing()),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),

                                        new BaseItem(new ItemStack(Material.FURNACE)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                },
                                new BaseItem(new Generator())
                        ),

                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new REBattery()),
                                        new BaseItem(new ItemStack(Material.AIR)),

                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new BasicMachineCasing()),
                                        new BaseItem(new ItemStack(Material.AIR)),

                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.FURNACE)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                },
                                new BaseItem(new Generator())
                        ),

                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new REBattery()),

                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new BasicMachineCasing()),

                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.FURNACE)),
                                },
                                new BaseItem(new Generator())
                        ),

                        new Recipe(
                                new IItem[]{
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new REBattery()),

                                        new BaseItem(new RefinedIronIngot()),
                                        new BaseItem(new RefinedIronIngot()),
                                        new BaseItem(new RefinedIronIngot()),

                                        new BaseItem(new ItemStack(Material.AIR)),
                                        new BaseItem(new ItemStack(Material.AIR)),//Iron Furnace
                                        new BaseItem(new ItemStack(Material.AIR)),
                                },
                                new BaseItem(new Generator())
                        ),
                }
        );
    }
}
