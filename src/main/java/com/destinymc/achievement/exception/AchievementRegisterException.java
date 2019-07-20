package com.destinymc.achievement.exception;

import com.destinymc.achievement.Achievement;

/**
 * @author Luke Bingham
 */
public final class AchievementRegisterException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public AchievementRegisterException(Achievement achievement) {
        super("Achievement(" + achievement.getUniqueId() + ") is already registered");
    }
}
