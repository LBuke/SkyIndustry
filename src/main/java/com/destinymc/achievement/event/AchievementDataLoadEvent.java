package com.destinymc.achievement.event;

import com.destinymc.achievement.profile.AchievementProfile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Luke Bingham
 */
public final class AchievementDataLoadEvent extends Event {

    private final AchievementProfile achievementProfile;

    /**
     * Construct a new Event
     */
    public AchievementDataLoadEvent(AchievementProfile achievementProfile) {
        super(false);
        this.achievementProfile = achievementProfile;
    }

    public AchievementProfile getAchievementProfile() {
        return achievementProfile;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
