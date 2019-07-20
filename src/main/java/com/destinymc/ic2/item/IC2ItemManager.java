package com.destinymc.ic2.item;

import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.item.type.Rubber;
import com.destinymc.ic2.item.type.cable.*;
import com.destinymc.ic2.item.type.carbon.CarbonFibers;
import com.destinymc.ic2.item.type.carbon.CarbonPlate;
import com.destinymc.ic2.item.type.carbon.CombinedCarbonFibers;
import com.destinymc.ic2.item.type.casing.*;
import com.destinymc.ic2.item.type.dust.CoalDust;
import com.destinymc.ic2.item.type.electronic.AdvancedElectronicCircuit;
import com.destinymc.ic2.item.type.electronic.ElectronicCircuit;
import com.destinymc.ic2.item.type.eu.*;
import com.destinymc.ic2.item.type.ingot.*;
import com.destinymc.ic2.item.type.ore.*;
import com.destinymc.ic2.item.type.plate.*;
import com.destinymc.ic2.item.type.tool.ForgeHammer;
import com.destinymc.ic2.item.type.tool.InsulationCutter;
import com.destinymc.ic2.machine.item.*;
import com.destinymc.util.Log;
import com.destinymc.util.Pair;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created at 17/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class IC2ItemManager {

    protected static final HashMap<Pair<Material, Integer>, IC2Item> ITEM_MAP;

    static {
        ITEM_MAP = Maps.newHashMap();
    }

    public IC2ItemManager(JavaPlugin plugin) {
    }

    public void register() {

        // INGOT
        IC2Item.register(new MixedMetalIngot());
        IC2Item.register(new BronzeIngot());
        IC2Item.register(new CopperIngot());
        IC2Item.register(new LeadIngot());
        IC2Item.register(new RefinedIronIngot());
        IC2Item.register(new SilverIngot());
        IC2Item.register(new SteelIngot());
        IC2Item.register(new TinIngot());
        IC2Item.register(new UraniumIngot());

        // CASING
        IC2Item.register(new BronzeCasing());
        IC2Item.register(new CopperCasing());
        IC2Item.register(new GoldCasing());
        IC2Item.register(new IronCasing());
        IC2Item.register(new LeadCasing());
        IC2Item.register(new SteelCasing());
        IC2Item.register(new TinCasing());

        // PLATE
        IC2Item.register(new BronzePlate());
        IC2Item.register(new CopperPlate());
        IC2Item.register(new GoldPlate());
        IC2Item.register(new IronPlate());
        IC2Item.register(new LapisPlate());
        IC2Item.register(new LeadPlate());
        IC2Item.register(new ObsidianPlate());
        IC2Item.register(new SteelPlate());
        IC2Item.register(new TinPlate());
        IC2Item.register(new AdvancedAlloy());

        // DUST
        IC2Item.register(new CoalDust());

        // CABLE
        IC2Item.register(new CopperCable());
        IC2Item.register(new InsulatedCopperCable());
        IC2Item.register(new DetectorCable());
        IC2Item.register(new GlassFibreCable());
        IC2Item.register(new GoldCable());
        IC2Item.register(new InsulatedGoldCable());
        IC2Item.register(new GoldCable2xIns());
        IC2Item.register(new HVCable());
        IC2Item.register(new InsulatedHVCable());
        IC2Item.register(new HVCable2xIns());
        IC2Item.register(new HVCable3xIns());
        IC2Item.register(new SplitterCable());
        IC2Item.register(new TinCable());
        IC2Item.register(new InsulatedTinCable());
        IC2Item.register(new CableCasing());

        // TOOLS
        IC2Item.register(new InsulationCutter());
        IC2Item.register(new ForgeHammer());

        // EU
        IC2Item.register(new SingleUseBattery());
        IC2Item.register(new REBattery());
        IC2Item.register(new AdvancedREBattery());
        IC2Item.register(new EnergyCrystal());
        IC2Item.register(new LapotronCrystal());

        // UNKNOWN CATEGORY
        IC2Item.register(new Rubber());
        IC2Item.register(new ElectronicCircuit());
        IC2Item.register(new AdvancedElectronicCircuit());
        IC2Item.register(new CarbonFibers());
        IC2Item.register(new CombinedCarbonFibers());
        IC2Item.register(new CarbonPlate());

        // CUSTOM BLOCKS
        IC2Item.register(new BasicMachineCasing());
        IC2Item.register(new AdvancedMachineCasing());
        IC2Item.register(new BronzeBlock());
        IC2Item.register(new CopperBlock());
        IC2Item.register(new CopperOre());
        IC2Item.register(new LeadBlock());
        IC2Item.register(new LeadOre());
        IC2Item.register(new SilverBlock());
        IC2Item.register(new SilverOre());
        IC2Item.register(new SteelBlock());
        IC2Item.register(new TinBlock());
        IC2Item.register(new TinOre());
        IC2Item.register(new UraniumBlock());
        IC2Item.register(new UraniumOre());

        // INTRACTABLE BLOCKS
        IC2Item.register(new BatBox());
        IC2Item.register(new MFEUnit());
        IC2Item.register(new MFSUnit());
        IC2Item.register(new Generator());
        IC2Item.register(new GeothermalGenerator());
        IC2Item.register(new SolarPanel());
        IC2Item.register(new Windmill());
        IC2Item.register(new Watermill());
        IC2Item.register(new Macerator());

    }

    /**
     * Cached key value map of IC2Items.
     *
     * @return Map of IC2Item values
     */
    public HashMap<Pair<Material, Integer>, IC2Item> getItemMap() {
        return ITEM_MAP;
    }

    /**
     * Get the IC2Item instance via a given ItemStack.
     *
     * @param itemStack - Vanilla ItemStack
     * @param <T> - Instance of IC2Item
     * @return Instance of IC2Item
     */
    public static <T extends IC2Item> T getItem(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR)
            return null;

        int identifier = ItemFactory.getIntNBTTag(itemStack, IC2Item.META);
        if (identifier == -1)
            return null;

        Pair<Material, Integer> pair = new Pair<>(itemStack.getType(), identifier);

        IC2Item found = ITEM_MAP.get(pair);
        if (found == null) {
            Log.debug("Pair wasn't matched in ITEM_MAP");
            return null;
        }

        return (T) ITEM_MAP.get(pair);
    }
}
