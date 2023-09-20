package eu.brickpics.mentionsys;

import eu.brickpics.mentionsys.listener.ChatListener;
import eu.brickpics.mentionsys.listener.TabListener;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MentionSys extends JavaPlugin {

    public static LuckPerms api;

    @Override
    public void onEnable() {
        api = LuckPermsProvider.get();
        // Plugin startup logic
        Bukkit.getLogger().info("MSG Plugin Loaded");

        //connection = SQL.getInstance().getConnenction();
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new TabListener(), this);

    }
}
