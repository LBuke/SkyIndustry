package com.destinymc.ic2.recipe.workbench.shaped;

import com.destinymc.ic2.item.type.cable.InsulatedCopperCable;
import com.destinymc.ic2.item.type.electronic.ElectronicCircuit;
import com.destinymc.ic2.item.type.ingot.RefinedIronIngot;
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
public final class ElectronicCircuitWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public ElectronicCircuitWorkbenchRecipe() {
        super(new Recipe[]{
                new Recipe(
                        new IItem[]{
                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new InsulatedCopperCable()),

                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new RefinedIronIngot()),
                                new BaseItem(new ItemStack(Material.REDSTONE)),

                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new InsulatedCopperCable()),
                        },
                        new BaseItem(new ElectronicCircuit())
                ),
                new Recipe(
                        new IItem[]{
                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new InsulatedCopperCable()),

                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new RefinedIronIngot()),
                                new BaseItem(new InsulatedCopperCable()),

                                new BaseItem(new InsulatedCopperCable()),
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new InsulatedCopperCable()),
                        },
                        new BaseItem(new ElectronicCircuit())
                ),

        });
    }
}
