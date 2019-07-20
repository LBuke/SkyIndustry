package com.destinymc.factory;

import java.util.Map;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public class MapFactory<K, V, T extends Map<K, V>> implements Factory<T> {

    private final T map;

    public MapFactory(T t) {
        this.map = t;
    }

    public MapFactory put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    public T getMap() {
        return map;
    }

    public V getValue(K k) {
        return map.getOrDefault(k, null);
    }

    public T build() {
        return map;
    }
}
