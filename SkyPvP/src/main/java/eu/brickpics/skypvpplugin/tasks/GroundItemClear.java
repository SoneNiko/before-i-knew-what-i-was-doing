package eu.brickpics.skypvpplugin.tasks;

import eu.brickpics.skypvpplugin.main.SkyPvPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class GroundItemClear extends BukkitRunnable {


    SkyPvPPlugin plugin;


    public GroundItemClear(SkyPvPPlugin plugin) {
        this.plugin = plugin;
    }



    @Override
    public void run() {


        for (final Player all : Bukkit.getOnlinePlayers()) {

            all.sendMessage("§8┃ §2SkyPvP §8× §7" + " §6§lGroundItems will be removed in 30 seconds");

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                Events.currentEvent = 0;
                World world = getServer().getWorld("world");
                List<Entity> entList = world.getEntities();

                for (Entity current : entList) {
                    if (current instanceof Item) {
                        current.remove();
                    }
                }
                all.sendMessage("§8┃ §2SkyPvP §8× §7" + " §6§lGroundItems removed");
            }


        }, (30 * 20L));





    }
    }

}

