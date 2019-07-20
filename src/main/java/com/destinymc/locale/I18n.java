package com.destinymc.locale;

import com.destinymc.util.C;
import com.destinymc.util.FileUtil;
import com.destinymc.util.StringMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class I18n {

    private static final HashMap<Locale, Set<I18nString>> LOCALE_MAP;

    public static final Locale DEFAULT = Locale.EN_US;

    static {
        LOCALE_MAP = Maps.newHashMap();

        for (Locale locale : Locale.values()) {
            Properties properties = FileUtil.loadPropertiesFromResource("locale/" + locale.getTag() + ".properties");
            if (properties == null) continue;

            Set<I18nString> strings = Sets.newHashSet();
            for (String key : properties.stringPropertyNames()) {
                strings.add(new I18nString(key, properties.getProperty(key, C.RED + "Missing Text")));
            }

            LOCALE_MAP.put(locale, strings);
        }
    }

    /**
     * Get the Locale string from the cached i18n.
     *
     * @param locale - Locale for the query
     * @param key - Key identifier for the query
     * @return Locale string cache
     */
    private static I18nString getString(Locale locale, String key) {
        if (!LOCALE_MAP.containsKey(locale))
            locale = DEFAULT;

        Set<I18nString> set = LOCALE_MAP.getOrDefault(locale, null);
        if (set == null) return null;

        return set.stream().filter(i18nString -> i18nString.getKey().equals(key)).findFirst().orElse(null);
    }

    public static boolean isKeyNull(String key) {
        return getString(DEFAULT, key) == null;
    }

    public static String get(String key) {
        I18nString string = getString(DEFAULT, key);
        if (string == null) return C.RED + "Missing Text";

        return C.translate(string.getValue());
    }

    public static String get(String key, StringMap data) {
        I18nString string = getString(DEFAULT, key);
        if (string == null) return C.RED + "Missing Text";

        String value = string.getValue();
        for (String dataTag : data.getMap().keySet()) {
            value = value.replaceAll(Pattern.quote("{" + dataTag + "}"), data.getValue(dataTag));
        }

        return C.translate(value);
    }

    public static String get(Locale locale, String key) {
        I18nString string = getString(locale, key);
        if (string == null) return C.RED + "Missing Text";

        return C.translate(string.getValue());
    }

    public static String get(Locale locale, String key, StringMap data) {
        I18nString string = getString(locale, key);
        if (string == null) return C.RED + "Missing Text";

        String value = string.getValue();
        for (String dataTag : data.getMap().keySet()) {
            value = value.replaceAll(Pattern.quote("{" + dataTag + "}"), data.getValue(dataTag));
        }

        return C.translate(value);
    }

    public static String[] getAsArray(Locale locale, String key) {
        I18nString string = getString(locale, key);
        if (string == null) return new String[] {C.RED + "Missing Text"};

        String value = string.getValue();
        String[] array = new String[] {value};

        if (value.contains("{n}"))
            array = value.split(Pattern.quote("{n}"));

        for (int i = 0; i < array.length; i++)
            array[i] = C.translate(array[i]);

        return array;
    }

    public static String[] getAsArray(Locale locale, String key, StringMap data) {
        I18nString string = getString(locale, key);
        if (string == null) return new String[] {C.RED + "Missing Text"};

        String value = string.getValue();
        for (String dataTag : data.getMap().keySet()) {
            value = value.replace("{" + dataTag + "}", data.getValue(dataTag));
        }

        String[] array = new String[] {value};

        if (value.contains("{n}"))
            array = value.split(Pattern.quote("{n}"));

        for (int i = 0; i < array.length; i++)
            array[i] = C.translate(array[i]);

        return array;
    }

    public static List<String> getAsList(Locale locale, String key) {
        return Arrays.asList(getAsArray(locale, key));
    }

    public static List<String> getAsList(Locale locale, String key, StringMap data) {
        return Arrays.asList(getAsArray(locale, key, data));
    }
}
