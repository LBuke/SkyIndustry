package com.destinymc.ic2.machine;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.ic2.machine.visual.VisualMachine;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Set;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface Machine {

    long getIdentifier();

    String getName();

    String[] getDescription();

    byte getRows();

    short getTexture();

    short getTile();

    Location getLocation();

    void setLocation(Location location);

    Inventory getInventory();

    MachineFoundationType getMachineFoundationType();

    BlockState getBlockState();

    void setBlockState(BlockState blockState);

    VisualMachine getVisualMachine();

    Set<Integer> getBlockedSlots();

    boolean isBlockedSlot(int slot);

    HashMap<MachineSlot[], int[]> getSlotDataMap();

    int[] getAcceptSlots(ItemStack itemStack);

    IC2Item getMachineItem();

    BlockFace getFaceDirection();

    void setFaceDirection(BlockFace face);
}
