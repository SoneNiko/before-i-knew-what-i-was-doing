package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.main.GunGame;
import eu.brickpics.gungame.manager.LevelManager;
import eu.brickpics.gungame.manager.NPCManager;
import eu.brickpics.gungame.manager.multilang.AchievementManager;
import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.manager.util.Log;
import eu.brickpics.gungame.storage.Data;
import eu.brickpics.gungame.storage.SQLWrapper;
import net.geknxddelt.informationsystem.api.TitlesAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player p = event.getEntity();
        final Player pkiller = event.getEntity().getKiller();
        String playeruuid = p.getUniqueId().toString();
        
        // Stats

        SQLWrapper.addDeaths(playeruuid, 1);
        
        if (pkiller != null) {
            String killeruuid = pkiller.getUniqueId().toString();
            if (LevelManager.getCurrentLevel(pkiller) > SQLWrapper.getHighScore(pkiller.getUniqueId().toString()))
                SQLWrapper.setHighScore(pkiller.getUniqueId().toString(), LevelManager.getCurrentLevel(pkiller));
            if (!killeruuid.equals("b653bfa4-3d01-4b99-8a85-e6c3a4260485")) {
                SQLWrapper.addKills(killeruuid, Integer.valueOf(1));
                Log.info("Pssst Phillip!!!");
            }
        }

        //Achievements

        if (SQLWrapper.getDeaths(playeruuid) == 10)
            if (!AchievementManager.hasAchievement(15, playeruuid))
                AchievementManager.setAchievement(15, true, playeruuid);
        if (SQLWrapper.getDeaths(playeruuid) == 100)
            if (!AchievementManager.hasAchievement(16, playeruuid))
                AchievementManager.setAchievement(16, true, playeruuid);
        if (SQLWrapper.getDeaths(playeruuid) == 1000)
            if (!AchievementManager.hasAchievement(17, playeruuid))
                AchievementManager.setAchievement(17, true, playeruuid);

        if (!(pkiller == null)) {
            if (SQLWrapper.getKills(pkiller.getUniqueId().toString()) == 10)
                if (!AchievementManager.hasAchievement(15, pkiller.getUniqueId().toString()))
                    AchievementManager.setAchievement(15, true, pkiller.getUniqueId().toString());
            if (SQLWrapper.getDeaths(pkiller.getUniqueId().toString()) == 100)
                if (!AchievementManager.hasAchievement(16, pkiller.getUniqueId().toString()))
                    AchievementManager.setAchievement(16, true, pkiller.getUniqueId().toString());
            if (SQLWrapper.getDeaths(pkiller.getUniqueId().toString()) == 1000)
                if (!AchievementManager.hasAchievement(17, pkiller.getUniqueId().toString()))
                    AchievementManager.setAchievement(17, true, pkiller.getUniqueId().toString());
        }



        if (!AchievementManager.hasAchievement(15, p.getUniqueId().toString()))
            AchievementManager.setAchievement(15, true, p.getUniqueId().toString());

        // Titles

        event.setDeathMessage("");

        if (pkiller == null) {
            for (Player player : Bukkit.getOnlinePlayers())
                player.sendMessage(Data.PREFIX + MessageManager.getString("death_regular", player).replace("%player%", p.getDisplayName()));
        } else {
            for (Player player : Bukkit.getOnlinePlayers())
                player.sendMessage(Data.PREFIX + MessageManager.getString("death_killed", player).replace("%player%", p.getDisplayName()).replace("%killer", pkiller.getDisplayName()));
            if (SQLWrapper.isTitles(pkiller.getUniqueId().toString()))
                TitlesAPI.sendTitle(pkiller, 0, 2 * 20, 5, Data.TITLEPREFIX + ChatColor.GREEN + MessageManager.getString("titles_kill", pkiller) + Data.TITLESUFFIX, ChatColor.GREEN + p.getDisplayName());
        }


        // Heal, Levelup and Sound
        if (pkiller != null) {
            LevelManager.levelUpByOne(p.getKiller());
            if (!(LevelManager.getCurrentLevel(p) > 59))
                pkiller.setHealth(20.0D);
            if (SQLWrapper.isSounds(p.getKiller().getUniqueId().toString()))
                pkiller.playSound(pkiller.getLocation(), Sound.LEVEL_UP, 1F, 1.0F);
        }
        int levelToValue = 0;
        if (LevelManager.getCurrentLevel(p) != 1)
            levelToValue = Math.round(LevelManager.getCurrentLevel(p) - (LevelManager.getCurrentLevel(p) / 3.0F));
        LevelManager.setCurrentLevel(p, levelToValue);
        event.setKeepLevel(true);


        event.getDrops().clear();
        p.teleport(Data.SPAWN);
        Bukkit.getScheduler().scheduleSyncDelayedTask(GunGame.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        },5L);
    }

    //@EventHandler
    //public void onMove(PlayerMoveEvent event) {
    //    if (event.getPlayer().getLocation().getBlock().isLiquid()) {
    //        event.getPlayer().damage(10000.0D);
    //    }
    //}



    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (SQLWrapper.isTitles(p.getUniqueId().toString())) {
            if (event.getPlayer().getKiller() != null)
                TitlesAPI.sendTitle(p,0, 2 * 20, 5, Data.TITLEPREFIXRED + "§c" + MessageManager.getString("titles_dead", p) + Data.TITLESUFFIXRED, ChatColor.RED + event.getPlayer().getKiller().getDisplayName());
            else
                TitlesAPI.sendTitle(p,0, 2 * 20, 5, Data.TITLEPREFIXRED + "§c" + MessageManager.getString("titles_dead", p) + Data.TITLESUFFIXRED, ChatColor.RED + MessageManager.getString("subtitle_regular_death", p));
        }

        if (SQLWrapper.isSounds(p.getUniqueId().toString()))
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1F, 1F);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(GunGame.getInstance(), new Runnable() {
            @Override
            public void run() {
                NPCManager.display(p);
            }
        }, 0L, 20L);

        LevelManager.giveEquip(event.getPlayer());
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
    }
}
