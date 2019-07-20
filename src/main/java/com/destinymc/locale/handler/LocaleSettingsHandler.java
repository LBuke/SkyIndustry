package com.destinymc.locale.handler;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.destinymc.locale.Locale;
import com.destinymc.packet.in.WrapperPlayClientSettings;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.util.Log;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public final class LocaleSettingsHandler {

    public LocaleSettingsHandler(JavaPlugin plugin) {

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                       new PacketAdapter.AdapterParameteters()
                               .plugin(plugin).optionAsync()
                               .types(WrapperPlayClientSettings.TYPE)) {

                   @Override
                   public void onPacketReceiving(PacketEvent event) {
                       PacketContainer container = event.getPacket();
                       Player player = event.getPlayer();

                       LocaleProfile localeProfile = ProfileManager.getForeignProfile(player.getUniqueId(), LocaleProfile.class);
                       if (localeProfile == null)
                           return;

                       WrapperPlayClientSettings packet = new WrapperPlayClientSettings(container);

                       Log.debug(packet.getLocale());

                       Locale locale = Locale.fromString(packet.getLocale());
                       if (localeProfile.getLocale() != locale) {
                           localeProfile.setLocale(locale);
                           Log.debug("Locale Changed.");
                       }
                   }
               }
        );
    }
}
