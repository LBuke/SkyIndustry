package com.destinymc.ic2.recipe.type;

import com.destinymc.ic2.util.item.IItem;
import com.destinymc.util.Pair;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class BaseShapedCraftingRecipe extends BaseCraftingRecipe {

    public BaseShapedCraftingRecipe(IItem[] input, IItem output) {
        super(input, output);
    }

    public BaseShapedCraftingRecipe(Recipe[] recipes) {
        super(recipes);
    }

    @Override
    public Pair<? extends BaseCraftingRecipe, Recipe> get(ItemStack[] matrix) {
        for (Recipe recipe : recipes) {
            boolean failed = false;
            for (int index = 0; index < recipe.input.length; index++) {
                if (!recipe.input[index].isMatch(matrix[index])) {
                    failed = true;
                    break;
                }
            }

            if (!failed)
                return new Pair<>(this, recipe);
        }

        return null;
    }
}
