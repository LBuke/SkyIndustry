package com.destinymc.achievement.menu;

import com.destinymc.achievement.Achievement;
import com.destinymc.achievement.AchievementCategory;
import com.destinymc.achievement.profile.AchievementData;
import com.destinymc.achievement.profile.AchievementProfile;
import com.destinymc.factory.ItemFactory;
import com.destinymc.inventory.BaseMenu;
import com.destinymc.inventory.button.MenuItem;
import com.destinymc.inventory.button.preset.PreviousPageCommon;
import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import com.destinymc.rank.Rank;
import com.destinymc.reward.Reward;
import com.destinymc.util.C;
import com.destinymc.misc.Difficulty;
import com.destinymc.util.MathUtil;
import com.destinymc.util.StringUtil;
import com.google.common.collect.Lists;
import org.bukkit.Material;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created at 01/03/2019
 *
 * @author Luke Bingham
 */
final class AchievementInventory extends BaseMenu {

    private static final int[] SLOTS = {10,11,12,13,14,15,16, 19,20,21,22,23,24,25, 28,29,30,31,32,33,34};

    private final AchievementCategory category;
    private final Profile profile;
    private final BaseMenu parent;

    public AchievementInventory(AchievementCategory category, Profile profile, BaseMenu parent) {
        super(6, category.getName(profile));
        this.category = category;
        this.profile = profile;
        this.parent = parent;

        this.update();
    }

    public void update() {
        super.addItem(new PreviousPageCommon(47, profile, parent));

        List<AchievementData> achievements = this.getByCategory(category);
        if (achievements.isEmpty()) return;

        int x = 0;
        for (AchievementData achievementData : this.getByCategory(this.category)) {

            boolean c = achievementData.isComplete();

            ItemFactory item = new ItemFactory(Material.INK_SACK, c ? (byte)10 : (byte)8);
            item.setName((c ? C.GREEN : C.RED) + achievementData.getAchievement().getName(profile));

            List<String> lore = Lists.newArrayList();
            lore.add(C.DARK_GRAY + C.ITALIC + category.getName(profile));
            lore.add("");
            lore.addAll(StringUtil.getShortenedLore(C.GRAY + achievementData.getAchievement().getLongDescription(profile)));
            lore.add("");

            lore.add(C.YELLOW + I18n.get(profile.getLocale(), "difficulty") + C.WHITE + ":");
            Difficulty d = achievementData.getAchievement().getDifficulty();
            lore.add(C.WHITE + " \u25b6 " + d.getColor() + C.BOLD + d.getName(profile).toUpperCase());
            lore.add("");

            lore.add(C.YELLOW + I18n.get(profile.getLocale(), "rewards") + C.WHITE + ":");
            for (Reward r : achievementData.getAchievement().getRewards()) {
                lore.add(C.WHITE + " \u25b6 " + r.getNameWithColor(profile));
            }
            lore.add("");

            if (achievementData.isComplete()) {
                lore.add(C.GREEN + I18n.get(profile.getLocale(), "achievement.unlocked"));
            }
            else if (achievementData.isLocked()) {
                lore.add(C.DARK_RED + I18n.get(profile.getLocale(), "achievement.locked"));

                Achievement achievement = achievementData.getRequiredAchievement();
                if (achievement != null) {
                    AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
                    if (achievementProfile != null) {
                        AchievementData requiredData = achievementProfile.getAchievementData(achievement.getUniqueId());
                        if (!requiredData.isComplete()) {
                            lore.add("");
                            lore.add(C.RED + I18n.get(profile.getLocale(), "achievement.required") + C.WHITE + ":");
                            int current = requiredData.getData();
                            int goal = achievement.getTask().getGoal();
                            lore.add(C.WHITE + " \u25b6 " + achievement.getNameWithDifficultyColor(profile, false) + C.RESET + " (" + C.GRAY + MathUtil.getPercent(current, goal) + "%" + C.RESET + ")");
                        }
                    }
                }

                Rank rank = achievementData.getRequiredRank();
                if (rank != null && !profile.hasRank(rank)) {
                    lore.add("");
                    lore.add(C.RED + I18n.get(profile.getLocale(), "achievement.rank.required") + C.WHITE + ":");
                    lore.add(C.WHITE + " \u25b6 " + rank.getName());
                }
            }
            else {
                lore.add(C.AQUA + I18n.get(profile.getLocale(), "achievement.progress"));
                int current = achievementData.getData();
                int goal = achievementData.getAchievement().getTask().getGoal();
                lore.add(" " + StringUtil.getProgressBar(current, goal, 30, '\u2759', C.GREEN, C.GRAY, false) + C.GRAY + " " + MathUtil.getPercent(current, goal) + "%");
            }
            lore.add("");

            item.setLore(lore);
            super.addItem(new MenuItem(SLOTS[x++], item.build()));
        }
    }

    private List<AchievementData> getByCategory(AchievementCategory category) {
        AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
        if (achievementProfile == null) return Lists.newArrayList();

        return achievementProfile.getAchievementDataMap().values().stream().filter(data -> data.getAchievement().getCategory() == category).collect(Collectors.toList());
    }
}
