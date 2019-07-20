package com.destinymc.ic2.item.misc;

import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.event.MachinePlaceEvent;
import com.destinymc.ic2.machine.misc.MachineAttribute;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.util.LocationUtil;
import com.destinymc.util.RandomUtil;
import com.destinymc.util.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface MachineBlockItem<T extends Machine> extends PlaceableItem {

    T getMachine(long identifier, Location location);

    @Override
    default boolean construct(Player player, Location location) {
//        MachineBlockItem blockItem = (MachineBlockItem) getBlockItem();

        Machine machine = this.getMachine(RandomUtil.RANDOM.nextLong(), location);
        if (machine == null)
            return false;

        MachinePlaceEvent machinePlaceEvent = new MachinePlaceEvent(player, machine, location);
        Bukkit.getPluginManager().callEvent(machinePlaceEvent);

        if (machinePlaceEvent.isCancelled())
            return false;

        BlockFace playerDirection = LocationUtil.getCardinalDirection(player.getLocation().getYaw());
        if (playerDirection == null) return false;

        machine.setFaceDirection(LocationUtil.getOppositeDirection(playerDirection));

        location.getBlock().setType(machine.getMachineFoundationType().getBlockMaterial());
        location.getWorld().playSound(location, Sound.BLOCK_STONE_PLACE, 1f, 1f);

        Block block = location.getBlock();
        if (block == null)
            return false;

        BlockState blockState = block.getState();
        if (blockState == null)
            return false;

        if (blockState instanceof ShulkerBox) {
            ShulkerBox shulkerBox = (ShulkerBox) block.getState();

//            BlockUtil.renameBlockInventory(shulkerBox, "m;" + machine.getIdentifier());
            shulkerBox.update(true, false);

            shulkerBox.setMetadata(MachineUtil.MACHINE_META, new FixedMetadataValue(ServerUtil.getPlugin(), machine.getIdentifier()));

            MachineUtil.setInnerContents(machine, shulkerBox);

            machine.setBlockState(shulkerBox);
        }

        if (machine instanceof MachineAttribute) {
            ((MachineAttribute) machine).init();
        }

        return true;
    }
}
