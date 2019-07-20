package com.destinymc.ic2.recipe.workbench.shaped.machine;

import com.destinymc.ic2.item.type.dust.CoalDust;
import com.destinymc.ic2.item.type.electronic.ElectronicCircuit;
import com.destinymc.ic2.machine.item.Generator;
import com.destinymc.ic2.machine.item.SolarPanel;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class MachineSolarPanelWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public MachineSolarPanelWorkbenchRecipe() {
        super(
                new IItem[]{
                        new BaseItem(new CoalDust()),
                        new BaseItem(new ItemStack(Material.GLASS)),
                        new BaseItem(new CoalDust()),

                        new BaseItem(new ItemStack(Material.GLASS)),
                        new BaseItem(new CoalDust()),
                        new BaseItem(new ItemStack(Material.GLASS)),

                        new BaseItem(new ElectronicCircuit()),
                        new BaseItem(new Generator()),
                        new BaseItem(new ElectronicCircuit()),
                },
                new BaseItem(new SolarPanel())
        );
    }
}
