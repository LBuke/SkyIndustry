package com.destinymc.achievement.preset;

import com.destinymc.achievement.AchievementCategory;
import com.destinymc.achievement.BaseAchievement;
import com.destinymc.achievement.profile.AchievementData;
import com.destinymc.ic2.machine.event.MachinePlaceEvent;
import com.destinymc.ic2.machine.types.MachineSolarGenerator;
import com.destinymc.misc.Difficulty;
import com.destinymc.reward.Reward;
import org.bukkit.event.EventHandler;

public final class EnergyGenerationAchievement extends BaseAchievement {

    public EnergyGenerationAchievement() {
        super(
                2,
                "energy-generation",
                false,
                AchievementCategory.ADVANCED,
                Difficulty.REGULAR,
                1,
                new Reward[] {}
        );
    }

    @EventHandler
    protected final void onMachinePlace(MachinePlaceEvent event) {
        if (event.getMachine() == null)
            return;

        if (!(event.getMachine() instanceof MachineSolarGenerator))
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
