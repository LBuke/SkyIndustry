package com.destinymc.ic2.recipe;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.destinymc.ic2.recipe.event.PrepareCustomItemCraftEvent;
import com.destinymc.ic2.recipe.macerator.ClayDustMaceratorRecipe;
import com.destinymc.ic2.recipe.macerator.CoalDustMaceratorRecipe;
import com.destinymc.ic2.recipe.type.BaseCraftingRecipe;
import com.destinymc.ic2.recipe.type.BaseMaceratorRecipe;
import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.CarbonFibersWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.ElectronicCircuitWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.eu.EnergyCrystalWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.eu.LapotronCrystalWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.eu.REBatteryWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.ingot.MixedMetalIngotWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.machine.BasicMachineCasingWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.machine.GeneratorWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.machine.GeothermalGeneratorWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.tool.ForgeHammerRecipe;
import com.destinymc.ic2.recipe.workbench.shaped.tool.InsulationCutterWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shapeless.CombinedCarbonFibersWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shapeless.CopperPlateRecipe;
import com.destinymc.ic2.recipe.workbench.shapeless.eu.SingleUseBatteryWorkbenchRecipe;
import com.destinymc.ic2.recipe.workbench.shapeless.ingot.RefinedIronWorkbenchRecipe;
import com.destinymc.ic2.util.item.IItem;
import com.destinymc.packet.in.WrapperPlayClientRecipeDisplayed;
import com.destinymc.util.Log;
import com.destinymc.util.Pair;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class RecipeManager implements Listener {

    private static Set<BaseCraftingRecipe> craftingRecipes;
    private static Set<BaseMaceratorRecipe> maceratorRecipes;

    public RecipeManager(JavaPlugin plugin) {
        this.craftingRecipes = Sets.newHashSet();
        this.maceratorRecipes = Sets.newHashSet();

        Bukkit.getPluginManager().registerEvents(this, plugin);

        // INGOT
        reisterCraft(new MixedMetalIngotWorkbenchRecipe());
        reisterCraft(new RefinedIronWorkbenchRecipe());

        // PLATE
        reisterCraft(new CopperPlateRecipe());

        // CABLE
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.copper.CopperCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.copper.InsulatedCopperCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.gold.GoldCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.gold.InsulatedGoldCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.hv.HVCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.hv.InsulatedHVCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.DetectorCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.GlassFibreCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.SplitterCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shaped.cable.TinCableWorkbenchRecipe());

        reisterCraft(new com.destinymc.ic2.recipe.workbench.shapeless.cable.gold.GoldCable2xInsWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shapeless.cable.gold.InsulatedGoldCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shapeless.cable.hv.HVCable2xInsWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shapeless.cable.hv.HVCable3xInsWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shapeless.cable.hv.InsulatedHVCableWorkbenchRecipe());
        reisterCraft(new com.destinymc.ic2.recipe.workbench.shapeless.cable.InsulatedCopperCableWorkbenchRecipe());

        // TOOL
        reisterCraft(new ForgeHammerRecipe());
        reisterCraft(new InsulationCutterWorkbenchRecipe());

        reisterCraft(new ElectronicCircuitWorkbenchRecipe());

        reisterCraft(new SingleUseBatteryWorkbenchRecipe());
        reisterCraft(new REBatteryWorkbenchRecipe());
        reisterCraft(new EnergyCrystalWorkbenchRecipe());
        reisterCraft(new LapotronCrystalWorkbenchRecipe());

        reisterCraft(new CarbonFibersWorkbenchRecipe());
        reisterCraft(new CombinedCarbonFibersWorkbenchRecipe());

        // MACHINE
        reisterCraft(new BasicMachineCasingWorkbenchRecipe());
        reisterCraft(new GeneratorWorkbenchRecipe());
        reisterCraft(new GeothermalGeneratorWorkbenchRecipe());

        registerMacerator(new CoalDustMaceratorRecipe());
        registerMacerator(new ClayDustMaceratorRecipe());

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, WrapperPlayClientRecipeDisplayed.TYPE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                WrapperPlayClientRecipeDisplayed packet = new WrapperPlayClientRecipeDisplayed(event.getPacket());
                packet.setBookOpen(false);
                Log.debug("OPEN CRAFTING HELPER.");
            }
        });
    }

    public Set<BaseCraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public Set<BaseMaceratorRecipe> getMaceratorRecipes() {
        return maceratorRecipes;
    }

    private void reisterCraft(BaseCraftingRecipe recipe) {
        craftingRecipes.add(recipe);
        Log.register("IC2 Recipe : " + recipe.getClass().getSimpleName());
    }

    private void registerMacerator(BaseMaceratorRecipe recipe) {
        maceratorRecipes.add(recipe);
        Log.register("IC2 Recipe : " + recipe.getClass().getSimpleName());
    }

    public static List<BaseRecipe.Recipe> getWorkbenchRecipesViaResult(ItemStack result) {
        List<BaseRecipe.Recipe> list = Lists.newArrayList();
        for (BaseCraftingRecipe recipe : craftingRecipes) {
            for (BaseRecipe.Recipe insideRecipe : recipe.getInnerRecipes()) {
                if (recipe.getOutput(insideRecipe).isMatch(result)) {
                    list.add(insideRecipe);
                }
            }
        }

        return list;
    }

    public static List<BaseRecipe.Recipe> getWorkbenchRecipesViaInput(ItemStack result) {
        List<BaseRecipe.Recipe> list = Lists.newArrayList();
        for (BaseCraftingRecipe recipe : craftingRecipes) {
            for (BaseRecipe.Recipe insideRecipe : recipe.getInnerRecipes()) {
                if (recipe.getInput(insideRecipe)[0].isMatch(result)) {
                    list.add(insideRecipe);
                }
            }
        }

        return list;
    }

    public static List<BaseRecipe.Recipe> getMaceratorRecipesViaResult(ItemStack result) {
        List<BaseRecipe.Recipe> list = Lists.newArrayList();
        for (BaseMaceratorRecipe recipe : maceratorRecipes) {
            for (BaseRecipe.Recipe insideRecipe : recipe.getInnerRecipes()) {
                for (IItem output : insideRecipe.getOutput()) {
                    if (output.isMatch(result)) {
                        list.add(insideRecipe);
                    }
                }
            }
        }

        return list;
    }

    public static List<BaseRecipe.Recipe> getMaceratorRecipesViaInput(ItemStack result) {
        List<BaseRecipe.Recipe> list = Lists.newArrayList();
        for (BaseMaceratorRecipe recipe : maceratorRecipes) {
            for (BaseRecipe.Recipe insideRecipe : recipe.getInnerRecipes()) {
                if (recipe.getInput().isMatch(result)) {
                    list.add(insideRecipe);
                }
            }
        }

        return list;
    }

    @EventHandler
    protected final void onCraft(PrepareItemCraftEvent event) {

        Pair<? extends BaseCraftingRecipe, BaseRecipe.Recipe> recipePair = null;
        for (BaseCraftingRecipe recipe : craftingRecipes) {
            Pair<? extends BaseCraftingRecipe, BaseRecipe.Recipe> pair = recipe.get(event.getInventory().getMatrix());
            if (pair != null) {
                recipePair = pair;
                break;
            }
        }

        if (recipePair == null)
            return;

        ItemStack output = recipePair.getKey().getOutput(recipePair.getValue()).toItem();
        event.getInventory().setResult(output);

        for (HumanEntity humanEntity : event.getViewers()) {
            PrepareCustomItemCraftEvent customItemCraftEvent = new PrepareCustomItemCraftEvent((Player) humanEntity, recipePair, output);
            Bukkit.getPluginManager().callEvent(customItemCraftEvent);
        }
    }

    @EventHandler
    protected final void onCraft(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player == null)
            return;

        if (event.getClickedInventory() == null)
            return;

        switch (event.getClickedInventory().getType()) {
            case WORKBENCH:
            case CRAFTING:

                break;
        }
    }
}

