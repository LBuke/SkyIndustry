package com.destinymc.reward;

import com.destinymc.profile.Profile;

/**
 * @author Luke Bingham
 */
public interface Reward {

    String getName(Profile profile);
    String getNameWithColor(Profile profile);

    boolean onReward(Profile profile);
}
