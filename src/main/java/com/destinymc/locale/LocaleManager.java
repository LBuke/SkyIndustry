package com.destinymc.locale;

import com.destinymc.changelog.ChangelogManager;
import com.destinymc.locale.handler.*;
import com.destinymc.tip.TipManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 18/02/2019
 *
 * @author Luke Bingham
 */
public final class LocaleManager {

    public LocaleManager(JavaPlugin plugin, TipManager tipManager, ChangelogManager changelogManager) {

        new LocaleEntityHandler(plugin);
        new LocaleItemHandler(plugin);

        new LocaleChatHandler(plugin);
//        new LocaleTabHandler(plugin, tipManager, changelogManager);
        new LocaleTitleHanlder(plugin);

        new LocaleSettingsHandler(plugin);
    }
}
