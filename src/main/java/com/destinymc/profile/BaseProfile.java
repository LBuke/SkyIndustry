package com.destinymc.profile;

import com.destinymc.profile.foreign.LocaleProfile;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public final class BaseProfile implements Profile {

    private final HashMap<Class<? extends ForeignProfile>, ForeignProfile> foreignProfiles;

    private final UUID uniqueId;

    public BaseProfile(UUID uniqueId) {
        this.uniqueId = uniqueId;
        this.foreignProfiles = Maps.newHashMap();

        this.addForeignProfile(new LocaleProfile(this), null);
    }

    /**
     * @return Unique Identifier
     */
    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    /**
     * @return Player object from the Unique Id
     */
    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }

    /**
     * Get a Foreign Profile assigned to this Profile.
     *
     * @param clazz - Foreign Profile class
     * @return Foreign Profile object
     */
    @Override
    public <T extends ForeignProfile> T getForeignProfile(Class<T> clazz) {
        return (T) this.foreignProfiles.getOrDefault(clazz, null);
    }

    /**
     * @param foreignProfile - Foreign Profile object
     * @param connection - Valid MySQL Connection
     * @param <T> - Extension of Foreign Profile
     */
    @Override
    public <T extends ForeignProfile> void addForeignProfile(T foreignProfile, Connection connection) {
        this.foreignProfiles.put(foreignProfile.getClass(), foreignProfile);
        foreignProfile.init(connection);
    }
}
