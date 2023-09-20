package eu.brickpics.brickcordbot.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class SQLWrapper {
    public static HikariConfig config = new HikariConfig();
    public static HikariDataSource ds;

    public static void makeConnection() {
        //SQLWrapper.config.setJdbcUrl( "jdbc:mysql://localhost:3306/minecraft?useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin" );
        //SQLWrapper.config.setUsername( "mchammer0815" );
        //SQLWrapper.config.setPassword( "IamLegendaryCoder2020!" );
        //SQLWrapper.config.setDriverClassName("com.mysql.jdbc.Driver");
        //SQLWrapper.config.addDataSourceProperty( "cachePrepStmts" , "true" );
        //SQLWrapper.config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        //SQLWrapper.config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        //SQLWrapper.ds = new HikariDataSource( config );
    }
}
