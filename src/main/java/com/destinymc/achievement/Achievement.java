package com.destinymc.achievement;

import com.destinymc.achievement.task.AchievementTask;
import com.destinymc.profile.Profile;
import com.destinymc.reward.Reward;
import com.destinymc.misc.Difficulty;
import com.destinymc.misc.Unique;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

/**
 * @author Luke Bingham
 */
public interface Achievement extends Unique<Integer>, Listener {

    /**
     * @return Unique Identifier
     */
    Integer getUniqueId();

    String getName(@Nonnull Profile profile);
    String getNameWithDifficultyColor(@Nonnull Profile profile, boolean bold);

    String getDescription(@Nonnull Profile profile);
    String getLongDescription(@Nonnull Profile profile);

    boolean isSilenced();

    AchievementCategory getCategory();

    Difficulty getDifficulty();

    AchievementTask getTask();

    Reward[] getRewards();
}
