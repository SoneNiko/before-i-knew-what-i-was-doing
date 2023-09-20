package eu.brickpics.gungame.main;

import eu.brickpics.gungame.commands.CMDStats;
import eu.brickpics.gungame.commands.CMDTest;
import eu.brickpics.gungame.listener.*;
import eu.brickpics.gungame.manager.NPCManager;
import eu.brickpics.gungame.manager.ScoreboardManager;
import eu.brickpics.gungame.manager.multilang.AchievementManager;
import eu.brickpics.gungame.storage.MYSQL;
import eu.brickpics.gungame.storage.SQLWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

public class GunGame extends JavaPlugin {

    private static GunGame instance;
    private static NPCManager npcManager;
    public static MYSQL mysql;

    @Override
    public void onEnable() {
        instance = this;
        this.npcManager = new NPCManager(this);

        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new ExpListener(), this);
        getServer().getPluginManager().registerEvents(new FunctionDisabler(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new MapProtection(), this);
        getServer().getPluginManager().registerEvents(new SpawnProt(), this);

        getServer().getPluginManager().registerEvents(new MenuFunctionListener(), this);

        getCommand("stats").setExecutor(new CMDStats());
        getCommand("test").setExecutor(new CMDTest());

        ConnectMySQL();

        ScoreboardManager.startScoreboard();

        for (Entity entity : getServer().getWorld("world").getEntities()) {
            if (entity instanceof Damageable) {
                ((Damageable) entity).damage(1000.0D);
            }
        }

        AchievementManager.initHashMap();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, SQLWrapper::setGunGameRanking, 20L, 20L * 30);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().getBlock().isLiquid()) {
                    p.damage(10000.0D);
                    if (!AchievementManager.hasAchievement(0, p.getUniqueId().toString()))
                        AchievementManager.setAchievement(0, true, p.getUniqueId().toString());
                }
            }
        }, 0L, 5L);
    }

    public static GunGame getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        instance = null;
        mysql.close();
    }

    private void ConnectMySQL() {
        mysql = new MYSQL("localhost", "minecraft", "mchammer0815", "IamLegendaryCoder2020!");
        mysql.update("CREATE TABLE IF NOT EXISTS GunGameStats(UUID varchar(64), KILLS int, DEATHS int, HIGHSCORE int, SOUNDS int, ACNOTIFY int, TITLES int, ACHIEVEMENTS varchar(64));");
    }


}
