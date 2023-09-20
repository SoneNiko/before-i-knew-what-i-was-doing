package eu.brickpics.gungame.commands;

import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.storage.Data;
import eu.brickpics.gungame.storage.SQLWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CMDStats implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            UUID uuid = p.getUniqueId();
            if (args.length != 0) {
                if (SQLWrapper.playerExists(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString())) {
                    uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
                    p.sendMessage(Data.PREFIX + "§7========================");
                    p.sendMessage(Data.PREFIX + "§7 " + Bukkit.getPlayer(uuid).getDisplayName() + ":");
                    p.sendMessage(Data.PREFIX + "§7 " + MessageManager.getString("stats_kills", p) + ": §e" + SQLWrapper.getKills(uuid.toString()));
                    p.sendMessage(Data.PREFIX + "§7 " + MessageManager.getString("stats_deaths", p) + ": §e" + SQLWrapper.getDeaths(uuid.toString()));
                    p.sendMessage(Data.PREFIX + "§7 " + MessageManager.getString("stats_highscore", p) + ": §e" + SQLWrapper.getHighScore(uuid.toString()));
                    p.sendMessage(Data.PREFIX + "§7========================");
                } else {
                    p.sendMessage(Data.PREFIX + MessageManager.getString("stats_playerdoesnotexist", p).replace("%player%", args[0]));
                }

            } else {
                p.sendMessage(Data.PREFIX + "§7========================");
                p.sendMessage(Data.PREFIX + "§7 " + p.getDisplayName() + ":");
                p.sendMessage(Data.PREFIX + "§7 " + MessageManager.getString("stats_kills", p) + ": §e" + SQLWrapper.getKills(p.getUniqueId().toString()));
                p.sendMessage(Data.PREFIX + "§7 " + MessageManager.getString("stats_deaths", p) + ": §e" + SQLWrapper.getDeaths(p.getUniqueId().toString()));
                p.sendMessage(Data.PREFIX + "§7 " + MessageManager.getString("stats_highscore", p) + ": §e" + SQLWrapper.getHighScore(p.getUniqueId().toString()));
                p.sendMessage(Data.PREFIX + "§7========================");
            }

        } else {
            return false;
        }
        return true;
    }
}