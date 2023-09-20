package eu.brickpics.skypvpplugin.listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener{

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        e.setQuitMessage("§8┃ §2SkyPvP §8× §7" + player.getDisplayName() + " left");


    }
}
