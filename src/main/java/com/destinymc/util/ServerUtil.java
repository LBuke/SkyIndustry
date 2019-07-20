package com.destinymc.util;

import com.destinymc.ic2.IC2Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class ServerUtil {

    public static final String VERSION = "0.1";

    private static JavaPlugin PLUGIN = null;

    public static JavaPlugin getPlugin() {
        return PLUGIN == null ? PLUGIN = JavaPlugin.getPlugin(IC2Plugin.class) : PLUGIN;
    }
}
