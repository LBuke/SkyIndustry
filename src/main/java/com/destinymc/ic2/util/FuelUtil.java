package com.destinymc.ic2.util;

import com.destinymc.ic2.util.item.BaseItem;
import com.destinymc.ic2.util.item.IItem;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Created at 06/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class FuelUtil {

    private static Map<IItem, Double> FUEL_MAP = Maps.newHashMap();

    static {

        // IC2 ITEMS
//        FUEL_MAP.put(new BaseItem(SCRAP), 1.74D);
//        FUEL_MAP.put(new BaseItem(RUBBER_SAPLING), 0.4D);
//        FUEL_MAP.put(new BaseItem(Fuel can with 6 Biofuel Cells), 52.08D);
//        FUEL_MAP.put(new BaseItem(Fuel can with 6 Coalfuel Cells), 152.88D);


        // VANILLA ITEMS
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LAVA_BUCKET)), 3846.15384615D);//100
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.COAL_BLOCK)), 3076.92307692D);//80
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BLAZE_ROD)), 461.538461538D);//12
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.COAL)), 307.692307692D);//8
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.COAL, (byte) 1)), 307.692307692D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOAT)), 76.9230769231D);//2
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOAT_ACACIA)), 76.9230769231D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOAT_BIRCH)), 76.9230769231D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOAT_DARK_OAK)), 76.9230769231D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOAT_JUNGLE)), 76.9230769231D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOAT_SPRUCE)), 76.9230769231D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LOG)), 57.6923076923D);//1.5
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LOG, (byte) 1)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LOG, (byte) 2)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LOG, (byte) 3)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LOG_2)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LOG_2, (byte) 1)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD, (byte) 1)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD, (byte) 2)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD, (byte) 3)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD, (byte) 4)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD, (byte) 5)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_PLATE)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.FENCE)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.ACACIA_FENCE)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BIRCH_FENCE)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.DARK_OAK_FENCE)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.JUNGLE_FENCE)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SPRUCE_FENCE)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STAIRS)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.ACACIA_STAIRS)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BIRCH_WOOD_STAIRS)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.DARK_OAK_STAIRS)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.JUNGLE_WOOD_STAIRS)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SPRUCE_WOOD_STAIRS)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.TRAP_DOOR)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WORKBENCH)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOOKSHELF)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CHEST)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.TRAPPED_CHEST)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.DAYLIGHT_DETECTOR)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.JUKEBOX)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.NOTE_BLOCK)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BANNER)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STEP)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STEP, (byte) 1)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STEP, (byte) 2)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STEP, (byte) 3)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STEP, (byte) 4)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_STEP, (byte) 5)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOW)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.FISHING_ROD)), 57.6923076923D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.LADDER)), 57.6923076923D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_PICKAXE)), 38.4615384615D);//1
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_SPADE)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_HOE)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_AXE)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_SWORD)), 38.4615384615D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SIGN)), 38.4615384615D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOODEN_DOOR)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.ACACIA_DOOR_ITEM)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BIRCH_DOOR_ITEM)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.DARK_OAK_DOOR_ITEM)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.JUNGLE_DOOR_ITEM)), 38.4615384615D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SPRUCE_DOOR_ITEM)), 38.4615384615D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SAPLING)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SAPLING, (byte) 1)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SAPLING, (byte) 2)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SAPLING, (byte) 3)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SAPLING, (byte) 4)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.SAPLING, (byte) 5)), 19.2307692308D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.BOWL)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.STICK)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOD_BUTTON)), 19.2307692308D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 1)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 2)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 3)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 4)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 5)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 6)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 7)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 8)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 9)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 10)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 11)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 12)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 13)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 14)), 19.2307692308D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.WOOL, (byte) 15)), 19.2307692308D);

        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET)), 12.8846153846D);//0.335
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 1)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 2)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 3)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 4)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 5)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 6)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 7)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 8)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 9)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 10)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 11)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 12)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 13)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 14)), 12.8846153846D);
        FUEL_MAP.put(new BaseItem(new ItemStack(Material.CARPET, (byte) 15)), 12.8846153846D);
    }

    public static double getFuelPerInterval(ItemStack item) {
        for (Map.Entry<IItem, Double> entry : FUEL_MAP.entrySet()) {
            if (!entry.getKey().isMatch(item))
                continue;

            return entry.getValue();
        }

        return 0D;
    }
}
