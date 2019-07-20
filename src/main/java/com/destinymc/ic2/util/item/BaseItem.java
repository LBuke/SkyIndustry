package com.destinymc.ic2.util.item;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.item.IC2Item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 19/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class BaseItem implements IItem {

    private final ItemStack itemStack;
    private final IC2Item ic2Item;

    private final ItemType itemType;

    private BaseItem(ItemStack itemStack, IC2Item ic2Item) {
        if (itemStack == null && ic2Item == null) {
            this.itemStack = new ItemStack(Material.AIR);
            this.ic2Item = null;
        } else {
            this.itemStack = itemStack;
            this.ic2Item = ic2Item;
        }

        this.itemType = this.itemStack == null ? ItemType.IC2 : ItemType.VANILLA;
    }

    public BaseItem(ItemStack itemStack) {
        this(itemStack, null);
    }

    public BaseItem(IC2Item ic2Item) {
        this(null, ic2Item);
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public IC2Item getIc2Item() {
        return ic2Item;
    }

    @Override
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public ItemStack toItem() {
        if (itemStack != null)
            return itemStack.clone();

        if (ic2Item != null)
            return ic2Item.clone();

        return new ItemStack(Material.AIR);
    }

    @Override
    public boolean isMatch(ItemStack var) {
        switch (itemType) {
            case VANILLA:
                if (itemStack == null)
                    break;

                if (var == null && itemStack.getType() == Material.AIR)
                    return true;

                if (var == null)
                    break;

                if (itemStack.getType() != var.getType())
                    break;

                if (itemStack.getDurability() != var.getDurability())
                    break;

                if (itemStack.getItemMeta().isUnbreakable() != var.getItemMeta().isUnbreakable())
                    break;

                return true;

            case IC2:
                if (ic2Item == null)
                    break;

                if (var == null)
                    break;

                if (ic2Item.getType() != var.getType())
                    break;

                if (ic2Item.getDurability() != var.getDurability())
                    break;

                if (ic2Item.getItemMeta().isUnbreakable() != var.getItemMeta().isUnbreakable())
                    break;

                // Get the identifier of the IC2 item.
                int id = ItemFactory.getIntNBTTag(var, IC2Item.META);

                // Check if the id isn't -1 AND if the item filter id matches.
                if (id == -1 && id != ic2Item.getIdentifier())
                    break;

                return true;
        }

        return false;
    }
}
