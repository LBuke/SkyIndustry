package com.destinymc.profile;

import javax.annotation.Nonnull;
import java.sql.Connection;

/**
 * Created at 19/02/2019
 *
 * @author Luke Bingham
 */
public abstract class ForeignProfile {

    protected final Profile profile;

    /**
     * Construct a new Foreign Profile connected to Profile.
     *
     * @param profile - Original Base Profile
     */
    public ForeignProfile(@Nonnull Profile profile) {
        this.profile = profile;
    }

    /**
     * This method can be overridden to interact<br>
     *     with the database upon being initialised.
     *
     * @param connection - Valid MySQL Connection
     */
    public void init(Connection connection) {}

    public Profile getProfile() {
        return profile;
    }
}
