package eu.brickpics.skypvpplugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener{

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player p = event.getPlayer();


        if (p.getLocation().getY() <= 140 && p.getLocation().getY() >= 138) {

        }



    }

}
