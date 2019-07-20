package com.destinymc.database;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created at 25/02/2019
 *
 * @author Luke Bingham
 */
public class DatabaseHandler implements Database {

    /** The conf MySQL driver */
    private static final String DEFAULT_MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    /** The database credentials */
    private DatabaseCredentials dbCreds;

    /** Data source connection pool from HikariCP */
    private final HikariDataSource hikariSource = new HikariDataSource();

    // NOTE: HikariCP performs best at fixed pool size, minIdle=maxConns
    // https://github.com/brettwooldridge/HikariCP

    /** How many minimum idle connections should we always have (2) */
    protected int minIdle = 2;

    /** How many max connections should exist in pool (2) */
    protected int maxPoolSize = 2;

    /** How long, in millis, we stop waiting for new connection (15 secs) */
    protected int connectionTimeoutMs = 15 * 1000;

    /** How long, in millis, before connections timeout (45 secs) */
    protected int idleTimeoutMs = 45 * 1000;

    /** How long, in millis, this connection can be alive for (30 mins) */
    protected int maxLifetimeMs = 30 * 60 * 1000;

    /** How long, in millis, can a connection be gone from a pool (4 secs) */
    protected int leakDetectionThresholdMs = 4 * 1000;

    /** The ping alive query */
    protected String connectionTestQuery = "SELECT 1";

    /** Should the connection cache prepared statements */
    protected boolean cachePreparedStatements = true;

    /** Number of prepared statements to cache per connection */
    protected int preparedStatementCache = 250;

    /** Max number of prepared statements to have */
    protected int maxPreparedStatementCache = 2048;

    /** The log writer for Hikari */
    protected PrintWriter logWriter = new PrintWriter(System.out);

