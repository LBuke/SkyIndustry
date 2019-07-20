package com.destinymc.ic2.cable;

import org.bukkit.Material;

/**
 * Created at 13/02/2019
 * <p>
 * @author Luke Bingham
 */
public enum CableType {

    FLUID(1, 0, Material.DIAMOND_AXE),

    EU(2, 0, Material.DIAMOND_AXE),

    ;

    private final int id;
    private final int texture;
    private final Material material;

    CableType(int id, int texture, Material material) {
        this.id = id;
        this.texture = texture;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public int getTexture() {
        return texture;
    }

    public Material getMaterial() {
        return material;
    }

    public static CableType getById(int id) {
        for (CableType type : values()) {
            if (type.id == id) {
                return type;
            }
        }

        return FLUID;
    }
}
