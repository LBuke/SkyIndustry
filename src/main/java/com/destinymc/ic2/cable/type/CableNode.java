package com.destinymc.ic2.cable.type;

import com.destinymc.ic2.cable.CableShapeType;
import com.destinymc.ic2.cable.CableType;
import com.destinymc.misc.Unique;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

/**
 * Created at 13/02/2019
 * <p>
 * @author Luke Bingham
 */
public interface CableNode extends Unique<Long> {

    int getCablePositionId();

    BlockFace[] getRelativeFaces();
    boolean hasRelative(BlockFace face);
    BlockFace getOppositeFace(BlockFace face);

    Location getLocation();

    CableType getCableType();
    void setShapeType(CableType type);

    CableShapeType getShapeType();
    void setShapeType(CableShapeType shape);
}
