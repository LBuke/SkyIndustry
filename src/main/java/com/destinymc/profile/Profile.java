package com.destinymc.profile;

import com.destinymc.locale.I18n;
import com.destinymc.locale.Locale;
import com.destinymc.profile.foreign.LocaleProfile;
import com.destinymc.rank.Rank;
import com.destinymc.misc.Unique;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.util.UUID;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public interface Profile extends Unique<UUID> {

    /**
     * @return Unique Identifier
     */
    UUID getUniqueId();

    /**
     * @return Player object from the Unique Id
     */
    Player getPlayer();

    default boolean hasRank(Rank rank) {
        Player player = getPlayer();
        if (player == null)
            return false;

        Rank r = Rank.getUserRank(player);
        return r.ordinal() >= rank.ordinal();
    }

    default Locale getLocale() {
        LocaleProfile localeProfile = this.getForeignProfile(LocaleProfile.class);
        if (localeProfile == null)
            return I18n.DEFAULT;

        return localeProfile.getLocale();
    }

    /**
     * Get a Foreign Profile assigned to this Profile.
     *
     * @param clazz - Foreign Profile class
     * @param <T> - Extension of Foreign Profile
     * @return Foreign Profile object
     */
    <T extends ForeignProfile> T getForeignProfile(Class<T> clazz);

    /**
     * @param foreignProfile - Foreign Profile object
     * @param connection - Valid MySQL Connection
     * @param <T> - Extension of Foreign Profile
     */
    <T extends ForeignProfile> void addForeignProfile(T foreignProfile, Connection connection);
}