//    @EventHandler
//    public void onInteract(InventoryClickEvent event) {
//        Player player = (Player) event.getWhoClicked();
//        if (event.getClickedInventory() != null && (event.getClickedInventory().getType() == InventoryType.WORKBENCH || event.getClickedInventory().getType() == InventoryType.CRAFTING) && event.getSlot() == 0) {
//            CraftingInventory inv = (CraftingInventory) event.getClickedInventory();
//            Optional<IItem> craftingRecipeItem = Vice.getItemManager().getCraftingRecipe(inv.getMatrix());
//            if (!craftingRecipeItem.isPresent())
//                return;
//            if (inv.getItem(0) == null) return;
//            int amt = 0;
//            event.setCancelled(true);
//            if (event.getClick().toString().contains("SHIFT")) {
//                for (int i = 0; i < (event.getClickedInventory().getType() == InventoryType.WORKBENCH ? 9 : 4); i++) {
//                    ItemStack ingredient = inv.getMatrix()[i];
//                    if (ingredient == null || ingredient.getType() == Material.AIR)
//                        continue;
//                    if (amt == 0 || ingredient.getAmount() < amt)
//                        amt = ingredient.getAmount();
//                }
//            }
//            ItemStack result = inv.getItem(0).clone();
//            if (amt == 0)
//                amt = 1;
//            result.setAmount(amt);
//            if (event.getClick().toString().contains("SHIFT")) {
//                if (player.getInventory().firstEmpty() == -1) {
//                    return;
//                }
//                player.getInventory().addItem(result);
//            } else if (player.getItemOnCursor() != null && player.getItemOnCursor().getType() != Material.AIR) {
//                if (player.getItemOnCursor().isSimilar(result)) {
//                    if ((result.getAmount() + player.getItemOnCursor().getAmount()) > result.getMaxStackSize()) {
//                        return;
//                    } else {
//                        result.setAmount(result.getAmount() + player.getItemOnCursor().getAmount());
//                        player.setItemOnCursor(result);
//                    }
//                } else {
//                    return;
//                }
//            } else {
//                player.setItemOnCursor(result);
//            }
//            for (int i = 1; i < (inv.getType() == InventoryType.WORKBENCH ? 10 : 5); i++) {
//                ItemStack is = inv.getItem(i);
//                if (is == null || is.getType() == Material.AIR)
//                    continue;
//                if (is.getAmount() > amt) {
//                    is.setAmount(is.getAmount() - amt);
//                } else {
//                    inv.setItem(i, null);
//                }
//            }
//            if (!Vice.getItemManager().getCraftingRecipe(inv.getMatrix()).isPresent()) {
//                inv.