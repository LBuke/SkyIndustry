package com.destinymc.util;

import org.bukkit.Bukkit;

/**
 * Created at 19/01/2019
  * <p>
 * @author Luke Bingham
 */
public final class Log {

    private static boolean DEBUG_ENABLED = true;

    public static void log(String prefix, String message) {
        String pref = C.WHITE + "[" + C.AQUA + "IC2" + C.WHITE + "] [" + prefix + C.WHITE + "] ";
        Bukkit.getConsoleSender().sendMessage(pref + message);
    }

    public static void register(String message) {
        log(C.BLUE + "REGISTER", message);
    }

    public static void debug(String message) {
        if (!DEBUG_ENABLED) return;

        log(C.YELLOW + "DEBUG", message);
    }
}
