package com.destinymc.ic2.machine.misc.powered;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.misc.MachineTileType;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.util.C;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 16/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class MachinePowerData {

    private MachineTileType powerType;

    private int[] powerSlots;
    private int inputSlot = -1, outputSlot = -1;

    private int intake, outtake;
    private int maximumCapacity;

    private int current = 0;

    public MachinePowerData(int current) {
        this.current = current;
    }

    public MachineTileType getPowerType() {
        return powerType;
    }

    public void setPowerType(MachineTileType powerType) {
        this.powerType = powerType;
    }

    public int[] getPowerSlots() {
        return powerSlots;
    }

    public void setPowerSlots(int[] powerSlots) {
        this.powerSlots = powerSlots;
    }

    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    public int getOuttake() {
        return outtake;
    }

    public void setOuttake(int outtake) {
        this.outtake = outtake;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getInputSlot() {
        return inputSlot;
    }

    public void setInputSlot(int inputSlot) {
        this.inputSlot = inputSlot;
    }

    public int getOutputSlot() {
        return outputSlot;
    }

    public void setOutputSlot(int outputSlot) {
        this.outputSlot = outputSlot;
    }

    public boolean isFull() {
        return current >= maximumCapacity;
    }

    public boolean isEmpty() {
        return current <= 0;
    }

    public ItemFactory getTexturedItem() {
        return new ItemFactory(powerType.getMaterial())
                .setDurability(powerType.getDurability(current, maximumCapacity))
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
                .setUnbreaking(true);
    }

    public void updateMachine(Machine machine) {
        for (int slot : getPowerSlots()) {
            ItemStack itemStack = machine.getInventory().getItem(slot);
            if (itemStack == null || itemStack.getType() == Material.AIR)
                continue;

            ItemFactory foundItem = new ItemFactory(itemStack);

            foundItem.setName(C.YELLOW + C.BOLD + MachineUtil.getFormattedPower(getCurrent()) + C.RESET + " / " + C.YELLOW + C.BOLD + MachineUtil.getFormattedPower(getMaximumCapacity()));

            if (slot == getPowerSlots()[0]) {
                foundItem.setDurability(getPowerType().getDurability(getCurrent(), getMaximumCapacity()));
            }

            foundItem.build();
        }
    }

//    public static void main(String[] args) {
//        MachinePowerData data = new MachinePowerData(MachineTileType.BAR_1X3, 40000, 0);
//        //47
//
//        double value = data.maximumCapacity;
//        double goal = data.maximumCapacity;
//        int bars = 47;
//
//        int i = (int) Math.round(value >= goal ? bars : bars - Math.abs((goal - value) / goal * bars));
//
//        System.out.println("Value: " + value);
//        System.out.println("Texture: " + i);
//    }
}
