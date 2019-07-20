package com.destinymc.locale.handler;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.packet.out.WrapperPlayServerEntityMetadata;
import com.destinymc.packet.out.WrapperPlayServerSpawnEntity;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 16/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class LocaleEntityHandler extends LocaleHandler<Entity, Boolean> {

    public LocaleEntityHandler(JavaPlugin plugin) {
        super(plugin);

//        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, WrapperPlayServerEntityMetadata.TYPE) {
//               @Override
//               public void onPacketSending(PacketEvent event) {
//                   PacketContainer container = event.getPacket();
//
//                   // WrapperPlayServerEntityMetadata
//                   if (event.getPacketType() == WrapperPlayServerEntityMetadata.TYPE) {
//                       WrapperPlayServerEntityMetadata packet = new WrapperPlayServerEntityMetadata(container);
//                       Entity entity = packet.getEntity(event.getPlayer().getWorld());
//                       translate(entity);
//
//                       event.setPacket(packet.getHandle());
//                   }
//               }
//           }
//        );

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                new PacketAdapter.AdapterParameteters()
                        .plugin(plugin).optionAsync()
                        .types(WrapperPlayServerEntityMetadata.TYPE, WrapperPlayServerSpawnEntity.TYPE)) {

                @Override
                public void onPacketSending(PacketEvent event) {
                    PacketContainer container = event.getPacket();

                    // This will be used to get Locale.
                    Player player = event.getPlayer();
                    LocaleProfile localeProfile = ProfileManager.getForeignProfile(player.getUniqueId(), LocaleProfile.class);
                    if (localeProfile == null)
                        return;

                    // WrapperPlayServerEntityMetadata
//                    PacketPlayOutEntityMetadata
                    if (event.getPacketType() == WrapperPlayServerEntityMetadata.TYPE) {
                        WrapperPlayServerEntityMetadata packet = new WrapperPlayServerEntityMetadata(container);
                        Entity entity = packet.getEntity(event.getPlayer().getWorld());
                        translate(localeProfile.getLocale(), entity);

//                        WrappedDataWatcher watcher = WrappedDataWatcher.getEntityWatcher(entity);
//
//                        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(String.class);
//                        WrappedDataWatcher.WrappedDataWatcherObject object = new WrappedDataWatcher.WrappedDataWatcherObject(0, serializer);
//                        watcher.setObject(object, "Hello, World!");
//
//                        packet.setMetadata(watcher.getWatchableObjects());

//                        for (WrappedWatchableObject watchableObject : packet.getMetadata()) {
//                            if (watchableObject.getValue() instanceof String) {
//                                watchableObject.setValue(entity.getCustomName());
//                            }
//                        }

                        event.setPacket(packet.getHandle());
                    }

                    if (event.getPacketType() == WrapperPlayServerSpawnEntity.TYPE) {
                        WrapperPlayServerSpawnEntity packet = new WrapperPlayServerSpawnEntity(container);
                        Entity entity = packet.getEntity(event.getPlayer().getWorld());
                        translate(localeProfile.getLocale(), entity);

                        event.setPacket(packet.getHandle());
                    }
                }
            }
        );
    }

    @Override
    public Boolean translate(Locale locale, Entity input) {
        if (input == null || !input.isValid())
            return false;

        if (input.getCustomName() == null || input.getCustomName().equals("null"))
            return false;

        MatchResult result = super.attemptMatch(input.getCustomName());
        if (result == null)
            return false;

        input.setCustomName(I18n.get(result.getText()));
        return true;
    }
}
