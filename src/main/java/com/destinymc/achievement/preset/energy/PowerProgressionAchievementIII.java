package com.destinymc.achievement.preset.energy;

import com.destinymc.achievement.AchievementCategory;
import com.destinymc.achievement.BaseAchievement;
import com.destinymc.achievement.profile.AchievementData;
import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.IC2ItemManager;
import com.destinymc.ic2.machine.item.MFSUnit;
import com.destinymc.ic2.machine.event.MachinePlaceEvent;
import com.destinymc.ic2.machine.types.MachineMFS;
import com.destinymc.ic2.recipe.event.PrepareCustomItemCraftEvent;
import com.destinymc.misc.Difficulty;
import com.destinymc.reward.Reward;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public final class PowerProgressionAchievementIII extends BaseAchievement {

    /**
     * Power blocks (I, II, III)
     * - machine place
     * - machine item craft
     *
     * Portable power items (I, II, III)
     * - machine item craft
     * - item pickup
     */

    /**
     * Construct a new Achievement.
     */
    public PowerProgressionAchievementIII() {
        super(
                5, //Unique Identifier
                "power-progression-iii", //Name to reference in i18n properties
                false, //Should be announced to the player
                AchievementCategory.ADVANCED, //Category of the achievement
                Difficulty.REGULAR, //Difficulty of the achievement
                1, //Integer task/goal of the achievement
                new Reward[] { //Rewards to be given once complete
                        //TODO Rewards
                }
        );
    }

    @EventHandler(ignoreCancelled = true)
    protected final void onMachinePlace(MachinePlaceEvent event) {
        if (!(event.getMachine() instanceof MachineMFS))
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

    @EventHandler(ignoreCancelled = true)
    protected final void onPrepareCustomRecipe(PrepareCustomItemCraftEvent event) {
        IC2Item item = IC2ItemManager.getItem(event.getItem());
        if (item == null || item.getType() == Material.AIR)
            return;

        if (!(item instanceof MFSUnit))
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
