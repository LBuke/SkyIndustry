package com.destinymc.ic2.recipe.workbench.shaped.cable.gold;

import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.InsulatedGoldCable;
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
public final class InsulatedGoldCableWorkbenchRecipe extends BaseShapedCraftingRecipe {

    public InsulatedGoldCableWorkbenchRecipe() {
        super(new IItem[]{
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.GOLD_INGOT)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.AIR)),
                        new BaseItem(new Rubber()),
                        new BaseItem(new ItemStack(Material.AIR)),
                },
                new BaseItem(new InsulatedGoldCable(4))
        );
    }
}
