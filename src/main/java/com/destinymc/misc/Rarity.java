package com.destinymc.misc;

/**
 * Created at 01/03/2019
 *
 * @author Luke Bingham
 */
public enum Rarity {

    /** Lowest rarity type, self-explanatory (it's common) */
    COMMON(1, "common"),

    /** Slightly rarer than 'Common' but still somewhat common */
    UNCOMMON(5, "uncommon"),

    /** Rare is classes as a collectible due to being 'rare', technically it's hard to come across */
    RARE(10, "rare"),

    /** Here we're getting juicy! A step down from the highest rarity tier, very hard to come across */
    EPIC(15, "epic"),

    /** Maximum rarity tier! It doesn't get better than this, almost impossible to acquire */
    LEGENDARY(20, "legendary"),

    ;

    private final int id;
    private final String localeKey;

    Rarity(int id, String localeKey) {
        this.id = id;
        this.localeKey = localeKey;
    }

    public int getId() {
        return id;
    }
}
