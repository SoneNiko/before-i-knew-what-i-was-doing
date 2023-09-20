package eu.brickpics.skypvpplugin.tasks;

import eu.brickpics.skypvpplugin.listener.AdminListener;
import eu.brickpics.skypvpplugin.main.SkyPvPPlugin;
import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Material.GOLDEN_APPLE;

public class Events extends BukkitRunnable{


    SkyPvPPlugin plugin;


    public Events(SkyPvPPlugin plugin) {
        this.plugin = plugin;
    }

    public static int currentEvent = 0;
    @Override
    public void run() {





        if(AdminListener.nextEvent == 0) { //if no admin forces event
            //random Number between 1 and 3
            final int ran = (int) Math.floor(Math.random() * 3 + 1);



            for (final Player p : Bukkit.getOnlinePlayers()) {

                if (ran == 0) {

                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }

                } else if (ran == 1) {

                    //First Event
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.addPotionEffect((new PotionEffect(PotionEffectType.SPEED, 99999, 1)));
                    p.sendMessage("§d§l>> §6New Event: §b§lSPEED");
                    Events.currentEvent = 1;
                    System.out.println("§d§l>> §6New Event: §b§lSPEED");

                    //End of first Event
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            Events.currentEvent = 0;
                            for (PotionEffect effect : p.getActivePotionEffects()) {
                                p.removePotionEffect(effect.getType());
                            }
                            p.sendMessage("§d§l>> §6End of Event: §b§lSPEED");
                        }

                    }, (60 * 20L));


                } else if (ran == 2) {

                    //Second Event
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.addPotionEffect((new PotionEffect(PotionEffectType.JUMP, 99999, 3)));
                    p.sendMessage("§d§l>> §6New Event: §b§lBUNNY");
                    Events.currentEvent = 2;
                    System.out.println("§d§l>> §6New Event: §b§lBUNNY");

                    //End of second Event
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            Events.currentEvent = 0;
                            for (PotionEffect effect : p.getActivePotionEffects()) {
                                p.removePotionEffect(effect.getType());
                            }
                            p.sendMessage("§d§l>> §6End of Event: §b§lBUNNY");
                        }

                    }, (60 * 20L));


                } else if (ran == 3) {

                    //Third Event
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.getInventory().setItem(4, (new ItemManager(GOLDEN_APPLE).build()));
                    p.sendMessage("§d§l>> §6New Event: §b§lGOLDEN APPLE");
                    Events.currentEvent = 3;
                    System.out.println("§d§l>> §6New Event: §b§lGOLDEN APPLE");

                    //End of third Event
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            Events.currentEvent = 0;
                            p.sendMessage("§d§l>> §6End of Event: §b§lGOLDEN APPLE");
                        }

                    }, (60 * 20L));


                }
            }






        }else { //If admin forces next event

            for (final Player p : Bukkit.getOnlinePlayers()) {

                if (AdminListener.nextEvent == 1) {

                    //First Event
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.addPotionEffect((new PotionEffect(PotionEffectType.SPEED, 99999, 1)));
                    p.sendMessage("§d§l>> §6New Event: §b§lSPEED");
                    Events.currentEvent = 1;
                    System.out.println("§d§l>> §6New Event: §b§lSPEED");

                    //End of first Event
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            Events.currentEvent = 0;
                            AdminListener.nextEvent = 0;
                            for (PotionEffect effect : p.getActivePotionEffects()) {
                                p.removePotionEffect(effect.getType());
                            }
                            p.sendMessage("§d§l>> §6End of Event: §b§lSPEED");
                        }

                    }, (60 * 20L));


                } else if (AdminListener.nextEvent == 2) {

                    //Second Event
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.addPotionEffect((new PotionEffect(PotionEffectType.JUMP, 99999, 3)));
                    p.sendMessage("§d§l>> §6New Event: §b§lBUNNY");
                    Events.currentEvent = 2;
                    System.out.println("§d§l>> §6New Event: §b§lBUNNY");

                    //End of second Event
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            Events.currentEvent = 0;
                            AdminListener.nextEvent = 0;
                            for (PotionEffect effect : p.getActivePotionEffects()) {
                                p.removePotionEffect(effect.getType());
                            }
                            p.sendMessage("§d§l>> §6End of Event: §b§lBUNNY");
                        }

                    }, (60 * 20L));


                } else if (AdminListener.nextEvent == 3) {

                    //Third Event
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.getInventory().setItem(4, (new ItemManager(GOLDEN_APPLE).build()));
                    p.sendMessage("§d§l>> §6New Event: §b§lGOLDEN APPLE");
                    Events.currentEvent = 3;
                    System.out.println("§d§l>> §6New Event: §b§lGOLDEN APPLE");

                    //End of third Event
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            Events.currentEvent = 0;
                            AdminListener.nextEvent = 0;
                            p.sendMessage("§d§l>> §6End of Event: §b§lGOLDEN APPLE");
                        }

                    }, (60 * 20L));


                }


            }

        }




    }

}

