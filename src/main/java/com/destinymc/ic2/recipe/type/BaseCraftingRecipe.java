package com.destinymc.ic2.recipe.type;

import com.destinymc.ic2.util.item.IItem;
import com.destinymc.util.Pair;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class BaseCraftingRecipe extends BaseRecipe {

    public BaseCraftingRecipe(IItem[] input, IItem output) {
        super(input, output);
    }

    public BaseCraftingRecipe(Recipe[] recipes) {
        super(recipes);
    }

    public IItem[] getInput(Recipe recipe) {
        return recipe.input;
    }

    public IItem getOutput(Recipe recipe) {
        return recipe.output[0];
    }

    public abstract Pair<? extends BaseCraftingRecipe, Recipe> get(ItemStack[] matrix);
}
