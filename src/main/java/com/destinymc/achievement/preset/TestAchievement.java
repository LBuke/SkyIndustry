package com.destinymc.achievement.preset;

import com.destinymc.achievement.AchievementCategory;
import com.destinymc.achievement.BaseAchievement;
import com.destinymc.achievement.profile.AchievementData;
import com.destinymc.reward.Reward;
import com.destinymc.misc.Difficulty;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Created at 28/02/2019
 *
 * @author Luke Bingham
 */
public final class TestAchievement extends BaseAchievement {

    public TestAchievement() {
        super(
                1,
                "test",
                false,
                AchievementCategory.ADVANCED,
                Difficulty.REGULAR,
                2,
                new Reward[] {}
        );
    }

    @EventHandler
    protected final void onPlayerSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking())
            return;

        AchievementData achievementData = super.getAchievementData(event.getPlayer());
        if (achievementData == null) return;

        if (achievementData.isComplete())
            return;

        if (achievementData.isLocked())
            return;

        achievementData.addData(1);
        achievementData.check();
    }
}
