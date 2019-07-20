package com.destinymc.util;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.List;

/**
 * Created at 25/02/2019
 *
 * @author Luke Bingham
 */
public final class StringUtil {

    private static final int CENTER_PX = 154;
    private static final int CENTER_MENU_PX = 80;
    private static final int LORE_LENGTH = 80;

    public static String getCenteredMessage(String message) {
        if (message == null || message.equals(""))
            return "";

        message = C.translate(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == C.COLOR_CHAR) {
                previousCode = true;
                continue;
            }

            else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                }
                else
                    isBold = false;
            }

            else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        return sb.toString() + message;
    }

    public static String getCenteredMenuText(String message) {
        message = C.translate(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == C.COLOR_CHAR) {
                previousCode = true;
                continue;
            }

            else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                }
                else
                    isBold = false;
            }

            else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_MENU_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        return sb.toString() + message;
    }

    public static List<String> getShortenedLore(String input, int maxPixelsPerLine) {
        input = C.translate(input);

        List<String> splitResult = Lists.newArrayList();
        String temp = "";
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : input.toCharArray()) {
            if (messagePxSize >= maxPixelsPerLine && (c == ' ' || c == '.')) {
                splitResult.add(temp);
                temp = ChatColor.getLastColors(temp) + "";
                messagePxSize = 0;
                continue;
            }

            temp += c;

            if (c == C.COLOR_CHAR) {
                previousCode = true;
                continue;
            }

            else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                }

                isBold = false;
                continue;
            }

            DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
            messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
            messagePxSize++;
        }

        splitResult.add(temp);

        return splitResult;
    }

    public static List<String> getShortenedLore(String input) {
        return getShortenedLore(input, LORE_LENGTH);
    }

    public static String getProgressBar(int current, int max, int totalBars, char symbol, String completedColor, String notCompletedColor, boolean reverse) {
        int progressBars = (int) (totalBars * (float) current / max);
        int leftOver = (totalBars - progressBars);

        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.translateAlternateColorCodes('&', completedColor));
        for (int i = 0; i < (reverse ? leftOver : progressBars); i++) {
            sb.append(symbol);
        }

        sb.append(ChatColor.translateAlternateColorCodes('&', notCompletedColor));
        for (int i = 0; i < (reverse ? progressBars : leftOver); i++) {
            sb.append(symbol);
        }

        return sb.toString();
    }
}
