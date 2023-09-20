package eu.brickpics.search;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Search extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginCommand("search-user").setExecutor(new CMDSearch());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
