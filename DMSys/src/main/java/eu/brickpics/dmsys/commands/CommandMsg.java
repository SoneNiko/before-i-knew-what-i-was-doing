package eu.brickpics.dmsys.commands;

import de.exceptionflug.protocolize.world.Sound;
import de.exceptionflug.protocolize.world.SoundCategory;
import de.exceptionflug.protocolize.world.WorldModule;
import eu.brickpics.dmsys.manager.SpyManager;
import eu.brickpics.dmsys.util.NameUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandMsg extends Command {
    public CommandMsg(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer && args.length >= 2) {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            ProxiedPlayer player = (ProxiedPlayer) sender;

            StringBuilder msgBuilder = new StringBuilder("");
            for (int i = 1; i < args.length; i++) {
                msgBuilder.append(args[i]);
            }
            String msg = msgBuilder.toString();

            String prefix = ChatColor.DARK_GRAY + "\u2503 " + ChatColor.GOLD + "MSG" + ChatColor.DARK_GRAY + " \u2503 ";

            String msgPlayer = prefix + ChatColor.AQUA + "You" + ChatColor.DARK_GRAY + " \u27A5 " + NameUtil.makeColoredName(target) + ChatColor.DARK_GRAY + " \u00BB " + ChatColor.RESET + msg;
            player.sendMessage(new TextComponent(msgPlayer));


            String msgTarget = prefix + NameUtil.makeColoredName(player) + ChatColor.GRAY + " \u27A5 " + ChatColor.AQUA + "You" + ChatColor.GRAY + " \u00BB " + ChatColor.RESET + msg;
            target.sendMessage(new TextComponent(msgTarget));

            SpyManager.getInstance().notifySpies(player, target, msg);

            WorldModule.playSound(target, Sound.BLOCK_NOTE_BLOCK_HARP, SoundCategory.MUSIC, 1F, 1F);
            WorldModule.playSound(player, Sound.ENTITY_ITEM_PICKUP, SoundCategory.MUSIC, 1F, 1F);
        }






    }
}
