package com.destinymc.achievement.profile;

import com.destinymc.achievement.Achievement;
import com.destinymc.achievement.AchievementCategory;
import com.destinymc.achievement.AchievementDAO;
import com.destinymc.achievement.AchievementManager;
import com.destinymc.achievement.event.AchievementDataLoadEvent;
import com.destinymc.profile.ForeignProfile;
import com.destinymc.profile.Profile;
import com.destinymc.util.ThreadUtil;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.util.HashMap;

/**
 * @author Luke Bingham
 */
public final class AchievementProfile extends ForeignProfile {

    private HashMap<Integer, AchievementData> achievementDataMap;

    public AchievementProfile(Profile profile) {
        super(profile);

        this.achievementDataMap = Maps.newHashMap();
    }

    @Override
    public void init(Connection connection) {
        for (AchievementData data : AchievementDAO.getAchievementData(connection, this.profile)) {
            this.achievementDataMap.put(data.achievement.getUniqueId(), data);
        }

        for (Achievement achievement : AchievementManager.getAchievements()) {
            if (this.achievementDataMap.containsKey(achievement.getUniqueId()))
                continue;

            this.achievementDataMap.put(achievement.getUniqueId(), new AchievementData(this.profile, achievement, 0, false));
        }

        ThreadUtil.runSyncLater(() -> {
            AchievementDataLoadEvent dataLoadEvent = new AchievementDataLoadEvent(AchievementProfile.this);
            Bukkit.getPluginManager().callEvent(dataLoadEvent);
        }, 20L);
    }

    public AchievementData getAchievementData(int id) {
        return this.achievementDataMap.getOrDefault(id, null);
    }

    public HashMap<Integer, AchievementData> getAchievementDataMap() {
        return this.achievementDataMap;
    }

    public int getAchievementCountByCategory(AchievementCategory category) {
        return (int) this.achievementDataMap.values().stream().filter(data -> data.getAchievement().getCategory() == category).count();
    }

    public int getCompletedAchievementCountByCategory(AchievementCategory category) {
        return (int) this.achievementDataMap.values().stream().filter(data -> data.isComplete() && data.getAchievement().getCategory() == category).count();
    }
}
