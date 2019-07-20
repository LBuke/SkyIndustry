package com.destinymc.achievement.profile;

import com.destinymc.achievement.Achievement;
import com.destinymc.achievement.AchievementDAO;
import com.destinymc.achievement.AchievementManager;
import com.destinymc.achievement.attribute.RequireAchievement;
import com.destinymc.database.BaseDatabase;
import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.rank.Rank;
import com.destinymc.reward.Reward;
import com.destinymc.misc.RequiredRank;
import com.destinymc.util.StringMap;
import com.destinymc.util.StringUtil;
import com.destinymc.util.ThreadUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Luke Bingham
 */
public final class AchievementData {

    private final Profile profile;
    protected final Achievement achievement;

    private boolean complete;
    private int data;

    public AchievementData(Profile profile, Achievement achievement, int data, boolean complete) {
        this.profile = profile;
        this.achievement = achievement;
        this.data = data;
        this.complete = complete;
    }

    public AchievementData(Profile profile, Achievement achievement) {
        this(profile, achievement, 0, false);
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        if (this.isComplete()) return;
        if (this.isLocked()) return;

        this.data = data;

        ThreadUtil.runAsync(() -> {
            try (Connection connection = BaseDatabase.getInstance().getConnection()) {
                AchievementDAO.setData(connection, this.profile.getUniqueId(), this.achievement.getUniqueId(), this.data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void addData(int amount) {
        if (this.isComplete()) return;
        if (this.isLocked()) return;

        this.setData(this.getData() + amount);
    }

    public void takeData(int amount) {
        if (this.isComplete()) return;
        if (this.isLocked()) return;

        this.setData(this.getData() - amount);
    }

    public boolean isLocked() {
        boolean locked = false;

        if (this.achievement.getClass().isAnnotationPresent(RequireAchievement.class)) {
            int achievementId = this.achievement.getClass().getAnnotation(RequireAchievement.class).value();

            AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
            if (achievementProfile == null) return true;

            AchievementData required = achievementProfile.getAchievementData(achievementId);
            if (!required.isComplete()) {
                locked = true;
            }
        }

        if (this.achievement.getClass().isAnnotationPresent(RequiredRank.class)) {
            Rank rank = this.achievement.getClass().getAnnotation(RequiredRank.class).value();
            Player player = profile.getPlayer();
            if (player == null)
                return true;

            if (!player.hasPermission(rank.getPermission()) && !player.isOp()) {
                locked = true;
            }
        }

        return locked;
    }

    public Achievement getRequiredAchievement() {
        if (this.achievement.getClass().isAnnotationPresent(RequireAchievement.class)) {
            int achievementId = this.achievement.getClass().getAnnotation(RequireAchievement.class).value();

            AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
            if (achievementProfile == null) return null;

            return achievementProfile.getAchievementData(achievementId).getAchievement();
        }

        return null;
    }

    public String getRequiredPermission() {
        return this.getRequiredRank().getPermission();
    }

    public Rank getRequiredRank() {
        if (this.achievement.getClass().isAnnotationPresent(RequiredRank.class)) {
            return this.achievement.getClass().getAnnotation(RequiredRank.class).value();
        }

        return Rank.DEFAULT;
    }

    public boolean isComplete() {
        return complete;
    }

    private void setComplete(boolean complete) {
        this.complete = complete;

        ThreadUtil.runAsync(() -> {
            try (Connection connection = BaseDatabase.getInstance().getConnection()) {
                AchievementDAO.setComplete(connection, this.profile.getUniqueId(), this.achievement.getUniqueId(), this.complete);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void check() {
        if (this.isComplete()) return;
        if (this.isLocked()) return;

        if (this.data >= this.achievement.getTask().getGoal()) {
            this.setComplete(true);

            for (Reward reward : this.achievement.getRewards()) {
                reward.onReward(profile);
            }

            if (!this.achievement.isSilenced())
                this.announce();
        }
    }

    private void announce() {
        Player player = Bukkit.getPlayer(profile.getUniqueId());
        if (player == null)
            return;

        LocaleProfile localeProfile = profile.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null)
            return;

        String[] output = I18n.getAsArray(localeProfile.getLocale(), "achievement.completed.chat",
                new StringMap()
                        .put("header", I18n.get(localeProfile.getLocale(), AchievementManager.HEADER))
                        .put("name", this.achievement.getNameWithDifficultyColor(profile, true))
                        .put("desc", this.achievement.getDescription(profile))
                        .put("footer", I18n.get(localeProfile.getLocale(), AchievementManager.FOOTER))
        );

        for (String str : output) {
            player.sendMessage(StringUtil.getCenteredMessage(str));
        }

//        JavaPlugin javaPlugin = JavaPlugin.getPlugin(BasePlugin.class);
//        AdvancementMessage message = new AdvancementMessage(
//                new NamespacedKey(javaPlugin, "ach-" + this.achievement.getUniqueId()),
//                new FormatedChatComponent(
//                        this.achievement.getNameWithDifficultyColor(profile, true) +
//                                C.RESET + " " +
//                                C.GRAY + this.achievement.getDescription(profile)).getFormatText(),
//                "minecraft:bookshelf",
////                    "task",
//                javaPlugin
//        );
//
//        message.showTo(player);
    }

}
