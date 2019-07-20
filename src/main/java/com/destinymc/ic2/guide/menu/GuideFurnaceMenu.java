package com.destinymc.ic2.guide.menu;

import com.destinymc.ic2.guide.GuideRoot;
import com.destinymc.ic2.guide.GuideType;
import com.destinymc.ic2.guide.recipe.DummyFurnaceRecipe;
import com.destinymc.ic2.machine.item.tile.*;
import com.destinymc.ic2.machine.item.tile.guide.FurnaceGuideTile;
import com.destinymc.inventory.button.ClickableItem;
import com.destinymc.inventory.button.MenuItem;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class GuideFurnaceMenu extends GuideMenu {

    private static final int INPUT = 38;
    private static final int OUTPUT = 51;

    private int index = 0;

    public GuideFurnaceMenu(Player player, GuideRoot root, int index) {
        super(8, player, root, GuideType.FURNACE);
        this.index = index;

        addItem(new MenuItem(0, new FurnaceGuideTile("FurnaceGuideTile", Lists.newArrayList()), false));

        for (int i = 1; i < getInventory().getSize(); i++) {
            if (i == INPUT || i == OUTPUT)
                continue;

            addItem(new MenuItem(i, new BlankTile("BlankTile", Lists.newArrayList()), false));
        }

        for (MenuItem item : getGuideTypeItems("name", Lists.newArrayList())) {
            addItem(item);
        }

        for (MenuItem item : getTypeArrowItems("name", Lists.newArrayList())) {
            addItem(item);
        }

        if (index <= 0)
            addItem(new MenuItem(45, new LockedLeftArrowTile("LeftArrowTile", Lists.newArrayList()), false));
        else
            addItem(new ClickableItem(45, new LeftArrowTile(index, "LeftArrowTile", Lists.newArrayList()), (player1, clickType) -> {
                new GuideFurnaceMenu(player, root, index - 1).openInventory(player);
            }));

        if (root.getFurnaceRecipes().length - 1 > index)
            addItem(new ClickableItem(53, new RightArrowTile((root.getFurnaceRecipes().length - 1) - index, "RightArrowTile", Lists.newArrayList()), (player1, clickType) -> {
                new GuideFurnaceMenu(player, root, index + 1).openInventory(player);
            }));
        else
            addItem(new MenuItem(53, new LockedRightArrowTile(1, "RightArrowTile", Lists.newArrayList()), false));

        DummyFurnaceRecipe recipe = root.getFurnaceRecipes()[index];
        if (recipe == null)
            return;

        addItem(new MenuItem(INPUT, recipe.getInput().toItem(), false));
        addItem(new MenuItem(OUTPUT, recipe.getResult().toItem(), false));
    }
}
