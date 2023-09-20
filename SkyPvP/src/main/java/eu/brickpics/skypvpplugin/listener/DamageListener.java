package eu.brickpics.skypvpplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            event.setCancelled(true);
        else if (event.getEntity().getLocation().getBlockY() >= 127) {
            event.setCancelled(true);
        }
    }
}
