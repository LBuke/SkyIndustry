package com.destinymc.ic2.guide.menu;

import com.destinymc.ic2.guide.GuideRoot;
import com.destinymc.ic2.guide.GuideType;
import com.destinymc.ic2.machine.item.tile.*;
import com.destinymc.inventory.BaseMenu;
import com.destinymc.inventory.button.ClickableItem;
import com.destinymc.inventory.button.MenuItem;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public abstract class GuideMenu extends BaseMenu {

    private Player player;
    private final GuideRoot root;
    private GuideType selectedGuideType = null;

    public GuideMenu(int rows, Player player, GuideRoot root, GuideType guideType) {
        super(rows, root.getItem().getDisplayName());
        this.player = player;
        this.root = root;
        this.selectedGuideType = guideType;
    }

    public GuideRoot getRoot() {
        return root;
    }

    public GuideType getSelectedGuideType() {
        return selectedGuideType;
    }

    public MenuItem[] getTypeArrowItems(String name, List<String> lore) {
        if (root.getGuideTypes().length >= 3) {
            return new MenuItem[]{
                    new ClickableItem(9, new LeftArrowTile(name, lore), (player1, clickType) -> {
                        GuideType type = root.previous(root.index(selectedGuideType));
                        switch (type) {
                            case WORKBENCH:
                                new GuideWorkbenchMenu(player, root, 0).openInventory(player);
                                break;

                            case FURNACE:
                                new GuideFurnaceMenu(player, root, 0).openInventory(player);
                                break;
                        }
                    }),
                    new ClickableItem(17, new RightArrowTile(name, lore), (player1, clickType) -> {
                        GuideType type = root.next(root.index(selectedGuideType));
                        switch (type) {
                            case WORKBENCH:
                                new GuideWorkbenchMenu(player, root, 0).openInventory(player);
                                break;

                            case FURNACE:
                                new GuideFurnaceMenu(player, root, 0).openInventory(player);
                                break;
                        }
                    }),
            };
        }

        if (root.getGuideTypes().length == 2) {
            return new MenuItem[]{
                    new ClickableItem(10, new LeftArrowTile(name, lore), (player1, clickType) -> {
                        GuideType type = root.previous(root.index(selectedGuideType));
                        switch (type) {
                            case WORKBENCH:
                                new GuideWorkbenchMenu(player, root, 0).openInventory(player);
                                break;

                            case FURNACE:
                                new GuideFurnaceMenu(player, root, 0).openInventory(player);
                                break;
                        }
                    }),
                    new ClickableItem(16, new RightArrowTile(name, lore), (player1, clickType) -> {
                        GuideType type = root.next(root.index(selectedGuideType));
                        switch (type) {
                            case WORKBENCH:
                                new GuideWorkbenchMenu(player, root, 0).openInventory(player);
                                break;

                            case FURNACE:
                                new GuideFurnaceMenu(player, root, 0).openInventory(player);
                                break;
                        }
                    })
            };
        }

        return new MenuItem[]{
                new MenuItem(10, new LockedLeftArrowTile(name, lore), false),
                new MenuItem(16, new LockedRightArrowTile(name, lore), false)
        };
    }

    public MenuItem[] getGuideTypeItems(String name, List<String> lore) {
        if (root.getGuideTypes().length == 1) {
            GuideType[] types = root.getSuitableGuides(1, selectedGuideType);
            return new MenuItem[]{
                    new MenuItem(13, types[0].getItemFactory().build(), false),
                    new MenuItem(22, new BigSlotTile(name, lore), false),
            };
        }

        if (root.getGuideTypes().length == 2) {
            GuideType[] types = root.getSuitableGuides(2, selectedGuideType);
            return new MenuItem[]{
                    new MenuItem(12, types[0].getItemFactory().build(), false),
                    new MenuItem(21, new BigSlotTile(name, lore), false),

                    new MenuItem(23, new BigDisabledSlotTile(name, lore), false),
                    new ClickableItem(14, types[1].getItemFactory().build(), (player1, clickType) -> {
                        switch (types[1]) {
                            case WORKBENCH:
                                new GuideWorkbenchMenu(player, root, 0).openInventory(player);
                                break;

                            case FURNACE:
                                new GuideFurnaceMenu(player, root, 0).openInventory(player);
                                break;
                        }
                    })
            };
        }

        if (root.getGuideTypes().length >= 3) {
            GuideType[] types = root.getSuitableGuides(3, selectedGuideType);
            return new MenuItem[]{
                    new MenuItem(11, types[0].getItemFactory().build(), false),
                    new MenuItem(20, new BigSlotTile(name, lore), false),

                    new MenuItem(13, types[1].getItemFactory().build(), false),
                    new MenuItem(22, new BigDisabledSlotTile(name, lore), false),

                    new MenuItem(15, types[2].getItemFactory().build(), false),
                    new MenuItem(24, new BigDisabledSlotTile(name, lore), false),
            };
        }

        return null;
    }
}
