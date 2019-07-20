package com.destinymc.ic2.machine.task;

import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.IC2ItemManager;
import com.destinymc.ic2.item.misc.PoweredItem;
import com.destinymc.ic2.machine.Machine;
import com.destinymc.ic2.machine.MachineManager;
import com.destinymc.ic2.machine.misc.process.ProcessMachine;
import com.destinymc.ic2.machine.misc.fueled.FueledMachine;
import com.destinymc.ic2.machine.misc.powered.MachinePowerData;
import com.destinymc.ic2.machine.misc.powered.PoweredMachine;
import com.destinymc.ic2.machine.types.MachineSolarGenerator;
import com.destinymc.ic2.util.FuelUtil;
import com.destinymc.packet.out.WrapperPlayServerSetSlot;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class MachineTask implements Runnable {

    private final MachineManager machineManager;
    private final IC2ItemManager itemManager;

    public MachineTask(MachineManager machineManager, IC2ItemManager itemManager) {
        this.machineManager = machineManager;
        this.itemManager = itemManager;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (Machine machine : machineManager.getMachines().values()) {
            if (machine.getInventory() == null)
                continue;

            if (!machine.getLocation().getChunk().isLoaded())
                continue;

            if (machine instanceof MachineSolarGenerator) {
                this.runSolarGenerator(machine, (MachineSolarGenerator) machine);
            }

            if (machine instanceof ProcessMachine) {
                this.runProcessMachine(machine, (ProcessMachine) machine);
            }

            if (machine instanceof PoweredMachine) {
                this.runPoweredMachine(machine, (PoweredMachine) machine);
            }

            if (machine instanceof FueledMachine) {
                this.runFueledMachine(machine, (FueledMachine) machine);
            }
        }
    }

    private boolean runPoweredMachine(Machine machine, PoweredMachine poweredMachine) {
        MachinePowerData data = poweredMachine.getPowerData();

        if (poweredMachine.doesAllowIntakeViaPoweredItem())
            runPoweredInputMachine(machine, poweredMachine, data);

        if (poweredMachine.doesAllowOuttakeViaPoweredItem())
            runPoweredOutputMachine(machine, poweredMachine, data);

        for (HumanEntity entity : machine.getInventory().getViewers()) {
            int windowId = ((CraftHumanEntity) entity).getHandle().activeContainer.windowId;

            for (int i = 0; i < data.getPowerSlots().length; i++) {
                WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot();
                packet.setWindowId(windowId);
                packet.setSlot(data.getPowerSlots()[i]);
                packet.setSlotData(machine.getInventory().getItem(data.getPowerSlots()[i]));

                packet.sendPacket((Player) entity);
            }
        }

        return false;
    }

    private boolean runPoweredInputMachine(Machine machine, PoweredMachine poweredMachine, MachinePowerData data) {

        if (data.isFull())
            return false;

        if (data.getInputSlot() < 0)
            return false;

        ItemStack slot3 = machine.getInventory().getItem(data.getInputSlot());
        IC2Item ic2Item = itemManager.getItem(slot3);
        if (ic2Item == null)
            return false;

        if (ic2Item instanceof PoweredItem) {
            PoweredItem poweredItem = (PoweredItem) ic2Item;
            if (poweredItem.isPowerEmpty(slot3))
                return false;

            long currentItemPower = poweredItem.getPower(slot3, ic2Item);

            if (poweredItem.getPowerOuttake() > data.getIntake()) {
                int amount = data.getIntake() * 5;
                if (currentItemPower - amount < 0) {
                    amount = (int) currentItemPower;
                } else if (data.getCurrent() + amount > data.getMaximumCapacity()) {
                    amount = data.getMaximumCapacity() - data.getCurrent();
                }

                poweredItem.setPower(slot3, ic2Item, currentItemPower - amount);
                data.setCurrent(data.getCurrent() + amount);
            } else {
                int amount = poweredItem.getPowerOuttake() * 5;
                if (currentItemPower - amount < 0) {
                    amount = (int) currentItemPower;
                } else if (data.getCurrent() + amount > data.getMaximumCapacity()) {
                    amount = data.getMaximumCapacity() - data.getCurrent();
                }

                poweredItem.setPower(slot3, ic2Item, currentItemPower - amount);
                data.setCurrent(data.getCurrent() + amount);
            }

            data.updateMachine(machine);

//            for (HumanEntity entity : machine.getInventory().getViewers()) {
//                int windowId = ((CraftHumanEntity) entity).getHandle().activeContainer.windowId;
//
//                for (int i = 0; i < data.getPowerSlots().length; i++) {
//                    WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot();
//                    packet.setWindowId(windowId);
//                    packet.setSlot(data.getPowerSlots()[i]);
//                    packet.setSlotData(machine.getInventory().getItem(data.getPowerSlots()[i]));
//
//                    packet.sendPacket((Player) entity);
//                }
//            }

            return true;
        }

        return false;
    }

    private boolean runPoweredOutputMachine(Machine machine, PoweredMachine poweredMachine, MachinePowerData data) {

        if (data.isEmpty())
            return false;

        if (data.getOutputSlot() < 0)
            return false;

        ItemStack slot3 = machine.getInventory().getItem(data.getOutputSlot());
        IC2Item ic2Item = itemManager.getItem(slot3);
        if (ic2Item == null)
            return false;

        if (ic2Item instanceof PoweredItem) {
            PoweredItem poweredItem = (PoweredItem) ic2Item;
            if (poweredItem.isPowerFull(slot3))
                return false;

            long currentItemPower = poweredItem.getPower(slot3, ic2Item);

//            if (poweredItem.getPowerIntake() > data.getOuttake()) {
//                int amount =  data.getOuttake() * 5;
//
//                if (currentItemPower + amount < poweredItem.getMaximumPowerCapacity()) {
//                    amount = (int) (currentItemPower + amount);
//                } else if (data.getCurrent() - amount > data.getMaximumCapacity()) {
//                    amount = data.getCurrent();
//                }
//
//                poweredItem.setPower(slot3, ic2Item, currentItemPower + amount);
//                data.setCurrent(data.getCurrent() - amount);
//            } else {
//                int amount = poweredItem.getPowerIntake() * 5;
//                if (currentItemPower + amount > data.getMaximumCapacity()) {
//                    amount = (int) currentItemPower;
//                } else if (data.getCurrent() - amount < 0) {
//                    amount = data.getCurrent();
//                }
//
//                poweredItem.setPower(slot3, ic2Item, currentItemPower + amount);
//                data.setCurrent(data.getCurrent() - amount);
//            }


            int amount = poweredItem.getPowerIntake() * 5;
            if (poweredItem.getPowerIntake() > data.getOuttake())
                amount =  data.getOuttake() * 5;

            if (data.getCurrent() - amount < 0) amount = data.getCurrent();
            if (currentItemPower + amount > poweredItem.getMaximumPowerCapacity()) amount = (int) (poweredItem.getMaximumPowerCapacity() - currentItemPower);

            poweredItem.setPower(slot3, ic2Item, currentItemPower + amount);
            data.setCurrent(data.getCurrent() - amount);

            data.updateMachine(machine);

            return true;
        }

        return false;
    }

    private boolean runFueledMachine(Machine machine, FueledMachine fueledMachine) {
        int fuel = fueledMachine.getFuel(machine);

        if (!fueledMachine.isEmpty(fuel)) {
            fueledMachine.drain(machine);
            return true;
        }

        if (!fueledMachine.canRefill(machine))
            return false;

        ItemStack input = machine.getInventory().getItem(fueledMachine.getFuelSlot());
        if (input == null || input.getType() == Material.AIR)
            return false;

        double fuelWorth = FuelUtil.getFuelPerInterval(input);

        if (input.getAmount() > 1) {
            input.setAmount(input.getAmount() - 1);
        } else {
            machine.getInventory().setItem(fueledMachine.getFuelSlot(), new ItemStack(Material.AIR));
        }

        fueledMachine.refill(machine, fuelWorth);
        return true;
    }

    public boolean runProcessMachine(Machine machine, ProcessMachine processMachine) {
        int process = processMachine.getProcess(machine);

        if (processMachine.getRecipe() == null) {
            return processMachine.start(machine);
        }

        processMachine.next(machine);

        return true;
    }

    public boolean runSolarGenerator(Machine machine, MachineSolarGenerator solarGenerator) {
        Location location = machine.getLocation();
        if (location == null) return false;

        World world = location.getWorld();
        if (world == null) return false;

        int slot = solarGenerator.getObjectSlots()[0];

        if (world.getTime() >= 0 && world.getTime() <= 12000) {
            solarGenerator.setObjectItem(machine, slot, 0);

            MachinePowerData data = solarGenerator.getPowerData();
            if (data == null) return true;

            if (data.getCurrent() > data.getMaximumCapacity())
                return true;

            int amount = data.getIntake();
            if (data.getCurrent() + amount > data.getMaximumCapacity())
                amount = data.getMaximumCapacity() - data.getCurrent();

            data.setCurrent(data.getCurrent() + amount);
        }
        else {
            solarGenerator.setObjectItem(machine, slot, 1);
        }

        return true;
    }
}
