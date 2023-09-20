package eu.brickpics.mentionsys.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SQL {
    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    private void makeConnection() {
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/minecraft?useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin" );
        config.setUsername( "mchammer0815" );
        config.setPassword( "IamLegendaryCoder2020!" );
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.addDataSourceProperty( "useSSL", "false");
        ds = new HikariDataSource( config );
    }

    public Connection getConnenction() throws SQLException {
        return ds.getConnection();
    }


    private static final eu.brickpics.mentionsys.util.SQL instance = new eu.brickpics.mentionsys.util.SQL();

    private SQL() {
        this.makeConnection();
    }

    public static SQL getInstance(){
        return instance;
    }



}
