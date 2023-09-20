package eu.brickpics.mentionsys.util;

import eu.brickpics.appearancemanager.manager.Clan;
import eu.brickpics.mentionsys.MentionSys;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Formatting {
    public static String makeClanTag(Player player) {
        return ChatColor.DARK_GRAY + "[" + Clan.of(player).getColorTag() + Clan.of(player).getTag() + ChatColor.DARK_GRAY + "]§r";
    }

    public static String makeColoredName(Player player) {
        return MentionSys.api.getGroupManager()
                .getGroup(MentionSys.api.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup())
                .getCachedData().getMetaData().getPrefix()
                .substring(0, 2)
                + player.getName(); // TODO: NickPlugin
    }
}
