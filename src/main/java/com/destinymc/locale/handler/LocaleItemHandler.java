package com.destinymc.locale.handler;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.destinymc.factory.ItemFactory;
import com.destinymc.ic2.item.misc.PoweredItem;
import com.destinymc.ic2.util.MachineUtil;
import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.locale.event.ProfileLocaleChangeEvent;
import com.destinymc.packet.in.WrapperPlayClientSetCreativeSlot;
import com.destinymc.packet.out.WrapperPlayServerSetSlot;
import com.destinymc.packet.out.WrapperPlayServerWindowData;
import com.destinymc.packet.out.WrapperPlayServerWindowItems;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.util.Log;
import com.destinymc.util.StringMap;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * Created at 16/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class LocaleItemHandler extends LocaleHandler<ItemStack, Boolean> implements Listener {

    public LocaleItemHandler(JavaPlugin plugin) {
        super(plugin);

        Bukkit.getPluginManager().registerEvents(this, plugin);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                        new PacketAdapter.AdapterParameteters()
                                .plugin(plugin).optionAsync()
                                .types(WrapperPlayServerSetSlot.TYPE, WrapperPlayServerWindowItems.TYPE)) {

                    @Override
                    public void onPacketSending(PacketEvent event) {
                        PacketContainer container = event.getPacket();

                        Player player = event.getPlayer();

                        // We cannot translate items for players in creative-mode
                        // As this changes the item and doesn't allow further translations..
                        if (player.getGameMode() == GameMode.CREATIVE)
                            return;

                        LocaleProfile localeProfile = ProfileManager.getForeignProfile(player.getUniqueId(), LocaleProfile.class);
                        if (localeProfile == null)
                            return;

                        if (event.getPacketType() == WrapperPlayServerSetSlot.TYPE) {
                            WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot(container);
                            ItemStack item = packet.getSlotData();
                            if (item == null)
                                return;

                            boolean usesData = false;

                            // Powered Item which uses 'power' within display name.
                            if (ItemFactory.hasNBTTag(item, PoweredItem.META)) {
                                long eu = ItemFactory.getLongNBTTag(item, PoweredItem.META);
                                translate(localeProfile.getLocale(), item, new StringMap().put(PoweredItem.META, MachineUtil.getFormattedPower(eu)));
                                usesData = true;
                            }

                            if (!usesData) {
                                translate(localeProfile.getLocale(), item);
                            }

                            packet.setSlotData(item);

                            event.setPacket(packet.getHandle());
                        }

                        if (event.getPacketType() == WrapperPlayServerWindowItems.TYPE) {
                            WrapperPlayServerWindowItems packet = new WrapperPlayServerWindowItems(container);
                            List<ItemStack> items = packet.getSlotData();
                            if (items == null || items.isEmpty())
                                return;

                            for (ItemStack item : items) {
                                boolean usesData = false;

                                // Powered Item which uses 'power' within display name.
                                if (ItemFactory.hasNBTTag(item, PoweredItem.META)) {
                                    long eu = ItemFactory.getLongNBTTag(item, PoweredItem.META);
                                    translate(localeProfile.getLocale(), item, new StringMap().put(PoweredItem.META, MachineUtil.getFormattedPower(eu)));
                                    usesData = true;
                                }

                                if (!usesData) {
                                    translate(localeProfile.getLocale(), item);
                                }
                            }

                            packet.setSlotData(items);

                            event.setPacket(packet.getHandle());
                        }
                    }
                }
        );
    }

    @EventHandler
    protected final void onLocaleChange(ProfileLocaleChangeEvent event) {
        event.getProfile().getPlayer().updateInventory();
        event.getProfile().getPlayer().sendMessage("DEBUG, Your locale has changed to - " + event.getTo().name());
    }

    @EventHandler
    protected final void onItemDrop(PlayerDropItemEvent event) {
        event.getItemDrop().setCustomName(event.getItemDrop().getItemStack().getItemMeta().getDisplayName());
        event.getItemDrop().setCustomNameVisible(true);
    }

    @Override
    public Boolean translate(Locale locale, ItemStack input) {
        if (input == null)
            return false;

        ItemMeta meta = input.getItemMeta();
        if (meta == null)
            return false;

        if (!meta.hasDisplayName())
            return false;

        String oldName = meta.getDisplayName();

        MatchResult result = super.attemptMatch(meta.getDisplayName());
        if (result == null)
            return false;

        meta.setDisplayName(I18n.get(locale, result.getText()));
        this.translateLore(locale, meta);

        input.setItemMeta(meta);

//        Log.debug("Translated " + oldName);

        return true;
    }

    public boolean translate(Locale locale, ItemStack input, StringMap data) {
        if (input == null)
            return false;

        if (data == null)
            return false;

        ItemMeta meta = input.getItemMeta();
        if (meta == null)
            return false;

        if (!meta.hasDisplayName())
            return false;

        String oldName = meta.getDisplayName();

        MatchResult result = super.attemptMatch(meta.getDisplayName());
        if (result == null)
            return false;

        meta.setDisplayName(I18n.get(locale, result.getText(), data));
        this.translateLore(locale, meta);

        input.setItemMeta(meta);

//        Log.debug("Translated " + oldName);

        return true;
    }

    private void translateLore(Locale locale, ItemMeta meta) {
        if (meta == null)
            return;

        if (!meta.hasLore())
            return;

        StringBuilder str = new StringBuilder();
        for (String line : meta.getLore()) {
            MatchResult result = super.attemptMatch(line);
            if (result == null) {
                str.append(line).append("{n}");
                continue;
            }

            StringMap data = null;
            String key = "";

            if (result.getText().contains(";")) {
                String[] values = result.getText().split(";");

                key = values[0];
                data = new StringMap();

                for (int i = 1; i < values.length; i++) {
                    data.put("" + (i - 1), values[i]);
                }
            }

            for (String localeStr : data == null ? I18n.getAsArray(locale, key) : I18n.getAsArray(locale, key, data)) {
                str.append(localeStr).append("{n}");
            }
        }
        str.setLength(str.length() - 3);

        List<String> lore = Lists.newArrayList();
        String newLore = str.toString();

        if (newLore.contains("{n}")) {
            lore.addAll(Arrays.asList(newLore.split("\\{n}")));
        } else {
            lore.add(newLore);
        }

        meta.setLore(lore);
    }
}
