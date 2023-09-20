package eu.brickpics.stairseat;

import eu.brickpics.stairseat.listener.DespawnCancel;
import eu.brickpics.stairseat.listener.GetupListener;
import eu.brickpics.stairseat.listener.SitListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class StairSeat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new GetupListener(), this);
        Bukkit.getPluginManager().registerEvents(new SitListener(), this);
        Bukkit.getPluginManager().registerEvents(new DespawnCancel(), this);
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                SeatManager.standPlayerUp(p);
            } catch (NullPointerException ignored) { }
        }

    }
}
