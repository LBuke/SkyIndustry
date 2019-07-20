package com.destinymc.ic2.recipe.type;

import com.destinymc.ic2.util.item.IItem;

public abstract class BaseMaceratorRecipe extends BaseRecipe {

    private final IItem[] output;
    private final IItem input;

    public BaseMaceratorRecipe(IItem[] input, IItem[] output) {
        super(input, output);
        this.output = output;
        this.input = input[0];
    }

    public IItem[] getOutput() {
        return output;
    }

    public IItem getInput() {
        return input;
    }
}
