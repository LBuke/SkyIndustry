package com.destinymc.ic2.machine.misc;

import org.bukkit.Material;

/**
 * Created at 16/01/2019
 * <p>
 * @author Luke Bingham
 */
public enum MachineTileType {

    BAR_1X3(1, 46, Material.DIAMOND_HOE, 52),

    FUEL_FLAME(2, 13, Material.DIAMOND_HOE, 152),

    FLUID_LAVA(3, 23, Material.DIAMOND_HOE, 106),

    PROCESS(4, 21, Material.DIAMOND_HOE, 130),

    POWER(5, 14, Material.DIAMOND_HOE, 166),
    ;

    private final int id;
    private final int textures;
    private final Material material;
    private final int textureStart;

    MachineTileType(int id, int textures, Material material, int textureStart) {
        this.id = id;
        this.textures = textures;
        this.material = material;
        this.textureStart = textureStart;
    }

    public int getId() {
        return id;
    }

    public short getDurability(double value, double goal) {
        int i = (int) Math.round(value >= goal ? textures : textures - Math.abs((goal - value) / goal * textures));
        return (short) (i + textureStart);
    }

    public int getTextures() {
        return textures;
    }

    public int getTextureStart() {
        return textureStart;
    }

    public Material getMaterial() {
        return material;
    }

    public static MachineTileType getById(int id) {
        for (MachineTileType type : MachineTileType.values()) {
            if (type.id != id) continue;
            return type;
        }

        return BAR_1X3;
    }
}
