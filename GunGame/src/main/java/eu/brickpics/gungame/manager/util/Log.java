package eu.brickpics.gungame.manager.util;

import org.bukkit.Bukkit;

public class Log {
    public static void info(String msg) {
        Bukkit.getLogger().info(msg);
    }

    public static void warning(String msg) {
        Bukkit.getLogger().warning(msg);
    }

    public static void severe(String msg) {
        Bukkit.getLogger().severe(msg);
    }
}
