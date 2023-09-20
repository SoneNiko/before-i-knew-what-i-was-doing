package eu.brickpics.gungame.manager;


import eu.brickpics.gungame.main.GunGame;
import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.storage.SQLWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class ScoreboardManager {

    public static HashMap<String, Scoreboard> scoreboards = new HashMap<>();

    private static Scoreboard sb;

    public static void startScoreboard() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GunGame.getInstance(), new Runnable() {
            @Override
            public void run() {
                ScoreboardManager.updateScoreboard();
            }
        }, 20L, 20L);
    }

    public static void updateScoreboard() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOnline()) {
                if (!scoreboards.containsKey(p.getName()))
                    initScoreboard(p);

                Scoreboard sb = scoreboards.get(p.getName());

                sb.getTeam("5").setPrefix(String.valueOf(LevelManager.getCurrentLevel(p))); // Score
                sb.getTeam("2").setPrefix(String.valueOf(SQLWrapper.getHighScore(p.getUniqueId().toString()))); // Highscore
                sb.getTeam("9").setPrefix("Forest");
                sb.getTeam("e").setPrefix("§c" + MessageManager.getString("teaming_forbidden", p));

                continue;
            }
            scoreboards.remove(p.getName());
        }
    }

    private static void initScoreboard(Player p) {
        try {
            sb = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective o = sb.registerNewObjective("Main", "Scoreboard");
            o.setDisplayName("   §2§lGun§a§lGame   ");
            o.setDisplaySlot(DisplaySlot.SIDEBAR);
            registerNewTeam(sb, "7", "§7", "§7", "§7");
            o.getScore("§7").setScore(13);
            registerNewTeam(sb, "6", "§8§l┃ §7" + MessageManager.getString("stats_score", p), "§6", "§6");
            o.getScore("§6").setScore(12);
            registerNewTeam(sb, "5", "§b", "§7", "§5");
            o.getScore("§5").setScore(11);
            registerNewTeam(sb, "4", "§4", "§4", "§4");
            o.getScore("§4").setScore(10);
            registerNewTeam(sb, "3", "§8§l┃ §7" + MessageManager.getString("stats_highscore", p), "§3", "§3");
            o.getScore("§3").setScore(9);
            registerNewTeam(sb, "2", "§b", "§7", "§2");
            o.getScore("§2").setScore(8);
            registerNewTeam(sb, "1", "§1", "§1", "§1");
            o.getScore("§1").setScore(7);
            registerNewTeam(sb, "n", "§8§l┃ §7" + MessageManager.getString("map", p), "§n", "§n");
            o.getScore("§n").setScore(6);
            registerNewTeam(sb, "9", "§b", "§9", "§9");
            o.getScore("§9").setScore(5);
            registerNewTeam(sb, "8", "§8", "§8", "§8");
            o.getScore("§8").setScore(4);
            registerNewTeam(sb, "f", "§8§l┃ §7" + "Teaming", "§f", "§f");
            o.getScore("§f").setScore(3);
            registerNewTeam(sb, "e", "§b", "§9", "§e");
            o.getScore("§e").setScore(2);
            registerNewTeam(sb, "d", "§d", "§d", "§d");
            o.getScore("§d").setScore(1);

            p.setScoreboard(sb);
            scoreboards.put(p.getName(), sb);
        } catch (IllegalArgumentException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private static void registerNewTeam(Scoreboard sb, String teamName, String prefix, String suffix, String toAdd) {
        Team team = sb.registerNewTeam(teamName);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(toAdd);
    }
}
