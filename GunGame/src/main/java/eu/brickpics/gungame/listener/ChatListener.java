package eu.brickpics.gungame.listener;

import eu.brickpics.gungame.manager.multilang.AchievementManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("gg"))
            if (!AchievementManager.hasAchievement(1, event.getPlayer().getUniqueId().toString()))
                AchievementManager.setAchievement(1, true, event.getPlayer().getUniqueId().toString());
    }
}
