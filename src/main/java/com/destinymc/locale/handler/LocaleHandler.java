package com.destinymc.locale.handler;

import com.destinymc.locale.Locale;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Pattern;

/**
 * Created at 16/02/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class LocaleHandler<T, E> {

    protected static final Pattern PATTERN;

    static {
        PATTERN = Pattern.compile("\\{t=.*}");
    }

    protected JavaPlugin plugin;

    public LocaleHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract E translate(Locale locale, T input);

    public MatchResult attemptMatch(String input) {
        if (input == null)
            return null;

        if (!PATTERN.matcher(input).find())
            return null;

        String text = input.substring(3, input.length()-1);
        if (text.length() > 0)
            return new MatchResult(text);

        return null;
    }

    protected class MatchResult {
        private final String text;

        public MatchResult(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
