package com.destinymc.profile.foreign;

import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.locale.event.ProfileLocaleChangeEvent;
import com.destinymc.profile.ForeignProfile;
import com.destinymc.profile.Profile;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public final class LocaleProfile extends ForeignProfile {

    private Locale locale = I18n.DEFAULT;

    public LocaleProfile(@Nonnull Profile profile) {
        super(profile);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        Locale old = this.locale;
        this.locale = locale;

        ProfileLocaleChangeEvent localeChangeEvent = new ProfileLocaleChangeEvent(profile, this, old, locale);
        Bukkit.getPluginManager().callEvent(localeChangeEvent);
    }
}
