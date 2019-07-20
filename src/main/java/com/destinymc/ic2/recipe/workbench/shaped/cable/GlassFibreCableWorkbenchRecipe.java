package com.destinymc.ic2.recipe.workbench.shaped.cable;

import com.destinymc.ic2.item.type.cable.GlassFibreCable;
import com.destinymc.ic2.item.type.ingot.SilverIngot;
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
public final class GlassFibreCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public GlassFibreCableWorkbenchRecipe() {
        super(new Recipe[]{
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new ItemStack(Material.DIAMOND)),
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                        },
                        new BaseItem(new GlassFibreCable(4))
                ),
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new SilverIngot()),
                                new BaseItem(new ItemStack(Material.DIAMOND)),
                                new BaseItem(new SilverIngot()),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                                new BaseItem(new ItemStack(Material.GLASS)),
                        },
                        new BaseItem(new GlassFibreCable(6))
                ),
        });
    }
}
