package com.destinymc.ic2.guide.recipe;

import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.ic2.util.item.IItem;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public final class DummyMaceratorRecipe extends DummyRecipe {

    private final IItem input;
    private final IItem[] result;

    private DummyMaceratorRecipe(IItem input, IItem[] result) {
        this.input = input;
        this.result = result;
    }

    private DummyMaceratorRecipe(BaseMaceratorRecipe maceratorRecipe) {
        this(maceratorRecipe.getInput(), maceratorRecipe.getOutput());
    }

    public IItem getInput() {
        return input;
    }

    public IItem[] getResult() {
        return result;
    }

    public static List<DummyMaceratorRecipe> convertIC2(Collection<BaseRecipe.Recipe> recipes) {
        List<DummyMaceratorRecipe> list = Lists.newArrayList();
        for (BaseRecipe.Recipe r : recipes) {
            list.add(new DummyMaceratorRecipe((BaseMaceratorRecipe) r.getBaseRecipe()));
        }

        return list;
    }
}
