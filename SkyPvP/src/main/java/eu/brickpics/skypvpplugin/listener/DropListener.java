package eu.brickpics.skypvpplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getLocation().getBlockY() >= 127) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§8┃ §2SkyPvP §8× §7 Oi! You are not allowed to drop stuff");
        }
    }
}
