package eu.brickpics.skypvpplugin.main;

import eu.brickpics.skypvpplugin.commands.CMDEvents;
import eu.brickpics.skypvpplugin.commands.CMDKit;
import eu.brickpics.skypvpplugin.commands.CMDKitArmorStand;
import eu.brickpics.skypvpplugin.listener.*;
import eu.brickpics.skypvpplugin.tasks.Events;
import eu.brickpics.skypvpplugin.tasks.GroundItemClear;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class SkyPvPPlugin extends JavaPlugin {


    @Override
    public void onEnable() {




        //Runs Event Task
        BukkitTask events = new Events(this).runTaskTimer(this,0L,10*60*20L);
        BukkitTask grounditems = new GroundItemClear(this).runTaskTimer(this,0L,5*60*20L);





        getLogger().info("Hello");
        getCommand("kit" ).setExecutor(new CMDKit());
        getCommand("event" ).setExecutor(new CMDEvents());
        getCommand("kitarmorstand" ).setExecutor(new CMDKitArmorStand());

        Bukkit.getPluginManager().registerEvents(new LeaveListener(),this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(),this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(),this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(this),this);
        Bukkit.getPluginManager().registerEvents(new DropListener(),this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new RespawnListener(this),this);
        Bukkit.getPluginManager().registerEvents(new MoveListener(),this);
        Bukkit.getPluginManager().registerEvents(new AdminListener(this),this);
        Bukkit.getPluginManager().registerEvents(new WeatherListener(),this);
        Bukkit.getPluginManager().registerEvents(new ArmorStandKitListener(),this);
        Bukkit.getPluginManager().registerEvents(new KitListener(),this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Turret: Good Bye");
    }
}