    /**
     * Initialize the handler with the specified database credentials.
     * <p>
     * Sets up the configuration for the connection pool and conf settings.
     * </p>
     *
     * @param dbCreds - the credentials for the database
     * @param driver - the driver class
     */
    public void init(DatabaseCredentials dbCreds, String driver) {
        this.dbCreds = dbCreds;

        // set the driver name for the connection driver
        hikariSource.setDriverClassName(driver);

        // assume host/port combo together, or could just be without port
        String connURL = dbCreds.getHost();

        // if a port is defined
        if (dbCreds.getPort() > 0) {
            connURL = dbCreds.getHost() + ":" + dbCreds.getPort();
        }

        // set the jdbc url, note the character encoding
        // https://stackoverflow.com/questions/3040597/jdbc-character-encoding
        //autoReconnect=true&useSSL=false
        hikariSource.setJdbcUrl("jdbc:mysql://" + connURL + "/" + dbCreds.getName() + "?characterEncoding=UTF-8&autoReconnect=true&useSSL=false");

        // set user/pass
        hikariSource.setUsername(dbCreds.getUser());
        hikariSource.setPassword(dbCreds.getPass());

        /** General conf settings for hikari */

        // works best when minIdle=maxPoolSize
        hikariSource.setMinimumIdle(minIdle);
        hikariSource.setMaximumPoolSize(maxPoolSize);

        // how long to wait, for a new connection
        hikariSource.setConnectionTimeout(connectionTimeoutMs);

        // how long before idle connection is destroyed
        hikariSource.setIdleTimeout(idleTimeoutMs);

        // how long can a connection exist
        hikariSource.setMaxLifetime(maxLifetimeMs);

        // how long connection is away from a pool before saying uh oh
        hikariSource.setLeakDetectionThreshold(leakDetectionThresholdMs);

        // test query to confirm alive
        hikariSource.setConnectionTestQuery(connectionTestQuery);

        // should we cache prepared statements
        hikariSource.addDataSourceProperty("cachePrepStmts", "" + cachePreparedStatements);

        // the size of the prepared statement cache
        hikariSource.addDataSourceProperty("prepStmtCacheSize", "" + preparedStatementCache);

        // the maximum cache limit
        hikariSource.addDataSourceProperty("prepStmtCacheSqlLimit", "" + maxPreparedStatementCache);

        // MUST set log writer
        try {
            hikariSource.setLogWriter(new PrintWriter(System.out));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the database handler given the credentials.
     *
     * @param credentials - the login details to this database
     */
    protected void init(DatabaseCredentials credentials) {
        this.init(credentials, DEFAULT_MYSQL_DRIVER);
    }

    /**
     * Load the settings for HikariCP from the plugin config and stores them
     * locally in the object, then initializes the database handler.
     *
     * @param plugin - the owning plugin
     * @param path - the path to load settings from
     */
    public void init(Plugin plugin, String path) {

        // grab plugin file configuration
        FileConfiguration config = plugin.getConfig();

        String host = config.getString(path + ".host", "localhost");
        int port = config.getInt(path + ".port", 3306);
        String dbName = config.getString(path + ".database", "dbName");
        String username = config.getString(path + ".user", "user");
        String password = config.getString(path + ".password", "pass123");

        // connection stats
        int minIdle = config.getInt(path + ".min-idle", 2);
        int maxConns = config.getInt(path + ".max-conn", 2);

        // save db credentials
        config.set(path + ".host", host);
        config.set(path + ".port", port);
        config.set(path + ".database", dbName);
        config.set(path + ".user", username);
        config.set(path + ".password", password);

        // save the config
        plugin.saveConfig();

        // load local fields
        this.minIdle = minIdle < 0 ? 1 : minIdle;
        this.maxPoolSize = maxConns < 1 ? 1 : maxConns;

        // create database credentials
        DatabaseCredentials creds = new DatabaseCredentials(host, port, dbName, username, password);

        // initialize hikari cp
        init(creds);
    }

    /**
     * Load the settings for HikariCP from the yaml config and stores them
     * locally in the object, then initializes the database handler.
     *
     * @param config - the yaml configuration
     */
    public void init(YamlConfiguration config) {

        String host = config.getString("host", "localhost");
        int port = config.getInt("port", 3306);
        String dbName = config.getString("database", "dbName");
        String username = config.getString("user", "user");
        String password = config.getString("password", "pass123");

        // connection stats
        int minIdle = config.getInt("min-idle", 2);
        int maxConns = config.getInt("max-conn", 2);

        // load local fields
        this.minIdle = minIdle < 0 ? 1 : minIdle;
        this.maxPoolSize = maxConns < 1 ? 1 : maxConns;

        // create database credentials
        DatabaseCredentials creds = new DatabaseCredentials(host, port, dbName, username, password);

        // initialize hikari cp
        init(creds);
    }

    /**
     * Load the settings for HikariCP from the yaml config and stores them
     * locally in the object, then initializes the database handler.
     *
     * @param config - the yaml configuration
     */
    public void init(FileConfiguration config) {

        String host = config.getString("host", "localhost");
        int port = config.getInt("port", 3306);
        String dbName = config.getString("database", "test");
        String username = config.getString("user", "user");
        String password = config.getString("password", "pass123");

        // connection stats
        int minIdle = config.getInt("min-idle", 2);
        int maxConns = config.getInt("max-conn", 2);

        // load local fields
        this.minIdle = minIdle < 0 ? 1 : minIdle;
        this.maxPoolSize = maxConns < 1 ? 1 : maxConns;

        // create database credentials
        DatabaseCredentials creds = new DatabaseCredentials(host, port, dbName, username, password);

        // initialize hikari cp
        init(creds);
    }

    /**
     * Close HikariCP connection pool, and all the connections.
     * <p>
     * Note: This should be called whenever the plugin turns off!
     * </p>
     */
    public void close() {
        if (!hikariSource.isClosed()) {
            hikariSource.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseCredentials getCredentials() {
        return dbCreds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Connection getConnection() {

        try {
            return hikariSource.getConnection();
        }
        catch (Exception e) {
            System.out.println("[DatabaseHandler] Unable to grab a connection from the connection pool!");
            e.printStackTrace();
        }

        return null;
    }
}
