package com.destinymc.ic2.guide;

import com.destinymc.command.CommandArgument;
import com.destinymc.command.ForeignCommand;
import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.IC2ItemManager;
import com.destinymc.ic2.recipe.RecipeManager;
import com.destinymc.ic2.recipe.type.BaseCraftingRecipe;
import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.inventory.BaseMenu;
import com.destinymc.util.ServerUtil;
import com.destinymc.misc.Toggleable;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.Iterator;

import static org.bukkit.Material.AIR;

/**
 * Created at 22/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class GuideManager implements Listener, Toggleable {

    private final static HashMap<MaterialData, GuideRoot> GUIDE_ITEM_MAP;

    static {
        GUIDE_ITEM_MAP = Maps.newHashMap();
    }

    private boolean enabled = true;

    public GuideManager(RecipeManager recipeManager, IC2ItemManager itemManager) {

        Bukkit.getPluginManager().registerEvents(this, ServerUtil.getPlugin());

//        GUIDE_ITEM_MAP.put(new Pair<>(Material.DIAMOND_PICKAXE, 0), new GuideRoot(recipeManager, new BaseItem(new ItemStack(Material.DIAMOND_PICKAXE))));

        Iterator<Recipe> mcRecipes = Bukkit.getServer().recipeIterator();
        while (mcRecipes.hasNext()) {
            Recipe recipe = mcRecipes.next();
            ItemStack item = recipe.getResult();

            if (item.getType() == AIR)
                continue;

            if (GUIDE_ITEM_MAP.containsKey(item.getData()))
                continue;

            GuideRoot root = new GuideRoot(recipeManager, new BaseItem(item));
            GUIDE_ITEM_MAP.put(item.getData(), root);
        }

        for (BaseCraftingRecipe craftingRecipe : recipeManager.getCraftingRecipes()) {
            for (BaseRecipe.Recipe recipe : craftingRecipe.getInnerRecipes()) {
                IC2Item ic2Item = craftingRecipe.getOutput(recipe).getIc2Item();

                if (GUIDE_ITEM_MAP.containsKey(ic2Item.getData()))
                    continue;

                GuideRoot root = new GuideRoot(recipeManager, new BaseItem(ic2Item));
                GUIDE_ITEM_MAP.put(ic2Item.getData(), root);
            }
        }

        for (BaseMaceratorRecipe maceratorRecipe : recipeManager.getMaceratorRecipes()) {
            ItemStack ic2Item = maceratorRecipe.getOutput()[0].toItem();
            if (ic2Item == null)
                continue;

            if (GUIDE_ITEM_MAP.containsKey(ic2Item.getData()))
                continue;

            GuideRoot root = new GuideRoot(recipeManager, new BaseItem(ic2Item));
            GUIDE_ITEM_MAP.put(ic2Item.getData(), root);
        }
    }

    @ForeignCommand(value = "toggle", permission = "ic2.admin")
    public void commandGuideToggle(CommandArgument argument) {
        this.toggle();
        argument.getSender().sendMessage("GuideManager " + (enabled ? "Enabled" : "Disabled"));
    }

    @EventHandler(ignoreCancelled = false)
    protected final void onInteract(InventoryClickEvent event) {
        if (this.isDisabled())
            return;

        if (event.getClick() != ClickType.SHIFT_RIGHT)
            return;

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == AIR)
            return;

        if (!GUIDE_ITEM_MAP.containsKey(item.getData()))
            return;

        GuideRoot root = GUIDE_ITEM_MAP.get(item.getData());
        Player player = (Player) event.getWhoClicked();

        BaseMenu menu = root.getDefaultMenu(player);
        if (menu == null)
            return;

        event.setCancelled(true);

        menu.openInventory(player);
    }

    @EventHandler
    protected final void onSneak(PlayerToggleSneakEvent event) {
//        if (event.isSneaking()) return;
//
//        List<GuideRoot> list = GUIDE_ITEM_MAP.values().stream().filter(root -> root.getFurnaceRecipes().length > 3 && root.getWorkbenchRecipes().length > 0).collect(Collectors.toList());
//        int randonIndex = RandomUtil.RANDOM.nextInt(list.size());
//
//        BaseMenu menu = list.get(randonIndex).getDefaultMenu(event.getPlayer());
//        if (menu == null)
//            return;
//
//        menu.openInventory(event.getPlayer());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void toggle() {
        enabled = !enabled;
    }
}
