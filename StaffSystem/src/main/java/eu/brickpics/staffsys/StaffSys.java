package eu.brickpics.staffsys;

import eu.brickpics.staffsys.punishment.PunishmentManager;
import eu.brickpics.staffsys.storage.MYSQL;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;


public final class StaffSys extends JavaPlugin {
    public FileConfiguration config;
    private static MYSQL mysql;

    public static MYSQL getMysql() {
        return mysql;
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    private static PunishmentManager punishmentManager;

    public static PunishmentManager getPunishmentManager() {
        return punishmentManager;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        ConnectMySQL();
        punishmentManager = new PunishmentManager();
    }

    @Override
    public void onDisable() {
        mysql.close();
    }


    private void ConnectMySQL() {
        mysql = new MYSQL(config.getString("host"), config.getString("database"), config.getString("user"), config.getString("password"));
        mysql.update("CREATE TABLE IF NOT EXISTS StaffSystemCases(UUID varchar(36), NAME varchar(16), STARTTIME varchar(20), ENDTIME varchar(20), PUNISHMENT varchar(32), REASON varchar(255), STAFFUUID varchar(36));");
    }
}
