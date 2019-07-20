package com.destinymc.ic2.item.misc;

import com.destinymc.util.ServerUtil;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created at 31/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface CustomBlock extends PlaceableItem {

    public static final String META = "CBLOCK";

    @Override
    default boolean construct(Player player, Location location) {
        Block block = location.getWorld().getBlockAt(location);
        block.setType(Material.MOB_SPAWNER);

        CraftWorld craftWorld = (CraftWorld) location.getWorld();
        BlockPosition blockPosition = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner) craftWorld.getHandle().getTileEntity(blockPosition);

        if (tileEntityMobSpawner == null)
            return false;

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

        NBTTagList armorList = new NBTTagList();
        armorList.add(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)).save(new NBTTagCompound()));
        armorList.add(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)).save(new NBTTagCompound()));
        armorList.add(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)).save(new NBTTagCompound()));
        armorList.add(CraftItemStack.asNMSCopy(this.getBlockItem().clone()).save(new NBTTagCompound()));
        spawnData.set("ArmorItems", armorList);
        tagCompound.set("SpawnData", spawnData);

        tileEntityMobSpawner.load(tagCompound);

        CreatureSpawner spawner = (CreatureSpawner) location.getWorld().getBlockAt(location).getState();
        spawner.setMetadata(CustomBlock.META, new FixedMetadataValue(ServerUtil.getPlugin(), this.getIdentifier()));

        return true;
    }
}
