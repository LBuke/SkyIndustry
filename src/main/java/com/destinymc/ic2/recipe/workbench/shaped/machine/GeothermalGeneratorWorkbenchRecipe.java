package com.destinymc.ic2.recipe.workbench.shaped.machine;

import com.destinymc.ic2.item.type.cell.EmptyCell;
import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
import com.destinymc.ic2.machine.item.Generator;
import com.destinymc.ic2.machine.item.GeothermalGenerator;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class GeothermalGeneratorWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public GeothermalGeneratorWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.GLASS)),
                        new BaseItem(new EmptyCell()),
                        new BaseItem(new ItemStack(Material.GLASS)),

                        new BaseItem(new ItemStack(Material.GLASS)),
                        new BaseItem(new EmptyCell()),
                        new BaseItem(new ItemStack(Material.GLASS)),

                        new BaseItem(new RefinedIronIngot()),
                        new BaseItem(new Generator()),
                        new BaseItem(new RefinedIronIngot()),
                },
                new BaseItem(new GeothermalGenerator())
        );
    }
}
