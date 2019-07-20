package com.destinymc.locale;

import com.destinymc.misc.Unique;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class I18nString implements Unique<String> {

    private final String key;
    private final String value;

    public I18nString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getUniqueId() {
        return key;
    }
}
