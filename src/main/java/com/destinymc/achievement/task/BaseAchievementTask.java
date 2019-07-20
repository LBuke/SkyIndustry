package com.destinymc.achievement.task;

/**
 * @author Luke Bingham
 */
public class BaseAchievementTask implements AchievementTask {

    private final int goal;

    public BaseAchievementTask(int goal) {
        this.goal = goal;
    }

    /**
     * This is the target/goal of the achievement value
     *
     * @return target / goal
     */
    @Override
    public int getGoal() {
        return this.goal;
    }
}
