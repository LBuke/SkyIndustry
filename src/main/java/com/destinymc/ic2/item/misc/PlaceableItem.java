package com.destinymc.ic2.item.misc;

import com.destinymc.ic2.item.IC2Block;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created at 21/01/2019
 * <p>
 * @author Luke Bingham
 */
public interface PlaceableItem extends ItemAttribute {

    int getIdentifier();

    IC2Block getBlockItem();

    boolean construct(Player player, Location location);
}
