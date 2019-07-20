package com.destinymc.achievement;

import com.destinymc.achievement.exception.AchievementRegisterException;
import com.destinymc.ic2.IC2Plugin;
import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

/**
 * @author Luke Bingham
 */
public final class AchievementManager {
    public static final String HEADER = "achievement.completed.header";
    public static final String FOOTER = "achievement.completed.footer";

    private static final Set<Achievement> achievements;

    static {
        achievements = Sets.newHashSet();
    }

    public static void registerAchievement(Achievement achievement) throws AchievementRegisterException {
        if (getAchievementById(achievement.getUniqueId()) != null) {
            throw new AchievementRegisterException(achievement);
        }

        achievements.add(achievement);
        Bukkit.getPluginManager().registerEvents(achievement, JavaPlugin.getPlugin(IC2Plugin.class));
    }

    public static Set<Achievement> getAchievements() {
        return achievements;
    }

    public static Achievement getAchievementById(int id) {
        return achievements.stream().filter(achievement -> achievement.getUniqueId() == id).findFirst().orElse(null);
    }
}
