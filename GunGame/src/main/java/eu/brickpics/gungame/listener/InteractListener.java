package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.storage.Data;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
    @EventHandler
    public static void onInteract(PlayerInteractEvent event) {
        if (!event.hasBlock()){
            return;
        }

        if ( event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission("gungame.build")) {
            event.setCancelled(false);
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.TRAP_DOOR ||event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.FURNACE ||event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.BED_BLOCK ||  event.getClickedBlock().getType() == Material.ENDER_CHEST || event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE || event.getClickedBlock().getType() == Material.CHEST || event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.WORKBENCH) {
            event.getPlayer().sendMessage(Data.PREFIX + MessageManager.getString("forbidden", event.getPlayer()));
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Physical means jump on it
        if (event.getAction() == Action.PHYSICAL) {
            Block block = event.getClickedBlock();
            if (block == null) return;
            // If the block is farmland (soil)
            if (block.getType() == Material.SOIL) {
                // Deny event and set the block
                event.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
                event.setCancelled(true);
                block.setTypeIdAndData(block.getType().getId(), block.getData(), true);
            }
        }
    }
}
