package com.destinymc.ic2.recipe.workbench.shaped.eu;

import com.destinymc.ic2.item.type.electronic.ElectronicCircuit;
import com.destinymc.ic2.item.type.eu.EnergyCrystal;
import com.destinymc.ic2.item.type.eu.LapotronCrystal;
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
public final class LapotronCrystalWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public LapotronCrystalWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new ItemStack(Material.INK_SACK, 1, (byte) 4)),
                        new BaseItem(new ElectronicCircuit()),
                        new BaseItem(new ItemStack(Material.INK_SACK, 1, (byte) 4)),

                        new BaseItem(new ItemStack(Material.INK_SACK, 1, (byte) 4)),
                        new BaseItem(new EnergyCrystal()),
                        new BaseItem(new ItemStack(Material.INK_SACK, 1, (byte) 4)),

                        new BaseItem(new ItemStack(Material.INK_SACK, 1, (byte) 4)),
                        new BaseItem(new ElectronicCircuit()),
                        new BaseItem(new ItemStack(Material.INK_SACK, 1, (byte) 4)),
                },
                new BaseItem(new LapotronCrystal())
        );
    }
}
