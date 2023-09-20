package eu.brickpics.dmsys.commands;

import eu.brickpics.dmsys.manager.SpyManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandSpy extends Command {
    public CommandSpy(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (SpyManager.getInstance().isSpy((ProxiedPlayer) sender)) {
                SpyManager.getInstance().add((ProxiedPlayer) sender);
                player.sendMessage(new TextComponent(ChatColor.RED + "You Entered the Spymode"));
            } else {
                SpyManager.getInstance().remove(player);
                player.sendMessage(new TextComponent(ChatColor.RED + "You Left the Spymode"));
            }

        }
    }
}
