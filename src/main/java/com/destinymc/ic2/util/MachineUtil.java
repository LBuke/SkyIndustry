package com.destinymc.ic2.util;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.Machine;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineUtil {

    public static final String MACHINE_META = "MACHINE";
    public static final String MACHINE_FACE_META = "MACHINE_FACE";

    public static final String SHULKER_ID_META = "S_ID";
    public static final String SHULKER_SIZE_META = "S_SIZE";
    public static final String SHULKER_USABLE_META = "S_USE";

    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DECIMAL_FORMAT = new DecimalFormat("#,###");
    }

    public static void setInnerContents(Machine machine, Container container) {

        int[] size = new int[3];
        ItemStack[] contents = new ItemStack[3];

        switch (machine.getRows()) {
            case 1:
                size[0] = 1;
                size[1] = 0;
                size[2] = 0;
                break;
            case 2:
                size[0] = 2;
                size[1] = 0;
                size[2] = 0;
                break;
            case 3:
                size[0] = 3;
                size[1] = 0;
                size[2] = 0;
                break;

            case 4:
                size[0] = 3;
                size[1] = 1;
                size[2] = 0;
                break;
            case 5:
                size[0] = 3;
                size[1] = 2;
                size[2] = 0;
                break;
            case 6:
                size[0] = 3;
                size[1] = 3;
                size[2] = 0;
                break;

            case 7:
                size[0] = 3;
                size[1] = 3;
                size[2] = 1;
                break;
            case 8:
                size[0] = 3;
                size[1] = 3;
                size[2] = 2;
                break;
            case 9:
                size[0] = 3;
                size[1] = 3;
                size[2] = 3;
                break;

            default:
                size[0] = 1;
                break;
        }

        for (int i = 0; i < size.length; i++) {
            contents[i] = constructShulker(machine.getIdentifier(), i, size[i]);
        }

        container.getInventory().setContents(contents);
    }

    private static ItemStack constructShulker(long machineId, int id, int size) {
        ItemStack item = new ItemStack(Material.BLACK_SHULKER_BOX);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(SHULKER_SIZE_META + " : " + size + "x9");
        item.setItemMeta(meta);

        item = ItemFactory.setNBTTag(item, MACHINE_META, machineId);

        item = ItemFactory.setNBTTag(item, SHULKER_ID_META, id);
        item = ItemFactory.setNBTTag(item, SHULKER_SIZE_META, size);
        item = ItemFactory.setNBTTag(item, SHULKER_USABLE_META, size > 0 ? 1 : 0);

        return item;
    }

    public static String getFormattedPower(long power) {
        return DECIMAL_FORMAT.format(power);
    }
}
