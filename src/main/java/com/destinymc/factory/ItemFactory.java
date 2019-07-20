package com.destinymc.factory;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagLong;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created at 10/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class ItemFactory implements Factory<ItemStack> {

    private ItemStack object;
    private ItemMeta itemMeta;

    public ItemFactory(Material material, byte data) {
        this.object = new ItemStack(material, 1, data);
        this.itemMeta = object.getItemMeta();
    }

    public ItemFactory(Material material, short data) {
        this.object = new ItemStack(material, 1);
        this.object.setDurability(data);
        this.itemMeta = object.getItemMeta();
    }

    public ItemFactory(Material material) {
        this(material, (byte) 0);
    }

    public ItemFactory(ItemStack itemStack) {
        this.object = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public final ItemFactory setAmount(int amount) {
        if (amount > 64) amount = 64;
        object.setAmount(amount);
        return this;
    }

    public final ItemFactory setName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public final ItemFactory setLore(List<String> lore) {
        itemMeta.setLore(lore);
        return this;
    }

    public final ItemFactory setLore(Queue<String> lore) {
        itemMeta.setLore((LinkedList<String>) lore);
        return this;
    }

    public final ItemFactory setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public final ItemFactory setOwner(String name) {
        if (object.getType().equals(Material.SKULL_ITEM)) {
            SkullMeta meta = (SkullMeta) itemMeta;
            meta.setOwner(name);
        }
        return this;
    }

    public final ItemFactory setDurability(short durability) {
        object.setDurability(durability);
        return this;
    }

    public final ItemFactory setData(byte data) {
        object.setDurability(data);
        return this;
    }

    public final ItemFactory setUnsafeEnchantment(Enchantment enchantment, int level) {
        object.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public final ItemFactory setEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public final ItemFactory addFlags(ItemFlag... flags) {
        itemMeta.addItemFlags(flags);
        return this;
    }

    public ItemFactory setGlowing(boolean glowing) {
        this.itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemFactory setUnbreaking(boolean value) {
        this.itemMeta.setUnbreakable(value);
        return this;
    }

    @Override
    public final ItemStack build() {
        object.setItemMeta(itemMeta);
        return object;
    }

    @Override
    public final ItemFactory clone() {
        ItemFactory clone = new ItemFactory(object.getType(), object.getData().getData());
        clone.itemMeta = this.itemMeta;
        return clone;
    }

    public static ItemStack setNBTTag(ItemStack itemStack, String key, int value) {
        net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = (item.hasTag()) ? item.getTag() : new NBTTagCompound();
        compound.set(key, new NBTTagInt(value));
        item.setTag(compound);

        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack setNBTTag(ItemStack itemStack, String key, long value) {
        net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = (item.hasTag()) ? item.getTag() : new NBTTagCompound();
        compound.set(key, new NBTTagLong(value));
        item.setTag(compound);

        return CraftItemStack.asBukkitCopy(item);
    }

    public static int getIntNBTTag(ItemStack itemStack, String key) {
        net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = item.getTag();

        if (compound == null || !compound.hasKey(key)) {
            return -1;
        }

        return Integer.parseInt(compound.get(key).toString());
    }

    public static long getLongNBTTag(ItemStack itemStack, String key) {
        net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = item.getTag();

        if (compound == null || !compound.hasKey(key)) {
            return -1;
        }

        return Long.parseLong(compound.get(key).toString().replaceAll("L", ""));
    }

    public static boolean hasNBTTag(ItemStack itemStack, String key) {
        net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = item.getTag();
        if (compound == null) return false;

        return compound.hasKey(key);
    }
}
