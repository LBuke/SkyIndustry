# IC²

Look at 'Example Usage' for a breif tutorial/ explanation.
But first, some images.

#### Furnace Guide
![Furnace Guide](https://i.imgur.com/HgPwiYn.png)

#### Workbench Guide
![Workbench Guide](https://i.imgur.com/q34ye8L.png)

---
### Example Usage
```java
public final class GuideFurnaceMenu extends GuideMenu {

    private static final int INPUT = 38;   // Input item slot(s)
    private static final int OUTPUT = 51;  // Output item slot(s)

    // This is the current page
    private int index = 0;

    // Constructor (Construct a new GuideMenu)
    public GuideFurnaceMenu(Player player, GuideRoot root, int index) {
        super(
                8,                  // Inventory rows (8 * 9 slots)
                player,             // Player to view to
                root,               // RootMenu, used to hold private instances
                GuideType.FURNACE   // Guide Type is Furnace (Vanilla)
        );
        this.index = index;

        // This is the 'tile' for the custom inventory visual.
        // 'addItem(MenuItem(index[int], ItemStack), clickable[boolean])'
        addItem(new MenuItem(0, new FurnaceGuideTile("FurnaceGuideTile", Lists.newArrayList()), false));

        for (int i = 1; i < getInventory().getSize(); i++) {
        
            // If the found slot is INPUT or OUTPUT, don't continue.
            // Because we don't want a 'blank slot' in these area's
            if (i == INPUT || i == OUTPUT)
                continue;
            
            // This blank 'tile' is used for null/air slots in the inventory.
            // So when hovering, it can show infomation. (IN-GUI Tutorial)
            // 'addItem(MenuItem(index[int], ItemStack), clickable[boolean])'
            addItem(new MenuItem(i, new BlankTile("BlankTile", Lists.newArrayList()), false));
        }

        // This loop get's all the guide types for the current recipe item.
        // There could be a WORKBENCH recipe, FURNACE, Custom IC2 Recipe..
        for (MenuItem item : getGuideTypeItems("name", Lists.newArrayList())) {
            addItem(item);
        }

        // These items are next page and back page items for the guide type.
        // If there isn't any guide's lined up, it'll show 'non-clickable' arrows.
        for (MenuItem item : getTypeArrowItems("name", Lists.newArrayList())) {
            addItem(item);
        }

        // NOTE: This page selection needs cleaning up, for now it's a madness.
        
        // If "CurrentPage <= 0" then add a 'non-clickable' back page arrow.
        if (index <= 0) {
            addItem(new MenuItem(45, new LockedLeftArrowTile("LeftArrowTile", Lists.newArrayList()), false));
        }
        
        // Else if there is pages available, add a 'clickable' back page arrow.
        // ClickableItem.java is a child of 'MenuItem' but has an 'onClick' method.
        else {
            addItem(new ClickableItem(45, new LeftArrowTile(index, "LeftArrowTile", Lists.newArrayList()), (player1, clickType) -> {
                new GuideFurnaceMenu(player, root, index - 1).openInventory(player);
            }));
        }

        // If there are more recipe visuals available, add a 'clickable' next page arrow.
        if (root.getFurnaceRecipes().length - 1 > index) {
            addItem(new ClickableItem(53, new RightArrowTile((root.getFurnaceRecipes().length - 1) - index, "RightArrowTile", Lists.newArrayList()), (player1, clickType) -> {
                new GuideFurnaceMenu(player, root, index + 1).openInventory(player);
            }));
        }
        
        // If there are no more available recipe's for the selected item, add a 'non-clickable' next page arrow.
        else {
            addItem(new MenuItem(53, new LockedRightArrowTile(1, "RightArrowTile", Lists.newArrayList()), false));
        }

        // What's 'DummyRecipe'?
        // Due to Vanilla Recipe's not being compatible with our custom IC2 recipe's,
        // We have to somehow store them together in a List of somesort.
        // The outcome is, have a Dummy class to hold all types of 'Furnace' recipe's!
        DummyFurnaceRecipe recipe = root.getFurnaceRecipes()[index];
        if (recipe == null)
            return;

        // Pretty straight forward, ADD the input & output recipe items!
        addItem(new MenuItem(INPUT, recipe.getInput().toItem(), false));
        addItem(new MenuItem(OUTPUT, recipe.getResult().toItem(), false));
        
        // We're done! Simple enough?
        // Open to suggestions/Improvements ~Luke
    }
}
```

***authors:** Luke Bingham (**MrTeddeh**)*, ..