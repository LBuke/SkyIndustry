package com.destinymc.ic2.recipe.type;

import com.destinymc.ic2.util.item.IItem;
import com.destinymc.util.Pair;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created at 19/01/2019
  * <p>
 * @author Luke Bingham
 */
public abstract class BaseShapelessCraftingRecipe extends BaseCraftingRecipe {

    public BaseShapelessCraftingRecipe(IItem[] input, IItem output) {
        super(input, output);
    }

    public BaseShapelessCraftingRecipe(Recipe[] recipes) {
        super(recipes);
    }

    private int findMatch(Recipe recipe, int index, ItemStack[] matrix, List<Integer> ignore) {
        for (int i = 0; i < matrix.length; i++) {
            if (ignore != null && !ignore.isEmpty() && ignore.contains(i))
                continue;

            if (matrix[i] == null || matrix[i].getType() == Material.AIR)
                continue;

            if (!recipe.input[index].isMatch(matrix[i]))
                continue;

            return i;
        }

        return -1;
    }

    @Override
    public Pair<? extends BaseCraftingRecipe, Recipe> get(ItemStack[] matrix) {
        List<Integer> ignore = Lists.newArrayList();
        for (Recipe recipe : recipes) {
            ignore.clear();

            int x = 0;
            for (int index = 0; index < recipe.input.length; index++) {
                int i = this.findMatch(recipe, index, matrix, ignore);

                if (i == -1)
                    break;

                ignore.add(i);

                x += 1;
                if (x == recipe.input.length && ignore.size() == recipe.input.length) {
                    return new Pair<>(this, recipe);
                }
            }
        }

        return null;
    }
}
