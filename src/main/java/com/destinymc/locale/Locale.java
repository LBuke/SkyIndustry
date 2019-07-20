package com.destinymc.locale;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public enum Locale {

    EN_GB(1, "en_gb"),
    EN_US(2, "en_us"),
    DE_DE(3, "de_de"),

    //More will be added gradually.
    ;

    private final int id;
    private final String tag;

    Locale(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public final String getTag() {
        return tag;
    }

    public static Locale fromString(String key) {
        for(Locale locale : values()) {
            if(!locale.getTag().equals(key)) continue;
            return locale;
        }

        return I18n.DEFAULT;
    }

    public static Locale fromId(int id) {
        for(Locale locale : values()) {
            if(locale.getId() != id) continue;
            return locale;
        }

        return I18n.DEFAULT;
    }
}
