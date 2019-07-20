package com.destinymc.ic2.guide.recipe;

import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.ic2.recipe.type.BaseShapedCraftingRecipe;
import com.destinymc.ic2.recipe.type.BaseShapelessCraftingRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import com.google.common.collect.Lists;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Collection;
import java.util.List;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class DummyWorkbenchRecipe extends DummyRecipe {

    private final IItem[] input;
    private final IItem result;

    private final RecipeType recipeType;

    private DummyWorkbenchRecipe(IItem[] input, IItem result, RecipeType recipeType) {
        this.input = input;
        this.result = result;
        this.recipeType = recipeType;
    }

    private DummyWorkbenchRecipe(BaseShapelessCraftingRecipe craftingRecipe, BaseRecipe.Recipe recipe) {
        this(craftingRecipe.getInput(recipe), craftingRecipe.getOutput(recipe), RecipeType.IC2_SHAPELESS);
    }

    private DummyWorkbenchRecipe(BaseShapedCraftingRecipe craftingRecipe, BaseRecipe.Recipe recipe) {
        this(craftingRecipe.getInput(recipe), craftingRecipe.getOutput(recipe), RecipeType.IC2_SHAPED);
    }

    private DummyWorkbenchRecipe(ShapedRecipe craftingRecipe) {
        this(IItem.correctShapedRecipe(craftingRecipe), new BaseItem(craftingRecipe.getResult()), RecipeType.MINECRAFT_SHAPED);
    }

    private DummyWorkbenchRecipe(ShapelessRecipe craftingRecipe) {
        this(IItem.toIItemArray(craftingRecipe.getIngredientList()), new BaseItem(craftingRecipe.getResult()), RecipeType.MINECRAFT_SHAPELESS);
    }

    public IItem[] getInput() {
        return input;
    }

    public IItem getResult() {
        return result;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public enum RecipeType {
        MINECRAFT_SHAPELESS,
        MINECRAFT_SHAPED,

        IC2_SHAPELESS,
        IC2_SHAPED,;
    }

    public static List<DummyWorkbenchRecipe> convertMC(Collection<Recipe> recipes) {
        List<DummyWorkbenchRecipe> list = Lists.newArrayList();
        for (Recipe r : recipes) {
            if (r instanceof ShapedRecipe) {
                list.add(new DummyWorkbenchRecipe((ShapedRecipe) r));
            }

            if (r instanceof ShapelessRecipe) {
                list.add(new DummyWorkbenchRecipe((ShapelessRecipe) r));
            }
        }

        return list;
    }

    public static List<DummyWorkbenchRecipe> convertIC2(Collection<BaseRecipe.Recipe> recipes) {
        List<DummyWorkbenchRecipe> list = Lists.newArrayList();

        for (BaseRecipe.Recipe r : recipes) {
            if (r.getBaseRecipe() instanceof BaseShapedCraftingRecipe) {
                list.add(new DummyWorkbenchRecipe((BaseShapedCraftingRecipe) r.getBaseRecipe(), r));
            }

            if (r.getBaseRecipe() instanceof BaseShapelessCraftingRecipe) {
                list.add(new DummyWorkbenchRecipe((BaseShapelessCraftingRecipe) r.getBaseRecipe(), r));
            }
        }

        return list;
    }
}
