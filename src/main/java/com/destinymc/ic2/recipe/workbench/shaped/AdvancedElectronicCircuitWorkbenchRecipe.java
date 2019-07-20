package com.destinymc.ic2.recipe.workbench.shaped;

import com.destinymc.ic2.item.type.electronic.AdvancedElectronicCircuit;
import com.destinymc.ic2.item.type.electronic.ElectronicCircuit;
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
public final class AdvancedElectronicCircuitWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public AdvancedElectronicCircuitWorkbenchRecipe() {
        super(new Recipe[]{
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new ItemStack(Material.GLOWSTONE_DUST)),
                                new BaseItem(new ItemStack(Material.REDSTONE)),

                                new BaseItem(new ItemStack(Material.INK_SACK, (byte) 4)),
                                new BaseItem(new ElectronicCircuit()),
                                new BaseItem(new ItemStack(Material.INK_SACK, (byte) 4)),

                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new ItemStack(Material.GLOWSTONE_DUST)),
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                        },
                        new BaseItem(new AdvancedElectronicCircuit())
                ),
                new Recipe(
                        new IItem[]{
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new ItemStack(Material.INK_SACK, (byte) 4)),
                                new BaseItem(new ItemStack(Material.REDSTONE)),

                                new BaseItem(new ItemStack(Material.GLOWSTONE_DUST)),
                                new BaseItem(new ElectronicCircuit()),
                                new BaseItem(new ItemStack(Material.GLOWSTONE_DUST)),

                                new BaseItem(new ItemStack(Material.REDSTONE)),
                                new BaseItem(new ItemStack(Material.INK_SACK, (byte) 4)),
                                new BaseItem(new ItemStack(Material.REDSTONE)),
                        },
                        new BaseItem(new AdvancedElectronicCircuit())
                ),

        });
    }
}
