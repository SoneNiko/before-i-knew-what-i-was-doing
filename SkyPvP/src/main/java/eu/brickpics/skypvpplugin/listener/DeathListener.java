package eu.brickpics.skypvpplugin.listener;

import eu.brickpics.skypvpplugin.main.SkyPvPPlugin;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class DeathListener implements Listener {

    SkyPvPPlugin plugin;
    public DeathListener(SkyPvPPlugin plugin) {
        this.plugin = plugin;
    }




    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {

        event.setKeepLevel(true);
        event.setDroppedExp(0);


        final Player player = event.getEntity();
        Player killer = player.getKiller();
        killer.setLevel(killer.getLevel() + 1);

        for(Player all : Bukkit.getOnlinePlayers()) {

            if (killer != null) {
                event.setDeathMessage("§8┃ §2SkyPvP §8× §7 " + player.getDisplayName() + " was killed by " + player.getKiller().getDisplayName());
                player.sendMessage("§8┃ §2SkyPvP §8× §7He was on " + player.getKiller().getHealth() + " HP");
            } else if(player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.VOID)){
                event.setDeathMessage("§8┃ §2SkyPvP §8× §7 " + player.getDisplayName() + " was killed by " + player.getKiller().getDisplayName() );
            }
        }
        //Disable Respawn Button
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
            public void run(){
                if (player.isDead()){
                    ((CraftPlayer)player).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                }
            }
        });



    }
}
