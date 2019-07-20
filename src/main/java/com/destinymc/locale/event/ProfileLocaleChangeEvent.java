package com.destinymc.locale.event;

import com.destinymc.locale.Locale;
import com.destinymc.profile.Profile;
import com.destinymc.profile.foreign.LocaleProfile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public final class ProfileLocaleChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Profile profile;
    private final LocaleProfile localeProfile;

    private final Locale from;
    private final Locale to;

    /**
     * @param profile - Profile of the user
     * @param localeProfile - Locale Foreign Profile of the user
     * @param from - Locale which was selected before-hand
     * @param to - Locale to switch to
     */
    public ProfileLocaleChangeEvent(Profile profile, LocaleProfile localeProfile, Locale from, Locale to) {
        this.profile = profile;
        this.localeProfile = localeProfile;
        this.from = from;
        this.to = to;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Profile getProfile() {
        return profile;
    }

    public LocaleProfile getLocaleProfile() {
        return localeProfile;
    }

    public Locale getFrom() {
        return from;
    }

    public Locale getTo() {
        return to;
    }
}
