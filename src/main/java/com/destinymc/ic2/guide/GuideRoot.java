package com.destinymc.ic2.guide;

import com.destinymc.ic2.guide.menu.GuideFurnaceMenu;
import com.destinymc.ic2.guide.menu.GuideMaceratorMenu;
import com.destinymc.ic2.guide.menu.GuideWorkbenchMenu;
import com.destinymc.ic2.guide.recipe.DummyFurnaceRecipe;
import com.destinymc.ic2.guide.recipe.DummyMaceratorRecipe;
import com.destinymc.ic2.guide.recipe.DummyWorkbenchRecipe;
import com.destinymc.ic2.recipe.RecipeManager;
import com.destinymc.ic2.util.item.IItem;
import com.destinymc.ic2.util.item.ItemType;
import com.destinymc.inventory.BaseMenu;
import com.destinymc.util.ArrayUtil;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class GuideRoot {

    private IItem item;

    private DummyFurnaceRecipe[] furnaceRecipes;
    private DummyWorkbenchRecipe[] workbenchRecipes;
    private DummyMaceratorRecipe[] maceratorRecipes;

    private GuideType[] guideTypes = null;
    private GuideType defaultType = null;

    public GuideRoot(RecipeManager recipeManager, IItem item) {
        this.item = item;

        // WORKBENCH
        List<DummyWorkbenchRecipe> workbench = Lists.newArrayList();
        if (item.getItemType() == ItemType.VANILLA) workbench.addAll(DummyWorkbenchRecipe.convertMC(Bukkit.getRecipesFor(item.toItem())));
        if (item.getItemType() == ItemType.IC2) workbench.addAll(DummyWorkbenchRecipe.convertIC2(recipeManager.getWorkbenchRecipesViaResult(item.toItem())));
        this.workbenchRecipes = new DummyWorkbenchRecipe[workbench.size()];
        this.workbenchRecipes = workbench.toArray(this.workbenchRecipes);
        workbench.clear();

        // FURNACE
        List<DummyFurnaceRecipe> furnace = Lists.newArrayList();
        if (item.getItemType() == ItemType.VANILLA) furnace.addAll(DummyFurnaceRecipe.convertMC(Bukkit.getRecipesFor(item.toItem())));
        this.furnaceRecipes = new DummyFurnaceRecipe[furnace.size()];
        this.furnaceRecipes = furnace.toArray(this.furnaceRecipes);
        furnace.clear();

        // MACERATOR
        List<DummyMaceratorRecipe> macerator = Lists.newArrayList();
//        if (item.getItemType() == ItemType.VANILLA) macerator.addAll(DummyMaceratorRecipe.convertMC(Bukkit.getRecipesFor(item.toItem())));
        if (item.getItemType() == ItemType.IC2) macerator.addAll(DummyMaceratorRecipe.convertIC2(recipeManager.getMaceratorRecipesViaResult(item.toItem())));
        this.maceratorRecipes = new DummyMaceratorRecipe[macerator.size()];
        this.maceratorRecipes = macerator.toArray(this.maceratorRecipes);
        macerator.clear();

        this.getGuideTypes();
        this.getDefaultGuideType();
    }

    public IItem getItem() {
        return item;
    }

    public DummyWorkbenchRecipe[] getWorkbenchRecipes() {
        return workbenchRecipes;
    }

    public DummyFurnaceRecipe[] getFurnaceRecipes() {
        return furnaceRecipes;
    }

    public DummyMaceratorRecipe[] getMaceratorRecipes() {
        return maceratorRecipes;
    }

    public GuideType[] getGuideTypes() {
        if (this.guideTypes == null) {
            List<GuideType> temp = Lists.newArrayList();

            if (this.workbenchRecipes.length > 0)
                temp.add(GuideType.WORKBENCH);

            if (this.furnaceRecipes.length > 0)
                temp.add(GuideType.FURNACE);

            if (this.maceratorRecipes.length > 0)
                temp.add(GuideType.MACERATOR);

            this.guideTypes = new GuideType[temp.size()];
            this.guideTypes = temp.toArray(this.guideTypes);
        }

        return this.guideTypes;
    }

    public GuideType getDefaultGuideType() {
        if (this.defaultType == null) {
            for (GuideType type : GuideType.values()) {
                if (ArrayUtil.containsElement(type, this.getGuideTypes())) {
                    this.defaultType = type;
                    break;
                }
            }
        }

        return this.defaultType;
    }

    public GuideType[] getSuitableGuides(int amount, GuideType selected) {
        GuideType[] types = new GuideType[amount];

        if (amount == 1) {
            types[0] = selected;
            return types;
        }

        int index = index(selected);

        if (amount == 2) {
            types[0] = selected;
            types[1] = next(index);
            return types;
        }

        types[0] = previous(index);
        types[1] = selected;
        types[2] = next(index);

        return types;
    }

    public int index(GuideType selected) {
        for (int i = 0; i < this.getGuideTypes().length; i++) {
            if (this.getGuideTypes()[i] == selected)
                return i;
        }

        return 0;
    }

    public GuideType next(int index) {
        if (index >= (getGuideTypes().length - 1)) {
            return getGuideTypes()[0];
        }

        return getGuideTypes()[index + 1];
    }

    public GuideType previous(int index) {
        if (index <= 0) {
            return getGuideTypes()[getGuideTypes().length - 1];
        }

        return getGuideTypes()[index - 1];
    }

    public BaseMenu getDefaultMenu(Player player) {
        if (this.defaultType == null)
            return null;

        switch (this.defaultType) {
            case WORKBENCH:
                return new GuideWorkbenchMenu(player, this, 0);

            case FURNACE:
                return new GuideFurnaceMenu(player, this, 0);

            case MACERATOR:
                return new GuideMaceratorMenu(player, this, 0);//TODO Change UI tile
        }

        return null;
    }
}
