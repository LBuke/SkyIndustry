package com.destinymc.database;

import java.sql.Connection;

/**
 * @author Luke Bingham
 */
public interface Database {

    /**
     * Get the credentials for the database.
     *
     * @return The credentials for the database.
     */
    DatabaseCredentials getCredentials();

    /**
     * Get the connection for the database.
     *
     * @return The connection for the database.
     */
    Connection getConnection();
}
