package com.destinymc.util;

import com.destinymc.ic2.IC2Plugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Luke Bingham
 */
public class ThreadUtil {

    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(IC2Plugin.class), runnable);
    }

    public static void runSyncLater(Runnable runnable, long ticks) {
        Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(IC2Plugin.class), runnable, ticks);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(JavaPlugin.getPlugin(IC2Plugin.class), runnable);
    }

    public static void runAsyncLater(Runnable runnable, long ticks) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(JavaPlugin.getPlugin(IC2Plugin.class), runnable, ticks);
    }
}
