package com.destinymc.ic2.item.misc;

import com.destinymc.ic2.cable.CableShapeType;
import com.destinymc.util.LocationUtil;
import com.destinymc.util.ServerUtil;
import com.google.common.collect.Lists;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

/**
 * Created at 02/03/2019
 *
 * @author Luke Bingham
 */
public interface CableBlock extends PlaceableItem {

    public static final String META = "CABBLOCK";


    /**
     *
     * MAKE THIS CLASS NOT USE A HARD BLOCK
     * (This should just use armorstands)
     *
     */

    default boolean construct(Player player, Location location, Location origin) {
        construct(player, location);

        if (location.getBlockX() == origin.getBlockX() && location.getBlockY() == origin.getBlockY() && location.getBlockZ() == origin.getBlockZ()) {
            Block originBlock = location.getWorld().getBlockAt(location);

            for (BlockFace face : CableShapeType.SUPPORTED) {
                Block found = originBlock.getRelative(face);
                if (found == null || found.getType() == Material.AIR)
                    continue;

                if (!(found.getState() instanceof CreatureSpawner))
                    continue;

                CreatureSpawner foundSpawner = (CreatureSpawner) originBlock.getRelative(face).getState();
                if (foundSpawner.hasMetadata(CustomBlock.META)) {
                    construct(player, foundSpawner.getLocation());
                }
            }
        }
        return true;
    }

    @Override
    default boolean construct(Player player, Location location) {
        Block block = location.getWorld().getBlockAt(location);
        if (block.getType() != Material.MOB_SPAWNER)
            block.setType(Material.MOB_SPAWNER);

        CraftWorld craftWorld = (CraftWorld) location.getWorld();
        BlockPosition blockPosition = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner) craftWorld.getHandle().getTileEntity(blockPosition);

        if (tileEntityMobSpawner == null)
            return false;

        BlockFace playerDirection = LocationUtil.getCardinalDirection(player.getLocation().getYaw());
        if (playerDirection == null) return false;

        NBTTagCompound tagCompound = tileEntityMobSpawner.d();
        if (tagCompound == null)
            tagCompound = new NBTTagCompound();

        tagCompound.setInt("Delay", Integer.MAX_VALUE);
        tagCompound.setInt("MinSpawnDelay", Integer.MAX_VALUE);
        tagCompound.setInt("MaxSpawnDelay", Integer.MAX_VALUE);
        tagCompound.setShort("RequiredPlayerRange", (short) 0);
        tagCompound.setShort("MaxNearbyEntities", (short) 0);
        tagCompound.setShort("SpawnRange", (short) 0);

        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setString("id", new MinecraftKey(EntityType.ARMOR_STAND.getName()).toString());
        spawnData.setBoolean("Invisible", true);
        spawnData.setBoolean("Marker", true);

        NBTTagCompound poseData = new NBTTagCompound();
        poseData.set("Head", new Vector3f(0.0F, LocationUtil.getYawFromBlockFace360(LocationUtil.getOppositeDirection(playerDirection)), 0.0F).a());
        spawnData.set("Pose", poseData);

        NBTTagList armorList = new NBTTagList();
        armorList.add(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)).save(new NBTTagCompound()));
        armorList.add(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)).save(new NBTTagCompound()));
        armorList.add(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)).save(new NBTTagCompound()));
        armorList.add(CraftItemStack.asNMSCopy(this.shape(location)).save(new NBTTagCompound()));
        spawnData.set("ArmorItems", armorList);

        tagCompound.set("SpawnData", spawnData);

        tileEntityMobSpawner.load(tagCompound);

        CreatureSpawner spawner = (CreatureSpawner) location.getWorld().getBlockAt(location).getState();
        spawner.setMetadata(CustomBlock.META, new FixedMetadataValue(ServerUtil.getPlugin(), this.getIdentifier()));

        return true;
    }

    default ItemStack shape(Location location) {
        ItemStack item = this.getBlockItem().clone();

        Block block = location.getBlock();
        List<BlockFace> facesList = Lists.newArrayList();

        for (BlockFace face : CableShapeType.SUPPORTED) {
            Block found = block.getRelative(face);
            if (found == null || found.getType() == Material.AIR)
                continue;

            if (!(found.getState() instanceof CreatureSpawner))
                continue;

            facesList.add(face);
        }
        System.out.println("faces:");
        facesList.forEach(f -> System.out.println(f.name()));
        System.out.println("");

        CableShapeType shapeType = CableShapeType.getShape(facesList);
        item.setDurability((short) shapeType.getId());

        return item;
    }
}
