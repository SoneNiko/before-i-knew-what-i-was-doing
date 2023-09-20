package eu.brickpics.stairseat.listener;

import eu.brickpics.stairseat.SeatManager;
import eu.brickpics.stairseat.Util;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SitListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && Util.isStair(block) && !SeatManager.playerSeats.containsKey(player) && player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                if (block.getBlockData().getAsString().contains("half=bottom")) {
                    SeatManager.sitPlayerDown(player, block);
                }
            }
        }
    }
}
