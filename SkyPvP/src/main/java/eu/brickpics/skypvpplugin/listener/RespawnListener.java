package eu.brickpics.skypvpplugin.listener;

import eu.brickpics.skypvpplugin.main.SkyPvPPlugin;
import eu.brickpics.skypvpplugin.manager.ItemManager;
import eu.brickpics.skypvpplugin.tasks.Events;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Material.*;

public class RespawnListener implements Listener{



    SkyPvPPlugin plugin;
    public RespawnListener(SkyPvPPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent e) {

        final Player p = e.getPlayer();

        if(Events.currentEvent == 0){
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
            {
                public void run()
                {
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                }

            }, (1));



        } else if (Events.currentEvent == 1) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
            {
                public void run()
                {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                }

            }, (1));


        } else if (Events.currentEvent == 2) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
            {
                public void run()
                {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 3));
                }

            }, (1));


        } else if (Events.currentEvent == 3) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
            {
                public void run()
                {
                    p.getInventory().setItem(4, (new ItemManager(GOLDEN_APPLE).build()));
                }

            }, (1));


        }





    }
}
