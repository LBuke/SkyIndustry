package com.destinymc.ic2.guide.recipe;

import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import com.google.common.collect.Lists;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Collection;
import java.util.List;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class DummyFurnaceRecipe extends DummyRecipe {

    private final IItem input;
    private final IItem result;

    private final RecipeType recipeType;

    private DummyFurnaceRecipe(IItem input, IItem result, RecipeType recipeType) {
        this.input = input;
        this.result = result;
        this.recipeType = recipeType;
    }

    private DummyFurnaceRecipe(FurnaceRecipe furnaceRecipe) {
        this(new BaseItem(new ItemStack(furnaceRecipe.getInput().getType())), new BaseItem(furnaceRecipe.getResult()), RecipeType.MINECRAFT);
    }

    public IItem getInput() {
        return input;
    }

    public IItem getResult() {
        return result;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public enum RecipeType {
        MINECRAFT, IC2,;
    }

    public static List<DummyFurnaceRecipe> convertMC(Collection<Recipe> recipes) {
        List<DummyFurnaceRecipe> list = Lists.newArrayList();

        for (Recipe r : recipes) {
            if (r instanceof FurnaceRecipe) {
                list.add(new DummyFurnaceRecipe((FurnaceRecipe) r));
            }
        }

        return list;
    }
}
