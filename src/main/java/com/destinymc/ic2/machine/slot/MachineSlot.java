package com.destinymc.ic2.machine.slot;

import com.destinymc.ic2.util.item.ItemType;
import org.bukkit.Material;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public class MachineSlot {

    private final Material material;
    private final short identifier;

    private boolean requireUnbreakable = true;
    private boolean ignoreDurability = false;

    private ItemType itemType = ItemType.IC2;

    public MachineSlot(Material material) {
        this(material, (short) 0);
    }

    public MachineSlot(Material material, int identifier) {
        this.material = material;
        this.identifier = (short) identifier;
    }

    public Material getMaterial() {
        return material;
    }

    public short getIdentifier() {
        return identifier;
    }

    public boolean isRequireUnbreakable() {
        return requireUnbreakable;
    }

    public MachineSlot setRequireUnbreakable(boolean requireUnbreakable) {
        this.requireUnbreakable = requireUnbreakable;
        return this;
    }

    public boolean isIgnoreDurability() {
        return ignoreDurability;
    }

    public MachineSlot setIgnoreDurability(boolean ignoreDurability) {
        this.ignoreDurability = ignoreDurability;
        return this;
    }

    public ItemType getItemType() {
        return itemType;
    }

    protected void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public static class VanillaItem extends MachineSlot {

        public VanillaItem(Material material) {
            super(material);

            setIgnoreDurability(true);
            setRequireUnbreakable(false);
            setItemType(ItemType.VANILLA);
        }
    }
}
