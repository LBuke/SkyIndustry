package com.destinymc.ic2.recipe.type;

import com.destinymc.ic2.util.item.IItem;

import java.util.Arrays;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class BaseRecipe {

    protected final Recipe[] recipes;

    public BaseRecipe(Recipe[] recipes) {
        this.recipes = recipes;
        Arrays.asList(recipes).forEach(recipe -> recipe.setBaseRecipe(this));
    }

    public BaseRecipe(IItem[] input, IItem[] output) {
        this.recipes = new Recipe[]{new Recipe(input, output).setBaseRecipe(this)};
    }

    public BaseRecipe(IItem[] input, IItem output) {
        this.recipes = new Recipe[]{new Recipe(input, output).setBaseRecipe(this)};
    }

    public Recipe[] getInnerRecipes() {
        return recipes;
    }

    public static class Recipe {
        private BaseRecipe baseRecipe;

        protected final IItem[] input;
        protected final IItem[] output;

        public Recipe(IItem[] input, IItem[] output) {
            this.input = input;
            this.output = output;
        }

        public Recipe(IItem[] input, IItem output) {
            this(input, new IItem[]{output});
        }

        public BaseRecipe getBaseRecipe() {
            return baseRecipe;
        }

        private Recipe setBaseRecipe(BaseRecipe baseRecipe) {
            this.baseRecipe = baseRecipe;
            return this;
        }

        public IItem[] getOutput() {
            return output;
        }

        public IItem[] getInput() {
            return input;
        }
    }
}
