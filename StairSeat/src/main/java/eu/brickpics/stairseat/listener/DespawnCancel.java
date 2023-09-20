package eu.brickpics.stairseat.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

public class DespawnCancel implements Listener {
    @EventHandler
    public void onDespawn(ItemDespawnEvent event) {
        Bukkit.getLogger().info(event.toString());
        if (event.getEntityType().equals(EntityType.ARROW)) {
            Arrow arrow = (Arrow) event.getEntity();
            event.setCancelled(true);
        }
    }
}
