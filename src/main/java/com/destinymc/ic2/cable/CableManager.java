package com.destinymc.ic2.cable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created at 13/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class CableManager {

    private final Cache<Integer, HashSet<CableSystem>> cableCache;

    public CableManager() {
        this.cableCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
    }

    public ConcurrentMap<Integer, HashSet<CableSystem>> getCableCache() {
        return cableCache.asMap();
    }

    /**
     * This will add a CableSystem into the 30 minute Cache.
     *
     * @param id - ASkyblock island identifier
     * @param cableSystem - a cable system
     * @return whether the system was added
     */
    public boolean addCableSystem(int id, CableSystem cableSystem) {
        HashSet<CableSystem> systems = this.getCableSystems(id);
        if (systems == null) {
            systems = Sets.newHashSet();
        }

        if (systems.contains(cableSystem)) {
            return false;
        }

        return systems.add(cableSystem);
    }

    /**
     * Get a collection of CableSystems on an Island.
     *
     * @param id - ASkyblock island identifier
     * @return Collection of CableSystems
     */
    public HashSet<CableSystem> getCableSystems(int id) {
        return cableCache.asMap().getOrDefault(id, null);
    }
}
