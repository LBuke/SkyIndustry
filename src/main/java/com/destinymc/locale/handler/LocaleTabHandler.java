package com.destinymc.locale.handler;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.destinymc.changelog.ChangelogManager;
import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.packet.out.WrapperPlayServerChat;
import com.destinymc.packet.out.WrapperPlayServerPlayerListHeaderFooter;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.tip.TipManager;
import com.destinymc.util.Log;
import com.destinymc.util.StringMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.destinymc.util.ServerUtil.VERSION;

/**
 * Created at 21/02/2019
 *
 * @author Luke Bingham
 */
public final class LocaleTabHandler extends LocaleHandler<String, String> {

    private static final Pattern JSON_PATTERN = Pattern.compile("\\{t(?:\\=|\\\\u003d)[^\"\\}]+\\}");

    public LocaleTabHandler(JavaPlugin plugin, TipManager tipManager, ChangelogManager changelogManager) {
        super(plugin);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                       new PacketAdapter.AdapterParameteters()
                               .plugin(plugin).optionAsync()
                               .types(WrapperPlayServerPlayerListHeaderFooter.TYPE)) {

                   @Override
                   public void onPacketSending(PacketEvent event) {
                       PacketContainer container = event.getPacket();

                       Player player = event.getPlayer();
                       LocaleProfile localeProfile = ProfileManager.getForeignProfile(player.getUniqueId(), LocaleProfile.class);
                       if (localeProfile == null)
                           return;

                       WrapperPlayServerPlayerListHeaderFooter packet = new WrapperPlayServerPlayerListHeaderFooter(container);

                       String[] newHeader = I18n.getAsArray(localeProfile.getLocale(), "tab.header",
                               new StringMap()
                                       .put("version", VERSION)
                                       .put("sec", tipManager.getSecondsRemaining())
                                       .put("tip", I18n.get(localeProfile.getLocale(), tipManager.getKey()))
                       );

                       String[] newFooter = I18n.getAsArray(localeProfile.getLocale(), "tab.footer",
                               new StringMap().put("version", VERSION).put("changelog", changelogManager.getLogKeys(localeProfile.getLocale()))
                       );

                       packet.setHeader(WrappedChatComponent.fromText(arrayToString(newHeader)));
                       packet.setFooter(WrappedChatComponent.fromText(arrayToString(newFooter)));

                       event.setPacket(packet.getHandle());
                   }
               }
        );
    }

    private String arrayToString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String str : array)
            builder.append(str).append("\n");
        return builder.toString();
    }

    @Override
    public String translate(Locale locale, String input) {
        Log.debug(JSON_PATTERN.toString());
        Matcher matcher = JSON_PATTERN.matcher(input);

        while (matcher.find()) {
            String value = matcher.group();

            Log.debug(value);

            MatchResult result = super.attemptMatch(value);
            if (result == null)
                continue;

            input = matcher.replaceAll(JSONObject.escape(I18n.get(locale, result.getText())));
        }

        return input;
    }
}
