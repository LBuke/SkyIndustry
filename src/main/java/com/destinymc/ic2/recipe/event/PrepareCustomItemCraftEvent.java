package com.destinymc.ic2.recipe.event;

import com.destinymc.ic2.recipe.type.BaseCraftingRecipe;
import com.destinymc.ic2.recipe.type.BaseRecipe;
import com.destinymc.util.Pair;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public final class PrepareCustomItemCraftEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Pair<? extends BaseCraftingRecipe, BaseRecipe.Recipe> recipePair;
    private final ItemStack item;

    private boolean cancelled;

    /**
     * Construct a new Event
     */
    public PrepareCustomItemCraftEvent(Player player, Pair<? extends BaseCraftingRecipe, BaseRecipe.Recipe> recipePair, ItemStack item) {
        super(false);
        this.player = player;
        this.recipePair = recipePair;
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public Pair<? extends BaseCraftingRecipe, BaseRecipe.Recipe> getRecipePair() {
        return recipePair;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
