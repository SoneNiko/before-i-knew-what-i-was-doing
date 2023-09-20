package eu.brickpics.dmsys.util;

import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Objects;

public class NameUtil {
    public static String makeColoredName(ProxiedPlayer player) {
        if (player.hasPermission("*")) {
            return ChatColor.DARK_RED + player.getDisplayName();
        } else {
            try {
                return LuckPermsProvider.get().getGroupManager().getGroup(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getCachedData().getMetaData().getPrefix().substring(0, 2) + player.getDisplayName();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


}
