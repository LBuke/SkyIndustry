package com.destinymc.util;

import org.bukkit.Nameable;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BlockUtil {

    public static boolean isBlockAnInventory(BlockState blockState) {
        if (blockState == null)
            return false;

        return blockState instanceof InventoryHolder;
    }

    public static void renameBlockInventory(BlockState blockState, String title) {
        if (blockState == null)
            return;

        if (!(blockState instanceof Nameable))
            return;

        ((Nameable) blockState).setCustomName(title);
    }

    public static Inventory getBlockInventory(BlockState blockState) {
        if (blockState == null)
            return null;

        if (!(blockState instanceof InventoryHolder))
            return null;

        return ((InventoryHolder) blockState).getInventory();
    }

    public static void editBlockState(Block block, Callback<BlockState> callback) {
        if (block == null) return;

        BlockState blockState = block.getState();
        if (blockState == null) return;

        callback.call(blockState);

        blockState.update();
    }
}
