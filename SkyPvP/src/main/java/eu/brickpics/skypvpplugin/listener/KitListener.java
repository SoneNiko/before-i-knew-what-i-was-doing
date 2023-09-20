package eu.brickpics.skypvpplugin.listener;

import eu.brickpics.skypvpplugin.manager.ItemManager;
import eu.brickpics.skypvpplugin.tasks.Events;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static org.bukkit.Material.*;

public class KitListener implements Listener {

    @EventHandler
    private void inventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equalsIgnoreCase("§b§lSkyPVP §6§lKits")) {





            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAssassin")){    //Get Kit ("§6§lAssassin")

                e.setCancelled(true);
                p.sendMessage("§d§l>> §6Selected §b§lAssassin");
                p.closeInventory();
                p.getInventory().clear();
                p.getInventory().setArmorContents(null);
                //Play Anvil sound
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);

                //Setup Kit Assassin
                p.getInventory().setItem(0, new ItemManager(DIAMOND_SWORD).build());
                p.getInventory().setItem(1, new ItemManager(COOKED_BEEF).setAmount(12).build());
                p.getInventory().setItem(2, new ItemManager(GOLDEN_APPLE).setAmount(3).build());
                p.getInventory().setBoots(new ItemManager(IRON_BOOTS).build());
                p.getInventory().setChestplate(new ItemManager(IRON_CHESTPLATE).build());
                p.getInventory().setLeggings(new ItemManager(IRON_LEGGINGS).build());
                p.getInventory().setHelmet(new ItemManager(IRON_HELMET).build());




                //If Event GoldenApple add Golden Apple
                if (Events.currentEvent == 3) {


                    p.getInventory().setItem(4, (new ItemManager(GOLDEN_APPLE).build()));
                }


            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTank")){    //Get Kit ("§6§lTank")
                if(p.getLevel() >= 5) {
                    e.setCancelled(true);
                    p.sendMessage("§d§l>> §6Selected §b§lTank");
                    p.closeInventory();
                    p.getInventory().clear();
                    p.getInventory().setArmorContents(null);
                    //Play Anvil sound
                    p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);

                    //Setup Kit Tank
                    p.getInventory().setItem(0, (new ItemManager(STONE_SWORD).build()));
                    p.getInventory().setItem(1, new ItemManager(COOKED_BEEF).setAmount(12).build());
                    p.getInventory().setItem(2, new ItemManager(GOLDEN_APPLE).setAmount(3).build());
                    p.getInventory().setBoots(new ItemManager(DIAMOND_BOOTS).build());
                    p.getInventory().setChestplate(new ItemManager(DIAMOND_CHESTPLATE).build());
                    p.getInventory().setLeggings(new ItemManager(IRON_LEGGINGS).build());
                    p.getInventory().setHelmet(new ItemManager(IRON_HELMET).build());



                    //If Event GoldenApple add Golden Apple
                    if (Events.currentEvent == 3) {


                        p.getInventory().setItem(4, (new ItemManager(GOLDEN_APPLE).build()));
                    }

                }else{
                    e.setCancelled(true);
                    p.sendMessage("You need 5 Kills to own this kit");
                }






            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lRod")) {    //Get Kit ("§6§lRod")

                if (p.getLevel() >= 10) {
                    e.setCancelled(true);
                    p.sendMessage("§d§l>> §6Selected §b§lRod");
                    p.closeInventory();
                    p.getInventory().clear();
                    p.getInventory().setArmorContents(null);
                    //Play Anvil sound
                    p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);

                    //Setup Kit Rod
                    p.getInventory().setItem(0, (new ItemManager(IRON_SWORD).build()));
                    p.getInventory().setItem(2, new ItemManager(COOKED_BEEF).setAmount(12).build());
                    p.getInventory().setItem(3, new ItemManager(GOLDEN_APPLE).setAmount(3).build());
                    p.getInventory().setItem(1, (new ItemManager(FISHING_ROD).build()));
                    p.getInventory().setChestplate(new ItemManager(IRON_CHESTPLATE).build());
                    p.getInventory().setHelmet(new ItemManager(IRON_HELMET).build());
                    p.getInventory().setLeggings(new ItemManager(IRON_LEGGINGS).build());
                    p.getInventory().setBoots(new ItemManager(IRON_BOOTS).build());



                    //If Event GoldenApple add Golden Apple
                    if (Events.currentEvent == 3) {


                        p.getInventory().setItem(4, (new ItemManager(GOLDEN_APPLE).build()));
                    }


                }else{
                    e.setCancelled(true);
                    p.sendMessage("You need 10 Kills to own this kit");
                }
            }


            return;
        }
    }



}
