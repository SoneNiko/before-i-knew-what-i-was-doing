package eu.brickpics.gungame.storage;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Data {
    public static final String PREFIX = ChatColor.DARK_GREEN + "•" + ChatColor.GREEN + "● " + ChatColor.DARK_GREEN + "Gun" + ChatColor.GREEN + "Game §7";

    public static final String TITLEPREFIX = ChatColor.DARK_GREEN + "•" + ChatColor.GREEN + "● §r";
    public static final String TITLESUFFIX = ChatColor.GREEN + " ●" + ChatColor.DARK_GREEN + "• §r";

    public static final String TITLEPREFIXRED = ChatColor.DARK_RED + "•" + ChatColor.RED + "● §r";
    public static final String TITLESUFFIXRED = ChatColor.RED + " ●" + ChatColor.DARK_RED + "• §r";

    public static final Location SPAWN = new Location(Bukkit.getWorld("world"), 0.0D, 90.0D, 0.0D);

    public static int spawnMinX = -8;
    public static int spawnMaxX = 9;
    public static int spawnMinZ = -9;
    public static int spawnMaxZ = 8;

    public static int spawnMinY = 88;

    public static double settingsNpcX = -1.5D;
    public static double settingsNpcY = 89.5D;
    public static double settingsNpcZ = 1.5D;
    public static float settingsNpcPitch = -135.0F;
    public static float settingsNpcYaw = 29.0F;

    public static double statsNpcX = 2.5D;
    public static double statsNpcY = 89.5D;
    public static double statsNpcZ = 1.5D;
    public static float statsNpcPitch = 135.0F;
    public static float statsNpcYaw = 29.0F;

}


