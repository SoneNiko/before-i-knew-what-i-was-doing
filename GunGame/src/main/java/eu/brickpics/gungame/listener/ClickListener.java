package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.manager.multilang.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;


public class ClickListener implements Listener {
    @EventHandler
    public static void onClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
            event.setCancelled(true);
            p.closeInventory();
            p.sendMessage(MessageManager.getString("forbidden", p));
        }
    }
}