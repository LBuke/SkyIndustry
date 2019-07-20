package com.destinymc.changelog;

import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.util.C;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

/**
 * Created at 21/02/2019
 *
 * @author Luke Bingham
 */
public final class ChangelogManager {

    private static String[] LOG_KEYS;
    private final static HashMap<Locale, String> KEY_STR = Maps.newHashMap();

    private int current;

    public ChangelogManager(JavaPlugin plugin) {

        List<String> keys = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            String key = "clog." + i;
            if (I18n.isKeyNull(key))
                break;

            keys.add(key);
        }

        LOG_KEYS = new String[keys.size()];
        LOG_KEYS = keys.toArray(LOG_KEYS);
    }

    public String[] getLogKeyArray() {
        return LOG_KEYS;
    }

    public String getLogKeys(Locale locale) {
        if (!KEY_STR.containsKey(locale)) {
            StringBuilder builder = new StringBuilder();
            for (String str : LOG_KEYS) {
                builder.append(C.GRAY).append(I18n.get(locale, str)).append("\n");
            }
            builder.setLength(builder.length() - 1);
            KEY_STR.put(locale, builder.toString());
        }

        return KEY_STR.get(locale);
    }
}
