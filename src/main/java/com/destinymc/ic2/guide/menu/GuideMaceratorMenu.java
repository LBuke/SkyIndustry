package com.destinymc.ic2.guide.menu;

import com.destinymc.ic2.guide.GuideRoot;
import com.destinymc.ic2.guide.GuideType;
import com.destinymc.ic2.guide.recipe.DummyWorkbenchRecipe;
import com.destinymc.ic2.machine.item.tile.*;
import com.destinymc.ic2.machine.item.tile.guide.WorkbenchGuideTile;
import com.destinymc.ic2.util.item.IItem;
import com.destinymc.inventory.button.ClickableItem;
import com.destinymc.inventory.button.MenuItem;
import com.destinymc.misc.Unfinished;
import com.destinymc.util.ArrayUtil;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Unfinished
public final class GuideMaceratorMenu extends GuideMenu {

    private static final int[] MATRIX = {38, 39, 40, 47, 48, 49, 56, 57, 58};
    private static final int OUTPUT = 51;

    private int index = 0;

    public GuideMaceratorMenu(Player player, GuideRoot root, int index) {
        super(8, player, root, GuideType.WORKBENCH);
        this.index = index;

        addItem(new MenuItem(0, new WorkbenchGuideTile("WorkbenchGuideTile", Lists.newArrayList()), false));

        for (int i = 1; i < getInventory().getSize(); i++) {
            if (ArrayUtil.containsElement(i, MATRIX))
                continue;

            if (i == OUTPUT)
                continue;

            addItem(new MenuItem(i, new BlankTile("BlankTile", Lists.newArrayList()), false));
        }

        for (MenuItem item : getGuideTypeItems("name", Lists.newArrayList())) {
            addItem(item);
        }

        for (MenuItem item : getTypeArrowItems("name", Lists.newArrayList())) {
            addItem(item);
        }

//        addItem(new MenuItem(12, new ItemStack(Material.WORKBENCH), false));
//        addItem(new MenuItem(21, new BigSlotTile("BigSlotTile", Lists.newArrayList()), false));
//
//        addItem(new MenuItem(14, new ItemStack(Material.FURNACE), false));
//        addItem(new MenuItem(23, new BigDisabledSlotTile("BigDisabledSlotTile", Lists.newArrayList()), false));
//
//        addItem(new MenuItem(9, new LockedLeftArrowTile("LockedLeftArrowTile", Lists.newArrayList()), false));
//        addItem(new MenuItem(17, new LockedRightArrowTile("LockedRightArrowTile", Lists.newArrayList()), false));

        if (index <= 0)
            addItem(new MenuItem(45, new LockedLeftArrowTile("LeftArrowTile", Lists.newArrayList()), false));
        else
            addItem(new ClickableItem(45, new LeftArrowTile(index, "LeftArrowTile", Lists.newArrayList()), (player1, clickType) -> {
                new GuideWorkbenchMenu(player, root, index - 1).openInventory(player);
            }));

        if (root.getWorkbenchRecipes().length - 1 > index)
            addItem(new ClickableItem(53, new RightArrowTile((root.getWorkbenchRecipes().length - 1) - index, "RightArrowTile", Lists.newArrayList()), (player1, clickType) -> {
                new GuideWorkbenchMenu(player, root, index + 1).openInventory(player);
            }));
        else
            addItem(new MenuItem(53, new LockedRightArrowTile(1, "RightArrowTile", Lists.newArrayList()), false));

        DummyWorkbenchRecipe recipe = root.getWorkbenchRecipes()[index];
        if (recipe == null)
            return;

        for (int i = 0; i < MATRIX.length; i++) {
            addItem(new MenuItem(MATRIX[i], new BlankTile(" ", Lists.newArrayList()), false));
        }

        for (int i = 0; i < recipe.getInput().length; i++) {
            IItem item = recipe.getInput()[i];
            if (item == null || item.toItem() == null || item.toItem().getType() == Material.AIR) {
                addItem(new MenuItem(MATRIX[i], new BlankTile(" ", Lists.newArrayList()), false));
                continue;
            }

            addItem(new MenuItem(MATRIX[i], item.toItem(), false));
        }

        addItem(new MenuItem(OUTPUT, recipe.getResult().toItem(), false));
    }
}
