package eu.brickpics.search;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SQLManager {
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    private void makeConnection() {
        this.config.setJdbcUrl( "jdbc:mysql://localhost:3306/minecraft?useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin" );
        this.config.setUsername( "mchammer0815" );
        this.config.setPassword( "IamLegendaryCoder2020!" );
        this.config.setDriverClassName("com.mysql.jdbc.Driver");
        this.config.addDataSourceProperty( "cachePrepStmts" , "true" );
        this.config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        this.config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        this.config.addDataSourceProperty( "useSSL", "false");
        this.ds = new HikariDataSource( config );
    }

    public Connection getConnenction() throws SQLException {
        return ds.getConnection();
    }


    private static final SQLManager instance = new SQLManager();

    private SQLManager() {
        this.makeConnection();
    }

    public static SQLManager getInstance(){
        return instance;
    }


}
