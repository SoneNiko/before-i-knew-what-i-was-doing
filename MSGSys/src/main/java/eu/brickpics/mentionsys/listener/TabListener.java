package eu.brickpics.mentionsys.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

public class TabListener implements Listener {
    @EventHandler
    public void onTab(TabCompleteEvent event) {
        //event.getCompletions()
        //event.setCompletions(); List<PlayerName: String>
        Bukkit.getLogger().info(event.getBuffer()); // In der Console wird angezeigt was der Buffer ist weil ich das wissen will!
        event.getCompletions().forEach(s -> Bukkit.getLogger().info(s)); // Same
    }
}
