package com.destinymc.util;

import com.destinymc.factory.MapFactory;
import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class StringMap extends MapFactory<String, String, HashMap<String, String>> {

    public StringMap() {
        super(Maps.newHashMap());
    }

    public StringMap put(String key, Object value) {
        super.put(key, String.valueOf(value));
        return this;
    }

    public StringMap put(String key, String value) {
        super.put(key, String.valueOf(value));
        return this;
    }
}
