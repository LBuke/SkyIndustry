package com.destinymc.ic2.machine;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.machine.event.MachineInnerInitialiseEvent;
import com.destinymc.ic2.machine.slot.MachineSlot;
import com.destinymc.ic2.machine.visual.BaseVisualMachine;
import com.destinymc.ic2.machine.visual.VisualMachine;
import com.destinymc.ic2.util.item.ItemType;
import com.destinymc.util.LocationUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class BaseMachine implements Machine {

    private final Set<Integer> blockedSlots;
    private final HashMap<MachineSlot[], int[]> slotData;

    private final long identifier;
    private final String name;
    private final String[] description;
    private final byte rows;
    private final short texture;
    private final short tile;

    private Location location;
    private Inventory inventory;

    private final MachineFoundationType foundationType;
    private BlockState blockState;

    private VisualMachine visualMachine;

    public BaseMachine(long identifier, Location location, String name, String[] description, int rows, int texture, int tile, MachineFoundationType foundationType) {
        this.identifier = identifier;
        this.location = location;
        this.name = name;
        this.description = description;
        this.rows = (byte) rows;
        this.texture = (short) texture;
        this.tile = (short) tile;
        this.foundationType = foundationType;

        this.visualMachine = new BaseVisualMachine(this);

        this.blockedSlots = Sets.newHashSet();
        this.slotData = Maps.newHashMap();
    }

    @Override
    public long getIdentifier() {
        return identifier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getDescription() {
        return description;
    }

    @Override
    public byte getRows() {
        return rows;
    }

    @Override
    public short getTexture() {
        return texture;
    }

    @Override
    public short getTile() {
        return tile;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Inventory getInventory() {
        if (inventory == null) {
            this.inventory = Bukkit.createInventory((InventoryHolder) this.getBlockState(), rows * 9, name);

            MachineInnerInitialiseEvent innerInitialiseEvent = new MachineInnerInitialiseEvent(this);
            Bukkit.getPluginManager().callEvent(innerInitialiseEvent);
        }

        return inventory;
    }

    @Override
    public MachineFoundationType getMachineFoundationType() {
        return foundationType;
    }

    @Override
    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    public VisualMachine getVisualMachine() {
        return visualMachine;
    }

    public void addBlockedSlot(int slot) {
        blockedSlots.add(slot);
    }

    public void addBlockedSlots(int[] slots) {
        for (int i : slots) {
            blockedSlots.add(i);
        }
    }

    @Override
    public Set<Integer> getBlockedSlots() {
        return blockedSlots;
    }

    @Override
    public boolean isBlockedSlot(int slot) {
        return blockedSlots.contains(slot);
    }

    @Override
    public HashMap<MachineSlot[], int[]> getSlotDataMap() {
        return slotData;
    }

    public void addSlotData(MachineSlot[] data, int[] slots) {
        slotData.put(data, slots);
    }

    @Override
    public int[] getAcceptSlots(ItemStack itemStack) {
        if (itemStack == null)
            return null;

        return slotData.entrySet().stream().filter(entry -> itemMatch(itemStack, entry.getKey())).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    private boolean itemMatch(ItemStack itemStack, MachineSlot[] machineSlotArray) {
        ItemMeta meta = itemStack.getItemMeta();

        for (MachineSlot machineSlot : machineSlotArray) {

            // Check if the Material Type matches.
            if (itemStack.getType() != machineSlot.getMaterial())
                continue;

            // Check if the unbreakable status matches.
            if (machineSlot.isRequireUnbreakable() != meta.isUnbreakable())
                continue;

            // Check if the Item Type is Vanilla based for durability checking.
            // IC2 cannot be checked due to durability being it's identifier.
            if (machineSlot.getItemType() == ItemType.VANILLA) {
                if (machineSlot.isIgnoreDurability())
                    return true;

                if (itemStack.getDurability() == machineSlot.getIdentifier())
                    return true;

                continue;
            }

            // Get the identifier of the IC2 item.
            int id = ItemFactory.getIntNBTTag(itemStack, IC2Item.META);

            // Check if the id isn't -1 AND if the item filter id matches.
            if (id == -1 && id != machineSlot.getIdentifier())
                continue;

            return true;
        }

        return false;
    }

    @Override
    public BlockFace getFaceDirection() {
        if (this.visualMachine == null)
            return null;

        if (this.visualMachine.getArmorStand() == null)
            return null;

        return LocationUtil.getCardinalDirection(this.visualMachine.getArmorStand().getLocation().getYaw());
    }

    @Override
    public void setFaceDirection(BlockFace face) {
        if (this.visualMachine == null)
            return;

        if (this.visualMachine.getArmorStand() == null)
            return;

        double y = Math.toRadians(LocationUtil.getYawFromBlockFace(face));
        this.visualMachine.getArmorStand().setHeadPose(new EulerAngle(0, y, 0));
    }
}
