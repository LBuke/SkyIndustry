package com.destinymc.rank;

import com.destinymc.misc.Unfinished;
import org.bukkit.entity.Player;

/**
 * Created at 28/02/2019
 *
 * @author Luke Bingham
 */
@Unfinished
public enum Rank {

    DEFAULT("default"),

    VIP("vip"),
    MVP("mvp"),
    YOUTUBER("youtube"),

    MODERATOR("mod"),
    ADMINISTRATOR("admin"),
    MANAGER("manager"),
    ;

    private final String id;
    private final String permission;

    Rank(String id) {
        this.id = id;
        this.permission = "ic2." + id;
    }

    public String getPermission() {
        return permission;
    }

    public String getName() {
        return id;
    }

    private static Rank[] RANKS = {
            MANAGER,
            ADMINISTRATOR,
            MODERATOR,
            MVP,
            VIP,
            DEFAULT,
    };

    public static Rank getUserRank(Player player) {
        for (Rank rank : RANKS) {
            if (player.hasPermission(rank.permission))
                return rank;
        }

        return DEFAULT;
    }

    public static String getName(Player player) {
        Rank rank = getUserRank(player);

        // TODO, Get rank prefex from permissions plugin. (whatever that is)

        return rank.id;
    }
}
