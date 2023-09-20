package eu.brickpics.skypvpplugin.listener;

import eu.brickpics.skypvpplugin.main.SkyPvPPlugin;
import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Material.*;

public class AdminListener implements Listener {


    SkyPvPPlugin plugin;


    public AdminListener(SkyPvPPlugin plugin) {
        this.plugin = plugin;
    }






    @EventHandler
    private void adminMenuClick(InventoryClickEvent e){ //Opens AdminMenu on PlayerClick

        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lEvent Menu")){
            e.setCancelled(true);

            //Creating Admininv
            Inventory admininv = Bukkit.getServer().createInventory(p,9,"§6§lEvent Menu");

            admininv.setItem(0, new ItemManager(IRON_BOOTS).setDisplayName("§6§lSPEED").build());
            admininv.setItem(1, new ItemManager(FEATHER).setDisplayName("§6§lBUNNY").build());
            admininv.setItem(2, new ItemManager(GOLDEN_APPLE).setDisplayName("§6§lGOLDEN APPLE").build());


            p.openInventory(admininv);




        }

    }
    public static int nextEvent = 0;
    @EventHandler
    private void eventChooser(InventoryClickEvent e){ //

        if (e.getInventory().getTitle().equalsIgnoreCase("§6§lEvent Menu")) { //gets Inventory with name Event Menu


            final Player p = (Player) e.getWhoClicked();


            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSPEED")){ //get Item with Name SPEED

                e.setCancelled(true);

                //Sets next Event to first Event
                AdminListener.nextEvent = 1;
                p.sendMessage("§8┃ §2SkyPvP §8× §7" + " §6§lNext Event will be SPEED!");





            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lBUNNY")){ //get Item with Name Bunny

                e.setCancelled(true);

                //Sets next Event to second Event
                AdminListener.nextEvent = 2;
                p.sendMessage("§8┃ §2SkyPvP §8× §7" + " §6§lNext Event will be BUNNY!");


            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lGOLDEN APPLE")){ //get Item with Name Bunny

                e.setCancelled(true);

                //Sets next Event to second Event
                AdminListener.nextEvent = 3;
                p.sendMessage("§8┃ §2SkyPvP §8× §7" + " §6§lNext Event will be GOLDEN APPLE!");


            }


        }


    }
}


