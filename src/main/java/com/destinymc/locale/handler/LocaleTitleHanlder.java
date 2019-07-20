package com.destinymc.locale.handler;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.packet.out.WrapperPlayServerChat;
import com.destinymc.packet.out.WrapperPlayServerTitle;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.util.Log;
import com.destinymc.util.StringMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created at 21/02/2019
 *
 * @author Luke Bingham
 */
public final class LocaleTitleHanlder extends LocaleHandler<String, String> {

    private static final Pattern JSON_PATTERN = Pattern.compile("\\{t(?:\\=|\\\\u003d)[^\"\\}]+\\}");

    public LocaleTitleHanlder(JavaPlugin plugin) {
        super(plugin);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                       new PacketAdapter.AdapterParameteters()
                               .plugin(plugin).optionAsync()
                               .types(WrapperPlayServerTitle.TYPE)) {

                   @Override
                   public void onPacketSending(PacketEvent event) {
                       PacketContainer container = event.getPacket();

                       Player player = event.getPlayer();
                       LocaleProfile localeProfile = ProfileManager.getForeignProfile(player.getUniqueId(), LocaleProfile.class);
                       if (localeProfile == null)
                           return;

                       WrapperPlayServerTitle packet = new WrapperPlayServerTitle(container);

                       String json = packet.getTitle().getJson();
                       json = json.replaceAll("\\\\u003d", "=");

                       if (!JSON_PATTERN.matcher(json).find()) {
                           return;
                       }

                       json = translate(localeProfile.getLocale(), json);

                       packet.setTitle(WrappedChatComponent.fromJson(json));

                       event.setPacket(packet.getHandle());
                   }
               }
        );
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

            input = matcher.replaceAll(JSONObject.escape(I18n.get(locale, result.getText(), new StringMap().put("IC2POWER", "?"))));
        }

        return input;
    }
}
