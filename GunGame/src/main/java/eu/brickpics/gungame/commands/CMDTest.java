package eu.brickpics.gungame.commands;

import eu.brickpics.gungame.manager.MenuBuilder;
import eu.brickpics.gungame.storage.SQLWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMDTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (int i = 1; i < 6; i++)
            Bukkit.broadcastMessage(SQLWrapper.gungame_rang.get(i));

        Bukkit.broadcastMessage(MenuBuilder.p1);
        Bukkit.broadcastMessage(MenuBuilder.p2);
        Bukkit.broadcastMessage(MenuBuilder.p3);
        Bukkit.broadcastMessage(MenuBuilder.p4);
        Bukkit.broadcastMessage(MenuBuilder.p5);
        return true;
    }
}
