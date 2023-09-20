package eu.brickpics.skypvpplugin.listener;

import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Material.*;
import static org.bukkit.Material.IRON_HELMET;

public class JoinListener implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent event) {


        Player player = event.getPlayer();
        event.setJoinMessage("§8┃ §2SkyPvP §8× §7" + player.getDisplayName() + " joined");
        player.getInventory().clear();
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        player.setHealthScale(20);
        player.setHealth(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.teleport(player.getWorld().getSpawnLocation());

        player.sendMessage("§d§l>> §6Selected §b§lAssassin");
        player.getInventory().setArmorContents(null);
        //Play Anvil sound
        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);

        //Setup Kit Assassin
        player.getInventory().setItem(0, new ItemManager(DIAMOND_SWORD).build());
        player.getInventory().setItem(1, new ItemManager(COOKED_BEEF).setAmount(12).build());
        player.getInventory().setItem(2, new ItemManager(GOLDEN_APPLE).setAmount(3).build());
        player.getInventory().setBoots(new ItemManager(IRON_BOOTS).build());
        player.getInventory().setChestplate(new ItemManager(IRON_CHESTPLATE).build());
        player.getInventory().setLeggings(new ItemManager(IRON_LEGGINGS).build());
        player.getInventory().setHelmet(new ItemManager(IRON_HELMET).build());



        // TODO: kit menu item geben
    }
}
