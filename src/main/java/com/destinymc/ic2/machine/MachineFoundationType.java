package com.destinymc.ic2.machine;

import org.bukkit.Material;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public enum MachineFoundationType {

    STONE(Material.BLACK_SHULKER_BOX),
    WOOD(Material.CHEST),;

    private final Material blockMaterial;

    MachineFoundationType(Material blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }
}
