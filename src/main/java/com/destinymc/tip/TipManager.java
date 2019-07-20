package com.destinymc.tip;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.destinymc.changelog.ChangelogManager;
import com.destinymc.locale.I18n;
import com.destinymc.packet.out.WrapperPlayServerPlayerListHeaderFooter;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.util.StringMap;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Timestamp;
import java.util.List;

import static com.destinymc.util.ServerUtil.VERSION;

/**
 * Created at 21/02/2019
 *
 * @author Luke Bingham
 */
public final class TipManager {

    private static String[] TIP_KEYS;

    private int current;

    private Timestamp timeChanged = null;

    public TipManager(JavaPlugin plugin, ChangelogManager changelogManager) {
        List<String> keys = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            String key = "tips." + i;
            if (I18n.isKeyNull(key))
                break;

            keys.add(key);
        }

        TIP_KEYS = new String[keys.size()];
        TIP_KEYS = keys.toArray(TIP_KEYS);

        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {

            Bukkit.getOnlinePlayers().forEach(player -> {
                WrapperPlayServerPlayerListHeaderFooter packet = new WrapperPlayServerPlayerListHeaderFooter();
                packet.setHeader(WrappedChatComponent.fromText(player.getName()));
                packet.setFooter(WrappedChatComponent.fromText(player.getName()));
                packet.sendPacket(player);
            });

            if (timeChanged == null)
                timeChanged = new Timestamp(System.currentTimeMillis());

            if (getSecondsRemaining() <= 0) {
                if (current >= (TIP_KEYS.length - 1)) {
                    current = 0;
                    timeChanged = new Timestamp(System.currentTimeMillis());
                    return;
                }

                current += 1;
                timeChanged = new Timestamp(System.currentTimeMillis());
            }
        }, 0L, 10);
    }

    private String arrayToString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String str : array)
            builder.append(str).append("\n");
        return builder.toString();
    }

    public long getSecondsRemaining() {
        if (timeChanged == null)
            return 0L;

        return ((timeChanged.getTime() + (1000 * 20)) - System.currentTimeMillis()) / 1000;
    }

    public String getKey() {
        return TIP_KEYS[current];
    }
}
