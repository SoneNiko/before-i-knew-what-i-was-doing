package eu.brickpics.dmsys.manager;

import eu.brickpics.dmsys.util.NameUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

public class SpyManager {
    private static SpyManager instance = null;
    private SpyManager() {

    }
    public static SpyManager getInstance() {
        if (instance == null) {
            instance = new SpyManager();
        }
        return instance;
    }

    public ArrayList<ProxiedPlayer> spylist = new ArrayList<>();

    public void add(ProxiedPlayer player) {
        spylist.add(player);
    }

    public void remove(ProxiedPlayer playerToRemove) {
        spylist.remove(playerToRemove);
    }

    public boolean isSpy(ProxiedPlayer player) {
        return spylist.contains(player);
    }

    public void notifySpies(ProxiedPlayer sender, ProxiedPlayer target, String msg) {
        String prefix = ChatColor.DARK_GRAY + "\u2503 " + ChatColor.GOLD + "MSG" + ChatColor.DARK_GRAY + " \u2503 ";
        spylist.forEach(player -> player.sendMessage(new TextComponent(prefix + NameUtil.makeColoredName(sender) + ChatColor.GRAY + " \u27A5 " + NameUtil.makeColoredName(target) + ChatColor.GRAY + " \u00BB " + ChatColor.RESET + msg)));
    }
}
