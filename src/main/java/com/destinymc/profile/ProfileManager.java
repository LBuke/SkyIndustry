package com.destinymc.profile;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public final class ProfileManager {

    private static final LoadingCache<UUID, Profile> PROFILE_MAP;

    static {
        PROFILE_MAP = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.HOURS).build(new CacheLoader<UUID, Profile>() {
            @Override
            public Profile load(@Nonnull UUID uuid) {
                return new BaseProfile(uuid);
            }
        });
    }

    public ProfileManager(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new ProfileHandler(plugin), plugin);
    }

    public static Profile getProfile(@Nonnull UUID uuid) {
        try {
            return PROFILE_MAP.get(uuid);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void invalidate(@Nonnull UUID uuid) {
        PROFILE_MAP.invalidate(uuid);
    }

    public static <T extends ForeignProfile> T getForeignProfile(UUID uuid, Class<T> foreignClass) {
        if (uuid == null) return null;

        Profile profile = getProfile(uuid);
        if (profile == null) return null;

        ForeignProfile foreignProfile = profile.getForeignProfile(foreignClass);
        if (foreignProfile == null) return null;

        return (T) foreignProfile;
    }

    public static <T extends ForeignProfile> T getForeignProfile(Player player, Class<T> foreignClass) {
        return getForeignProfile(player.getUniqueId(), foreignClass);
    }
}
