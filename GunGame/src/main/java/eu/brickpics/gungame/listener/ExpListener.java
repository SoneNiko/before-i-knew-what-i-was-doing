package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.manager.LevelManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExpListener implements Listener {
    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        LevelManager.giveEquip(event.getPlayer());
    }
}
