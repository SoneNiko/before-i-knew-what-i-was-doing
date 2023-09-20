package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.manager.LevelManager;
import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.storage.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    @EventHandler
    public static void onDrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        event.setCancelled(true);
        //LevelManager.giveEquip(p);
        p.sendMessage(Data.PREFIX + MessageManager.getString("forbidden", p));
    }

}
