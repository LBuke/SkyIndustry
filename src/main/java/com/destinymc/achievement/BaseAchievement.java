package com.destinymc.achievement;

import com.destinymc.achievement.profile.AchievementData;
import com.destinymc.achievement.profile.AchievementProfile;
import com.destinymc.achievement.task.AchievementTask;
import com.destinymc.achievement.task.BaseAchievementTask;
import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import com.destinymc.profile.ProfileManager;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.reward.Reward;
import com.destinymc.util.C;
import com.destinymc.misc.Difficulty;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

/**
 * @author Luke Bingham
 */
public abstract class BaseAchievement implements Achievement {

    private final int uniqueId;
    private final String name;

    private final boolean silenced;

    private final AchievementCategory category;
    private final Difficulty difficulty;
    private final AchievementTask task;
    private final Reward[] rewards;

    /**
     * Construct a new Achievement.
     *
     * @param uniqueId - Unique Identifier
     * @param name - Name to reference in i18n properties
     * @param silenced - Should be announced to the player
     * @param category - Category of the achievement
     * @param difficulty - Difficulty of the achievement
     * @param task - Integer task/goal of the achievement
     * @param rewards - Rewards to be given once complete
     */
    public BaseAchievement(int uniqueId, String name, boolean silenced, AchievementCategory category, Difficulty difficulty, AchievementTask task, Reward[] rewards) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.silenced = silenced;
        this.category = category;
        this.difficulty = difficulty;
        this.task = task;
        this.rewards = rewards;
    }

    /**
     * Construct a new Achievement.
     *
     * @param uniqueId - Unique Identifier
     * @param name - Name to reference in i18n properties
     * @param silenced - Should be announced to the player
     * @param category - Category of the achievement
     * @param difficulty - Difficulty of the achievement
     * @param task - Integer task/goal of the achievement
     * @param rewards - Rewards to be given once complete
     */
    public BaseAchievement(int uniqueId, String name, boolean silenced, AchievementCategory category, Difficulty difficulty, int task, Reward[] rewards) {
        this(uniqueId, name, silenced, category, difficulty, new BaseAchievementTask(task), rewards);
    }

    /**
     * Construct a new silenced Achievement.
     *
     * @param uniqueId - Unique Identifier
     * @param name - Name to reference in i18n properties
     * @param category - Category of the achievement
     * @param difficulty - Difficulty of the achievement
     * @param task - Integer task/goal of the achievement
     * @param rewards - Rewards to be given once complete
     */
    public BaseAchievement(int uniqueId, String name, AchievementCategory category, Difficulty difficulty, AchievementTask task, Reward[] rewards) {
        this(uniqueId, name, false, category, difficulty, task, rewards);
    }

    @Override
    public Integer getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getName(@Nonnull Profile profile) {
        LocaleProfile localeProfile = profile.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null)
            return I18n.get(I18n.DEFAULT, "achievement." + name);

        return I18n.get(localeProfile.getLocale(), "achievement." + name);
    }

    @Override
    public String getNameWithDifficultyColor(@Nonnull Profile profile, boolean bold) {
        return difficulty.getColor() + (bold ? C.BOLD : "") + this.getName(profile);
    }

    @Override
    public String getDescription(@Nonnull Profile profile) {
        LocaleProfile localeProfile = profile.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null)
            return I18n.get(I18n.DEFAULT, "achievement." + name + ".desc.short");

        return I18n.get(localeProfile.getLocale(), "achievement." + name + ".desc.short");
    }

    @Override
    public String getLongDescription(@Nonnull Profile profile) {
        LocaleProfile localeProfile = profile.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null)
            return I18n.get(I18n.DEFAULT, "achievement." + name + ".desc");

        return I18n.get(localeProfile.getLocale(), "achievement." + name + ".desc");
    }

    @Override
    public boolean isSilenced() {
        return silenced;
    }

    @Override
    public AchievementCategory getCategory() {
        return category;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public AchievementTask getTask() {
        return task;
    }

    @Override
    public Reward[] getRewards() {
        return rewards;
    }

    protected AchievementData getAchievementData(Player player) {
        Profile profile = ProfileManager.getProfile(player.getUniqueId());
        if (profile == null) return null;

        AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
        if (achievementProfile == null) return null;

        return achievementProfile.getAchievementData(this.getUniqueId());
    }

    protected AchievementData getAchievementData(Profile profile) {
        AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
        if (achievementProfile == null) return null;

        return achievementProfile.getAchievementData(this.getUniqueId());
    }
}
