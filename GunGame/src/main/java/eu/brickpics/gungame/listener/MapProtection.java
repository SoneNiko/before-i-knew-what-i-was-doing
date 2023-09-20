package eu.brickpics.gungame.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class MapProtection implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled((event.getPlayer().getGameMode() != GameMode.CREATIVE || !event.getPlayer().hasPermission("gungame.build")));
    }

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e) {
        e.setCancelled((e.getPlayer().getGameMode() != GameMode.CREATIVE || !e.getPlayer().hasPermission("gungame.build")));
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL)
            event.setCancelled(true);
    }
}
