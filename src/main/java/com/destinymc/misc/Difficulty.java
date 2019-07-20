package com.destinymc.misc;

import com.destinymc.locale.I18n;
import com.destinymc.profile.Profile;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.util.C;

import javax.annotation.Nonnull;

/**
 * @author Luke Bingham
 */
public enum Difficulty {

    CASUAL(C.GREEN, "difficulty.casual"),

    REGULAR(C.BLUE, "difficulty.regular"),

    HARDENED(C.RED, "difficulty.hardened"),

    VETERAN(C.DARK_RED, "difficulty.veteran"),

    ;

    private final String color;
    private final String name;

    Difficulty(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName(@Nonnull Profile profile) {
        LocaleProfile localeProfile = profile.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null) {
            return I18n.get(I18n.DEFAULT, name);
        }

        return I18n.get(localeProfile.getLocale(), name);
    }
}
