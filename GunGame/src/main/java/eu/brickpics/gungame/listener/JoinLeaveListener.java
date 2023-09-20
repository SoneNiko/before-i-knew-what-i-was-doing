package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.manager.LevelManager;
import eu.brickpics.gungame.manager.ScoreboardManager;
import eu.brickpics.gungame.manager.multilang.AchievementManager;
import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.storage.Data;
import eu.brickpics.gungame.storage.SQLWrapper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static eu.brickpics.gungame.listener.SpawnProt.inCombatList;

public class JoinLeaveListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();

        event.setJoinMessage("");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Data.PREFIX + MessageManager.getString("join", player).replace("%player%", p.getDisplayName()).replace("%playercount%", "(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")"));
        }

        p.setExp(0.0F);
        p.setLevel(0);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        LevelManager.giveEquip(p);

        p.setGameMode(GameMode.SURVIVAL);
        p.teleport(Data.SPAWN);

        if (!SQLWrapper.playerExists(uuid)) {
            SQLWrapper.createPlayer(uuid);
        }

        if (SQLWrapper.isSounds(p.getUniqueId().toString()))
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1F, 1F);



    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        ScoreboardManager.scoreboards.remove(p.getName());

        if (inCombatList.contains(p))
            AchievementManager.setAchievement(2, true, p.getUniqueId().toString());

        event.setQuitMessage("");
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(Data.PREFIX + MessageManager.getString("leave", player).replace("%player%", p.getDisplayName()).replace("%playercount%", "(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")"));
    }
}
