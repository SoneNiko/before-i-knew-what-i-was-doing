package eu.brickpics.gungame.manager;

import eu.brickpics.gungame.main.GunGame;
import eu.brickpics.gungame.manager.util.vendor.ItemManager;
import eu.brickpics.gungame.storage.Data;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.state.NPCSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;


public class NPCManager implements Listener {

    public static NPC npcstats;
    public static NPC npcsettings;
    private static GunGame main;
    private static NPCLib npcLib;


    public NPCManager(GunGame main) {
        NPCManager.main = main;
        npcLib = new NPCLib(main);
        load();
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    public static void startRefresher() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GunGame.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    display(p);
                }
            }
        }, 0L, 10L);
    }

    public static void load() {
        MineSkinFetcher.fetchSkinFromIdAsync(36804421, skin -> {
            Location npcstatsloc = new Location(Bukkit.getServer().getWorld("world"), Data.statsNpcX, Data.statsNpcY, Data.statsNpcZ, Data.statsNpcPitch, Data.statsNpcYaw);
            npcstats = npcLib.createNPC(Arrays.asList("", Data.PREFIX + ChatColor.GREEN + "Stats"));
            npcstats.setSkin(skin);
            npcstats.setItem(NPCSlot.MAINHAND, (new ItemManager(Material.WOOD_AXE)).build());
            npcstats.setLocation(npcstatsloc);
            npcstats.create();
        });

        MineSkinFetcher.fetchSkinFromIdAsync(1422379858, skin -> {
            Location npcettingsloc = new Location(Bukkit.getServer().getWorld("world"), Data.settingsNpcX, Data.settingsNpcY, Data.settingsNpcZ, Data.settingsNpcPitch, Data.settingsNpcYaw);
            npcsettings = npcLib.createNPC(Arrays.asList("", Data.PREFIX + ChatColor.GREEN + "Settings"));
            npcsettings.setSkin(skin);
            npcstats.setItem(NPCSlot.MAINHAND, (new ItemManager(Material.REDSTONE)).build());
            npcsettings.setLocation(npcettingsloc);
            npcsettings.create();

        });
        startRefresher();
    }

    @EventHandler
    public static void onNPCInteract(NPCInteractEvent event) {
        String settingsId = npcsettings.getId();
        String statsId = npcstats.getId();

        if (event.getNPC().getId().equals(settingsId)) {
            MenuBuilder.openMenu(MenuBuilder.MenuType.SETTINGS, event.getWhoClicked());
        } else if (event.getNPC().getId().equals(statsId)) {
            MenuBuilder.openMenu(MenuBuilder.MenuType.STATS, event.getWhoClicked());
        }
    }

    public static void display(Player p) {
        if (!npcstats.isShown(p))
            npcstats.show(p);
        if (!npcsettings.isShown(p))
            npcsettings.show(p);


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        display(p);
    }
}
