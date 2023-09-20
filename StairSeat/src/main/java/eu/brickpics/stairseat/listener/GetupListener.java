package eu.brickpics.stairseat.listener;

import eu.brickpics.stairseat.SeatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class GetupListener implements Listener {
    @EventHandler
    public void onStandUp(PlayerToggleSneakEvent event) {
        if(SeatManager.playerSeats.containsKey(event.getPlayer())) {
            Player player = event.getPlayer();
            SeatManager.standPlayerUp(player);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if(SeatManager.playerSeats.containsKey(event.getPlayer())) {
            Player player = event.getPlayer();
            SeatManager.standPlayerUp(player);
        }
    }
}
