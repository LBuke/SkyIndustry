package com.destinymc.achievement;

import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import com.destinymc.profile.foreign.LocaleProfile;
import org.bukkit.Material;

/**
 * @author Luke Bingham
 */
public enum AchievementCategory {

    GENERAL(0, Material.CHEST, "achievement.category.name.general"),

    ADVANCED(1, Material.ENDER_CHEST, "achievement.category.name.advanced"),

    ;

    private final int index;
    private final Material material;
    private final String name;

    AchievementCategory(int index, Material material, String name) {
        this.index = index;
        this.material = material;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName(Profile profile) {
        LocaleProfile localeProfile = profile.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null)
            return I18n.get(I18n.DEFAULT, this.name);

        return I18n.get(localeProfile.getLocale(), this.name);
    }
}
