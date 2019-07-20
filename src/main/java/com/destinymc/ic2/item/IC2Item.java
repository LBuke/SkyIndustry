package com.destinymc.ic2.item;

import com.destinymc.factory.ItemFactory;
import com.destinymc.util.C;
import com.destinymc.util.Log;
import com.destinymc.util.Pair;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class IC2Item extends ItemStack {

    public static final String META = "IC2ITEM";

    private final int identifier;
    private final String name;

    public IC2Item(int amount, int identifier, String name) {
        super(Material.DIAMOND_PICKAXE, amount, (short) identifier);
        this.identifier = identifier;
        this.name = name;

        ItemMeta meta = super.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        meta.setDisplayName("{t=" + name.replaceAll(" ", "") + "}");//C.WHITE + name
        meta.setLore(Collections.singletonList(C.DARK_GRAY + C.ITALIC + "IC2"));
        meta.setUnbreakable(true);
        super.setItemMeta(meta);

        ItemStack cloned = ItemFactory.setNBTTag(this, META, identifier);
        super.setItemMeta(cloned.getItemMeta());
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    protected static <T extends IC2Item> void register(T item) {
        IC2ItemManager.ITEM_MAP.put(new Pair<>(item.getData().getItemType(), item.getIdentifier()), item);
        Log.register("IC2 Item : " + item.getName());
    }
}
