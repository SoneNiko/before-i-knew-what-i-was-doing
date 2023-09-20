package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.main.GunGame;
import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.storage.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.ArrayList;

public class SpawnProt implements Listener {

    public static ArrayList<Player> inCombatList = new ArrayList<>();

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Player) {
            Entity p = event.getEntity();
            Entity attacker = event.getDamager();
            int attackerX = (int) Math.round(attacker.getLocation().getX());
            int attackerZ = (int) Math.round(attacker.getLocation().getZ());
            int opferX = (int) Math.round(p.getLocation().getX());
            int opferZ = (int) Math.round(p.getLocation().getZ());
            int attackerY = (int) Math.round(attacker.getLocation().getY());
            int opferY = (int) Math.round(p.getLocation().getY());
            if ((attackerY >= Data.spawnMinY || opferY >= Data.spawnMinY) && ((betweenequals(attackerX, Data.spawnMinX, Data.spawnMaxX) && betweenequals(attackerZ, Data.spawnMinZ, Data.spawnMaxZ)) || (betweenequals(opferX, Data.spawnMinX, Data.spawnMaxX) && betweenequals(opferZ, Data.spawnMinZ, Data.spawnMaxZ)))) {
                event.setCancelled(true);
                attacker.sendMessage(Data.PREFIX + MessageManager.getString("forbidden_spawn", (Player) attacker));
                return;
            }
        } else if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            Arrow a = (Arrow) event.getDamager();
            if(a.getShooter() instanceof Player) {
                Entity p = event.getEntity();
                Player attacker = (Player) a.getShooter();
                int attackerX = (int) Math.round(attacker.getLocation().getX());
                int attackerZ = (int) Math.round(attacker.getLocation().getZ());
                int opferX = (int) Math.round(p.getLocation().getX());
                int opferZ = (int) Math.round(p.getLocation().getZ());
                int attackerY = (int) Math.round(attacker.getLocation().getY());
                int opferY = (int) Math.round(p.getLocation().getY());
                if ((attackerY >= Data.spawnMinY || opferY >= Data.spawnMinY) && ((betweenequals(attackerX, Data.spawnMinX, Data.spawnMaxX) && betweenequals(attackerZ, Data.spawnMinZ, Data.spawnMaxZ)) || (betweenequals(opferX, Data.spawnMinX, Data.spawnMaxX) && betweenequals(opferZ, Data.spawnMinZ, Data.spawnMaxZ)))) {
                    event.setCancelled(true);
                    attacker.sendMessage(Data.PREFIX + MessageManager.getString("forbidden_spawn", attacker));
                    return;
                }
            }
        }
        if (event.getDamager() instanceof Player)
            inCombatList.add((Player) event.getDamager());
        if (event.getEntity() instanceof Player)
            inCombatList.add((Player) event.getEntity());

        Bukkit.getScheduler().scheduleSyncDelayedTask(GunGame.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (event.getDamager() instanceof Player)
                    inCombatList.remove((Player) event.getDamager());
                if (event.getEntity() instanceof Player)
                    inCombatList.remove((Player) event.getEntity());

            }
        }, 20L * 25);


    }

    @EventHandler
    public void onPlayerBowEvent(EntityShootBowEvent event) {
        Entity attacker = event.getEntity();
        int attackerX = (int) Math.round(attacker.getLocation().getX());
        int attackerZ = (int) Math.round(attacker.getLocation().getZ());
        int attackerY = (int) Math.round(attacker.getLocation().getY());
        if (attackerY >= Data.spawnMinY && betweenequals(attackerX, Data.spawnMinX, Data.spawnMaxX) && betweenequals(attackerZ, Data.spawnMinZ, Data.spawnMaxZ)) {
            event.setCancelled(true);
            attacker.sendMessage(Data.PREFIX + MessageManager.getString("forbidden_spawn", (Player) attacker));
        }

    }

    public static boolean betweenequals(int temp, int min, int max) {
        return (temp <= max && temp >= min);
    }
}
