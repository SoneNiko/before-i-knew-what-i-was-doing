package eu.brickpics.dmsys.commands;

import eu.brickpics.dmsys.manager.SpyManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class CommandTest extends Command {
    public CommandTest(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxyServer.getInstance().getLogger().info(SpyManager.getInstance().toString());
        SpyManager.getInstance().spylist.forEach(player -> ProxyServer.getInstance().getPlayer("KeinOptifine").sendMessage(new TextComponent(player.getName())));
    }
}
