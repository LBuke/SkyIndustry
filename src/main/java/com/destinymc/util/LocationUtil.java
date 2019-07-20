package com.destinymc.util;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

/**
 * Created at 13/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class LocationUtil {

    public BlockFace getRelative(Location parent, Location child) {
        if (parent == null || child == null)
            return null;

        int pX = parent.getBlockX(), cX = child.getBlockX();
        int pY = parent.getBlockY(), cY = child.getBlockY();
        int pZ = parent.getBlockZ(), cZ = child.getBlockZ();

        if (pX == cX && pY == cY) {
            if (pZ != cZ && pZ - 1 == cZ) return BlockFace.NORTH;
            if (pZ != cZ && pZ + 1 == cZ) return BlockFace.SOUTH;
        }

        if (pY == cY && pZ == cZ) {
            if (pX != cX && pX + 1 == cX) return BlockFace.EAST;
            if (pX != cX && pX - 1 == cX) return BlockFace.WEST;
        }

        if (pX == cX && pZ == cZ) {
            if (pY != cY && pY + 1 == cY) return BlockFace.UP;
            if (pY != cY && pY - 1 == cY) return BlockFace.DOWN;
        }

        return null;
    }

    public static BlockFace getCardinalDirection(float yaw) {
        double rotation = (yaw - 90.0F) % 360.0F;
        if (rotation < 0.0D) rotation += 360.0D;

        if (0.0D <= rotation && rotation < 45.0D)
            return BlockFace.WEST;

        if (45.0D <= rotation && rotation < 135.0D)
            return BlockFace.NORTH;

        if (135D <= rotation && rotation < 225.0D)
            return BlockFace.EAST;

        if (225.0D <= rotation && rotation < 315.0D)
            return BlockFace.SOUTH;

        if (315.0D <= rotation && rotation < 360.0D)
            return BlockFace.WEST;

        return null;
    }

    public static float getYawFromBlockFace(BlockFace face) {
        switch (face) {
            case WEST: return 90.0F;
            case NORTH: return 180.0F;
            case EAST: return -90.0F;
            case SOUTH: return 0.0F;
        }

        return 0.0F;
    }

    public static float getYawFromBlockFace360(BlockFace face) {
        float yaw = 0.0F;

        switch (face) {
            case WEST: yaw = 90.0F; break;
            case NORTH: yaw = 180.0F; break;
            case EAST: yaw = -90.0F; break;
            case SOUTH: yaw = 0.0F; break;
        }

        float rotation = (yaw - 90.0F) % 360.0F;
        if (rotation < 0.0D) rotation += 360.0D;

        return rotation;
    }

    public static BlockFace getOppositeDirection(BlockFace face) {
        switch (face) {
            case WEST: return BlockFace.EAST;
            case NORTH: return BlockFace.SOUTH;
            case EAST: return BlockFace.WEST;
            case SOUTH: return BlockFace.NORTH;
        }

        return BlockFace.NORTH;
    }
}
