package eu.brickpics.skypvpplugin.listener;

import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Material.*;

public class ArmorStandKitListener implements Listener {







    @EventHandler
    public void villagerKit(PlayerInteractAtEntityEvent e){

        if(e.getRightClicked().getType() == EntityType.ARMOR_STAND){


            Player p = (Player) e.getPlayer();

            //defines Kit inv with 9 Slots
            Inventory inv = Bukkit.getServer().createInventory(p,9,"§b§lSkyPVP §6§lKits");


            //Setup Item in kit GUI (Slot 0) and Name of Kit ("§6§lAssassin")
            inv.setItem(0, new ItemManager(DIAMOND_SWORD).setDisplayName("§6§lAssassin").build());

            //Setup Item in kit GUI (Slot 1) and Name of Kit ("§6§lTank")
            inv.setItem(1, new ItemManager(IRON_CHESTPLATE).setDisplayName("§6§lTank").build());

            //Setup Item in kit GUI (Slot 2) and Name of Kit ("§6§lRod")
            inv.setItem(2, new ItemManager(FISHING_ROD).setDisplayName("§6§lRod").build());


            //opens Kit inv
            p.openInventory(inv);
        }
    }
}
