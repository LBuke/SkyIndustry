package com.destinymc.locale;

import com.destinymc.util.StringMap;
import org.junit.Assert;

import java.util.Arrays;

import static org.junit.Assert.*;

public class I18nTest {

    @org.junit.Test
    public void get() {
        I18n.get(Locale.EN_US, "test1");
        I18n.get(Locale.EN_US, "test2", new StringMap().put("tag", 2));
    }

    @org.junit.Test
    public void getAsArray() {
        I18n.getAsArray(Locale.EN_US, "test3");
    }

    @org.junit.Test
    public void getAsArrayFail() {
        String[] str = I18n.getAsArray(Locale.EN_US, "404",
                new StringMap().put("level", 2).put("rank", "DEV")
        );

        Assert.assertEquals(str[0], "§cMissing Text");
    }

    @org.junit.Test
    public void getAsList() {
        I18n.getAsList(Locale.EN_US, "test3");
        I18n.getAsList(Locale.EN_US, "test3", new StringMap().put("tag", 2));
    }
}