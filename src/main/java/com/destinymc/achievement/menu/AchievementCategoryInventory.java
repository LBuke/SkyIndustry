package com.destinymc.achievement.menu;

import com.destinymc.achievement.AchievementCategory;
import com.destinymc.achievement.profile.AchievementProfile;
import com.destinymc.factory.ItemFactory;
import com.destinymc.inventory.BaseMenu;
import com.destinymc.inventory.button.ClickableItem;
import com.destinymc.inventory.button.preset.PreviousPageCommon;
import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import com.destinymc.util.StringMap;
import org.bukkit.Material;

/**
 * Created at 01/03/2019
 *
 * @author Luke Bingham
 */
public final class AchievementCategoryInventory extends BaseMenu {

    private final Profile profile;

    public AchievementCategoryInventory(Profile profile) {
        super(5, I18n.get(profile.getLocale(), "achievement.category.inventory"));
        this.profile = profile;

        this.update();
    }

    public void update() {
        super.addItem(new PreviousPageCommon(36, this.profile));

        super.addItem(new ClickableItem(40, new ItemFactory(Material.BOOK).build(), (player, clickType) -> {
            player.sendMessage("Clicked 'Stats'");
        }));

        for (AchievementCategory category : AchievementCategory.values()) {

            ItemFactory item = new ItemFactory(category.getMaterial());
            item.setName(category.getName(this.profile));

            AchievementProfile achievementProfile = profile.getForeignProfile(AchievementProfile.class);
            if (achievementProfile == null) return;

            item.setLore(I18n.getAsArray(this.profile.getLocale(), "achievement.category.lore",
                    new StringMap().put("complete", achievementProfile.getCompletedAchievementCountByCategory(category))
                            .put("uncomplete", achievementProfile.getAchievementCountByCategory(category))
            ));

            super.addItem(new ClickableItem(category.getIndex(), item.build(), (player, clickType) -> {
                new AchievementInventory(category, this.profile, this).openInventory(player);
            }));
        }
    }
}
