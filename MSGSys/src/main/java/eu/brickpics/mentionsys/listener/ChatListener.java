package eu.brickpics.mentionsys.listener;

import eu.brickpics.appearancemanager.manager.Clan;
import eu.brickpics.mentionsys.util.Formatting;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        // Ist registriert?
        //if (!Registration.isRegistered(e.getPlayer().getUniqueId()) && !e.getPlayer().hasPermission("register.skip")) {
        //    e.getPlayer().sendMessage(ChatColor.RED + "You need to register"); //TODO: formatting
        //    e.setCancelled(true);
        //    return;
        //}

        // Global vars
        Player p = e.getPlayer();
        String string = e.getMessage();

        // Doppeltes @
        int count = 0;
        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == '@')
                count++;
        }

        // @Person
        if (e.getMessage().contains("@")){
            for (String word : e.getMessage().split(" ")) {
                if(word.startsWith("@") && !(word.equals("@t") || word.equals("@team")) && !(word.equals("@a") || word.equals("@all"))) {
                    String name = word.replace("@", "");
                    Player mentionedPlayer = Bukkit.getPlayerExact(name); // TODO: Abfragen ob der Spieler im Staffmode ist oder "nicked" oder "vanished"

                    if (mentionedPlayer != null) {
                        TextComponent tc1 = new TextComponent();
                        tc1.setText(Formatting.makeColoredName(p) + ChatColor.DARK_GRAY + " » " + ChatColor.RESET + e.getMessage().replace(name, ChatColor.YELLOW + name + ChatColor.RESET));
                        tc1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here to Reply").create()));
                        tc1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "@" + p.getName()));

                        e.getRecipients().remove(mentionedPlayer);
                        mentionedPlayer.spigot().sendMessage(tc1);
                        mentionedPlayer.playSound(mentionedPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);
                    }
                    break;
                }

                // @all (only Admins)
                if(p.hasPermission("mention.all")) {
                    if (word.equals("@all")) {

                        e.setMessage(e.getMessage().replace("@all", ChatColor.YELLOW + "@all" + ChatColor.RESET));

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all != p) {
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);
                            }
                        }
                        break;
                    }
                }


                // @team oder @t
                if(word.equalsIgnoreCase("@t") || word.equalsIgnoreCase("@team")){

                    if (Clan.of(p) != null) {
                        Clan.of(e.getPlayer()).members.forEach(offlinePlayer -> {
                            if (offlinePlayer.isOnline()) {
                                Player mentionedTeammate = (Player) offlinePlayer;

                                for(Player all : Bukkit.getOnlinePlayers()){
                                    e.getRecipients().remove(all);

                                }
                                if (mentionedTeammate.getUniqueId() != p.getUniqueId()){

                                    TextComponent tc2 = new TextComponent();
                                    tc2.setText(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Reply" + ChatColor.DARK_GRAY + "]");
                                    tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "Click here to Reply to the TeamChat").create()));
                                    tc2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "@t"));

                                    String msg = e.getMessage()
                                            .replace("@t", ChatColor.YELLOW + "@t" + ChatColor.RESET)
                                            .replace("@team", ChatColor.YELLOW + "@team" + ChatColor.RESET);
                                    mentionedTeammate.sendMessage(Formatting.makeClanTag(p) + " §7TeamChat " + ChatColor.DARK_GRAY + " - §r" + Formatting.makeColoredName(p) + ChatColor.DARK_GRAY + " » " + msg.trim());
                                    mentionedTeammate.spigot().sendMessage(tc2);
                                    mentionedTeammate.playSound(mentionedTeammate.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);
                                }
                            }
                        });
                    } else {
                        p.sendMessage(ChatColor.RED + "You are not in a Team");
                    }

                }
            }
        }
    }
}
