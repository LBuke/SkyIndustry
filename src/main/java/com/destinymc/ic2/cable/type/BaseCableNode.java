package com.destinymc.ic2.cable.type;

import com.destinymc.ic2.cable.CableShapeType;
import com.destinymc.ic2.cable.CableType;
import com.destinymc.misc.Unfinished;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import static org.bukkit.block.BlockFace.*;

/**
 * Created at 13/02/2019
 * <p>
 * @author Luke Bingham
 */
@Unfinished
public final class BaseCableNode implements CableNode {

    /* each island cable has a unique id */
    private long uniqueIdentifier;

    /* this is a position id, in theory it's -1 from it's parent's */
    private int cablePositionIdentifier;

    /* relatives which are existing based on directional connections */
    private final BlockFace[] relatives = new BlockFace[] { SELF, SELF, SELF, SELF, SELF, SELF };

    /* Type of the cable contents */
    private CableType content;

    /* Shape of the cable, changes when needed */
    private CableShapeType shape;

    /* Location of the holding block */
    private final Location location;

    public BaseCableNode(long uniqueIdentifier, int cablePositionIdentifier, CableType content, CableShapeType shape, Location location) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.cablePositionIdentifier = cablePositionIdentifier;
        this.content = content;
        this.shape = shape;
        this.location = location;
    }

    /**
     * @return Unique Identifier
     */
    @Override
    public Long getUniqueId() {
        return uniqueIdentifier;
    }

    @Override
    public int getCablePositionId() {
        return cablePositionIdentifier;
    }

    @Override
    public BlockFace[] getRelativeFaces() {
        return relatives;
    }

    @Override
    public boolean hasRelative(BlockFace face) {
        int i = -1;

        switch (face) {
            case UP:    i = 0; break;
            case DOWN:  i = 1; break;
            case NORTH: i = 2; break;
            case EAST:  i = 3; break;
            case SOUTH: i = 4; break;
            case WEST:  i = 5; break;
            default: break;
        }

        return i != 1 && relatives[i] != SELF;
    }

    @Override
    public BlockFace getOppositeFace(BlockFace face) {
        switch (face) {
            case UP:    return DOWN;
            case DOWN:  return UP;
            case NORTH: return SOUTH;
            case EAST:  return WEST;
            case SOUTH: return NORTH;
            case WEST:  return EAST;

            default: return SELF;
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public CableType getCableType() {
        return content;
    }

    @Override
    public void setShapeType(CableType type) {
        content = type;
    }

    @Override
    public CableShapeType getShapeType() {
        return shape;
    }

    @Override
    public void setShapeType(CableShapeType shape) {
        this.shape = shape;
    }
}
