package eu.brickpics.gungame.manager;

import eu.brickpics.gungame.manager.multilang.AchievementManager;
import eu.brickpics.gungame.manager.multilang.MessageManager;
import eu.brickpics.gungame.manager.util.vendor.ItemManager;
import eu.brickpics.gungame.manager.util.vendor.NameManager;
import eu.brickpics.gungame.storage.SQLWrapper;
import net.geknxddelt.informationsystem.api.ColorAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;
import org.ipvp.canvas.slot.ClickOptions;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

import java.util.UUID;


public class MenuBuilder {
    public enum MenuType {
        SETTINGS,
        STATS,
        ACHIEVEMENTS,
    }

    public static String p1 = null;
    public static String p2 = null;
    public static String p3 = null;
    public static String p4 = null;
    public static String p5 = null;

    public static void openMenu(MenuType type, Player p) {
        try {
            SQLWrapper.setGunGameRanking();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Menu m = createMenu(type, p);
        m.open(p);
    }

    public static void closeMenu(Player p) {
        p.closeInventory();
    }

    private static Menu createMenu(MenuType type, Player p) {
        Menu m;

        ItemManager fallbackColorRed = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 1);
        ItemManager fallbackColorGreen = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 13);


        switch (type) {

            case STATS:
                m = ChestMenu.builder(4).title(MessageManager.getString("stats_name", p)).redraw(true).build();
                addBorder(m, type, p);
                addBottomBar(m, type, 27, p);
                String[] statHeadLore = {
                        "§r§2" + MessageManager.getString("stats_kills", p) + ": §6" + SQLWrapper.getKills(p.getUniqueId().toString()).toString(),
                        "§r§2" + MessageManager.getString("stats_deaths", p) + ": §6" + SQLWrapper.getDeaths(p.getUniqueId().toString()).toString(),
                        "§r§2" + MessageManager.getString("stats_highscore", p) + ": §6" + SQLWrapper.getHighScore(p.getUniqueId().toString()).toString()
                };
                m.getSlot(10).setItem(new ItemManager(Material.SKULL_ITEM).setSkullOwner(p.getName()).setData((short) 3).setGlow().addLoreArray(statHeadLore).setDisplayName("§r" + p.getDisplayName()).build());


                try {
                    if(SQLWrapper.gungame_rang.get(1) != null) {
                        p1 = NameManager.getName(SQLWrapper.gungame_rang.get(1));
                        m.getSlot(12).setItem(new ItemManager(Material.SKULL_ITEM).setDisplayName("§r" + p1).setData((short) SkullType.PLAYER.ordinal()).setSkullOwner(p1).addLoreLine("§r§2" + MessageManager.getString("stats_kills",p) + ": §6" + SQLWrapper.getKills(SQLWrapper.gungame_rang.get(1))).build());
                    }
                    if(SQLWrapper.gungame_rang.get(2) != null) {
                        p2 = NameManager.getName(SQLWrapper.gungame_rang.get(2));
                        m.getSlot(13).setItem(new ItemManager(Material.SKULL_ITEM).setDisplayName("§r" + p2).setData((short) SkullType.PLAYER.ordinal()).setSkullOwner(p2).addLoreLine("§r§2" + MessageManager.getString("stats_kills",p) + ": §6" + SQLWrapper.getKills(SQLWrapper.gungame_rang.get(2))).build());
                    }
                    if(SQLWrapper.gungame_rang.get(3) != null) {
                        p3 = NameManager.getName(SQLWrapper.gungame_rang.get(3));
                        m.getSlot(14).setItem(new ItemManager(Material.SKULL_ITEM).setDisplayName("§r" + p3).setData((short) SkullType.PLAYER.ordinal()).setSkullOwner(p3).addLoreLine("§r§2" + MessageManager.getString("stats_kills",p) + ": §6" + SQLWrapper.getKills(SQLWrapper.gungame_rang.get(3))).build());
                    }
                    if(SQLWrapper.gungame_rang.get(4) != null) {
                        p4 = NameManager.getName(SQLWrapper.gungame_rang.get(4));
                        m.getSlot(15).setItem(new ItemManager(Material.SKULL_ITEM).setDisplayName("§r" + p4).setData((short) SkullType.PLAYER.ordinal()).setSkullOwner(p4).addLoreLine("§r§2" + MessageManager.getString("stats_kills",p) + ": §6" + SQLWrapper.getKills(SQLWrapper.gungame_rang.get(4))).build());
                    }
                    if(SQLWrapper.gungame_rang.get(5) != null) {
                        p5 = NameManager.getName(SQLWrapper.gungame_rang.get(5));
                        m.getSlot(16).setItem(new ItemManager(Material.SKULL_ITEM).setDisplayName("§r" + p5).setData((short) SkullType.PLAYER.ordinal()).setSkullOwner(p5).addLoreLine("§r§2" + MessageManager.getString("stats_kills",p) + ": §6" + SQLWrapper.getKills(SQLWrapper.gungame_rang.get(5))).build());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                break;




            case ACHIEVEMENTS:
                m = ChestMenu.builder(6).title(MessageManager.getString("achievements_name", p)).redraw(true).build();
                addBorder(m, type, p);
                addBottomBar(m, type, 45, p);

                for (int i = 0; i < 7; i++) {
                    m.getSlot(i + 10).setItem(new ItemManager(Material.WOOL).setData((short) 14).setDisplayName("§r" + AchievementManager.getAchievementName(i, p)).addLoreLine(ChatColor.MAGIC + "aaaaaaaaaaaaa").build());
                }

                for (int i = 0; i < 7; i++) {
                    m.getSlot(i + 19).setItem(new ItemManager(Material.WOOL).setData((short) 14).setDisplayName("§r" + AchievementManager.getAchievementName(i + 7, p)).addLoreLine(ChatColor.MAGIC + "aaaaaaaaaaaaa").build());
                }

                for (int i = 0; i < 7; i++) {
                    m.getSlot(i + 28).setItem(new ItemManager(Material.WOOL).setData((short) 14).setDisplayName("§r" + AchievementManager.getAchievementName(i + 14, p)).addLoreLine(ChatColor.MAGIC + "aaaaaaaaaaaaa").build());
                }


                for (int i = 0; i < 7; i++) {
                    if (AchievementManager.hasAchievement(i, p.getUniqueId().toString()))
                        m.getSlot(i + 10).setItem(new ItemManager(Material.WOOL).setData((short) 5).setDisplayName("§r" + AchievementManager.getAchievementName(i, p)).addLoreLine("§r§7" + i + AchievementManager.getAchievementDescription(i, p)).build());
                }

                for (int i = 0; i < 7; i++) {
                    if (AchievementManager.hasAchievement(i + 7, p.getUniqueId().toString()))
                        m.getSlot(i + 19).setItem(new ItemManager(Material.WOOL).setData((short) 5).setDisplayName("§r" + AchievementManager.getAchievementName(i + 7, p)).addLoreLine("§r§7" + AchievementManager.getAchievementDescription(i + 7, p)).build());
                }

                for (int i = 0; i < 7; i++) {
                    if (AchievementManager.hasAchievement(i + 14, p.getUniqueId().toString()))
                        m.getSlot(i + 28).setItem(new ItemManager(Material.WOOL).setData((short) 5).setDisplayName("§r" + AchievementManager.getAchievementName(i + 14, p)).addLoreLine("§r§7" + AchievementManager.getAchievementDescription(i + 14, p)).build());
                }

                break;




            case SETTINGS:
                m = ChestMenu.builder(6).title(MessageManager.getString("settings_name", p)).redraw(true).build();
                addBorder(m, type, p);
                addBottomBar(m, type, 45, p);

                if (SQLWrapper.isTitles(p.getUniqueId().toString())) {
                    m.getSlot(20).setItem(new ItemManager(Material.NAME_TAG).setDisplayName("§r§a" + MessageManager.getString("settings_titles", p)).setGlow().build());

                    if (ColorAPI.getColorPlayer(p) != 5) {
                        m.getSlot(11).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 5).setDisplayName("§r§a" + MessageManager.getString("settings_titles_on", p)).build());
                        m.getSlot(29).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 5).setDisplayName("§r§a" + MessageManager.getString("settings_titles_on", p)).build());
                    } else {
                        m.getSlot(11).setItem(fallbackColorGreen.setDisplayName("§r§a" + MessageManager.getString("settings_titles_on", p)).build());
                        m.getSlot(29).setItem(fallbackColorGreen.setDisplayName("§r§a" + MessageManager.getString("settings_titles_on", p)).build());
                    }


                } else {
                    m.getSlot(20).setItem(new ItemManager(Material.NAME_TAG).setDisplayName("§r§c" + MessageManager.getString("settings_titles", p)).build());

                    if (ColorAPI.getColorPlayer(p) != 14) {
                        m.getSlot(11).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§r§c" + MessageManager.getString("settings_titles_off", p)).build());
                        m.getSlot(29).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§r§c" + MessageManager.getString("settings_titles_off", p)).build());
                    } else {
                        m.getSlot(11).setItem(fallbackColorRed.setDisplayName("§r§c" + MessageManager.getString("settings_titles_off", p)).build());
                        m.getSlot(29).setItem(fallbackColorRed.setDisplayName("§r§c" + MessageManager.getString("settings_titles_off", p)).build());
                    }

                }

                m.getSlot(20).setClickHandler(((player, clickInformation) -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 1F, 1);
                    SQLWrapper.setTitles(player.getUniqueId().toString(), !SQLWrapper.isTitles(p.getUniqueId().toString()));
                    MenuBuilder.openMenu(MenuType.SETTINGS, player);
                }));




                if (SQLWrapper.isSounds(p.getUniqueId().toString())) {
                    m.getSlot(22).setItem(new ItemManager(Material.NOTE_BLOCK).setGlow().setDisplayName("§r§a" + MessageManager.getString("settings_sounds", p)).build());


                    if (ColorAPI.getColorPlayer(p) != 5) {
                        m.getSlot(13).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 5).setDisplayName("§r§a" + MessageManager.getString("settings_sounds_on", p)).build());
                        m.getSlot(31).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 5).setDisplayName("§r§a" + MessageManager.getString("settings_sounds_on", p)).build());
                    } else {
                        m.getSlot(13).setItem(fallbackColorGreen.setDisplayName("§r§a" + MessageManager.getString("settings_sounds_on", p)).build());
                        m.getSlot(31).setItem(fallbackColorGreen.setDisplayName("§r§a" + MessageManager.getString("settings_sounds_on", p)).build());
                    }

                } else {
                    m.getSlot(22).setItem(new ItemManager(Material.NOTE_BLOCK).setDisplayName("§r§c" + MessageManager.getString("settings_sounds", p)).build());


                    if (ColorAPI.getColorPlayer(p) != 14) {
                        m.getSlot(13).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§r§c" + MessageManager.getString("settings_sounds_off", p)).build());
                        m.getSlot(31).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§r§c" + MessageManager.getString("settings_sounds_off", p)).build());
                    } else {
                        m.getSlot(13).setItem(fallbackColorRed.setDisplayName("§r§c" + MessageManager.getString("settings_sounds_off", p)).build());
                        m.getSlot(31).setItem(fallbackColorRed.setDisplayName("§r§c" + MessageManager.getString("settings_sounds_off", p)).build());
                    }

                }

                m.getSlot(22).setClickHandler(((player, clickInformation) -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 1F, 1);
                    SQLWrapper.setSounds(player.getUniqueId().toString(), !SQLWrapper.isSounds(player.getUniqueId().toString()));
                    MenuBuilder.openMenu(MenuType.SETTINGS, player);
                }));




                if (SQLWrapper.getACNotify(p.getUniqueId().toString()) == 1) {
                    m.getSlot(24).setItem(new ItemManager(Material.BOOK).setGlow().setDisplayName("§r§a" + MessageManager.getString("settings_achievements", p)).build());


                    if (ColorAPI.getColorPlayer(p) != 5) {
                        m.getSlot(15).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 5).setDisplayName("§r§a" + MessageManager.getString("settings_acnotify_on", p)).build());
                        m.getSlot(33).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 5).setDisplayName("§r§a" + MessageManager.getString("settings_acnotify_on", p)).build());
                    } else {
                        m.getSlot(15).setItem(fallbackColorGreen.setDisplayName("§r§a" + MessageManager.getString("settings_acnotify_on", p)).build());
                        m.getSlot(33).setItem(fallbackColorGreen.setDisplayName("§r§a" + MessageManager.getString("settings_acnotify_on", p)).build());
                    }

                } else {
                    m.getSlot(24).setItem(new ItemManager(Material.BOOK).setDisplayName("§r§c" + MessageManager.getString("settings_achievements", p)).build());


                    if (ColorAPI.getColorPlayer(p) != 14) {
                        m.getSlot(15).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§r§c" + MessageManager.getString("settings_acnotify_off", p)).build());
                        m.getSlot(33).setItem(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§r§c" + MessageManager.getString("settings_acnotify_off", p)).build());
                    } else {
                        m.getSlot(15).setItem(fallbackColorRed.setDisplayName("§r§c" + MessageManager.getString("settings_acnotify_off", p)).build());
                        m.getSlot(33).setItem(fallbackColorRed.setDisplayName("§r§c" + MessageManager.getString("settings_acnotify_off", p)).build());
                    }

                }

                m.getSlot(24).setClickHandler(((player, clickInformation) -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 1F, 1);
                    if (SQLWrapper.getACNotify(player.getUniqueId().toString()) == 1)
                        SQLWrapper.setACNotify(player.getUniqueId().toString(), 0);
                    else
                        SQLWrapper.setACNotify(player.getUniqueId().toString(), 1);
                    MenuBuilder.openMenu(MenuType.SETTINGS, player);
                }));








                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        m.setCloseHandler(new Menu.CloseHandler() {
            @Override
            public void close(Player player, Menu menu) {
                LevelManager.giveEquip(player);
            }
        });
        return m;
    }


    public static void addClickOptions(Slot slot) {
        ClickOptions options = ClickOptions.builder()
                .allow(ClickType.LEFT, ClickType.RIGHT)
                .allow(InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE, InventoryAction.PLACE_SOME)
                .build();
        slot.setClickOptions(options);
    }






    public static void addBorder(Menu menu, MenuType t, Player p) {
        Mask mask;
        switch (t) {
            case STATS:
                mask = BinaryMask.builder(menu)
                        .item(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) ColorAPI.getColorPlayer(p)).setNoName().setAmount(1).build())
                        .pattern("111111111")
                        .pattern("101000001")
                        .pattern("111111111")
                        .pattern("101010101")
                        .build();
                break;
            case SETTINGS:
                mask = BinaryMask.builder(menu)
                        .item(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) ColorAPI.getColorPlayer(p)).setNoName().setAmount(1).build())
                        .pattern("111111111")
                        .pattern("110101011")
                        .pattern("110101011")
                        .pattern("110101011")
                        .pattern("111111111")
                        .pattern("101010101")
                        .build();
                break;
            case ACHIEVEMENTS:
                mask = BinaryMask.builder(menu)
                        .item(new ItemManager(Material.STAINED_GLASS_PANE).setData((short) ColorAPI.getColorPlayer(p)).setNoName().setAmount(1).build())
                        .pattern("111111111")
                        .pattern("111111111")
                        .pattern("111101111")
                        .pattern("111111111")
                        .pattern("111111111")
                        .pattern("101010101")
                        .build();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }


        mask.apply(menu);
    }

    public static void addBottomBar(Menu menu, MenuType t, int rowindex, Player p) {
        Slot statsSlot = menu.getSlot(rowindex + 1);
        Slot achievementsSlot = menu.getSlot(rowindex + 3);
        Slot settingsSlot = menu.getSlot(rowindex + 5);
        Slot quitSlot = menu.getSlot(rowindex + 7);



        // TODO: Multilang

        if (t == MenuType.STATS) {
            statsSlot.setItem(new ItemManager(Material.SKULL_ITEM).setSkullOwner("KeinOptifine").setData((short) 3).setGlow().setDisplayName(MessageManager.getString("stats_name", p)).build());
        } else {
            statsSlot.setItem(new ItemManager(Material.SKULL_ITEM).setSkullOwner("KeinOptifine").setData((short) 3).setDisplayName(MessageManager.getString("stats_name", p)).build());
        }

        if (t == MenuType.ACHIEVEMENTS) {
            achievementsSlot.setItem(new ItemManager(Material.BOOK).setDisplayName(MessageManager.getString("achievements_name", p)).setGlow().build());
        } else {
            achievementsSlot.setItem(new ItemManager(Material.BOOK).setDisplayName(MessageManager.getString("achievements_name", p)).build());
        }

        if (t == MenuType.SETTINGS) {
            settingsSlot.setItem(new ItemManager(Material.REDSTONE).setDisplayName(MessageManager.getString("settings_name", p)).setGlow().build());
        } else {
            settingsSlot.setItem(new ItemManager(Material.REDSTONE).setDisplayName(MessageManager.getString("settings_name", p)).build());
        }

        quitSlot.setItem(new ItemManager(Material.BARRIER).setDisplayName(MessageManager.getString("menu_close", p)).build());




        statsSlot.setClickHandler((player, info) -> {
            //closeMenu(player);
            if(SQLWrapper.isSounds(player.getUniqueId().toString()))
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
            openMenu(MenuType.STATS, player);
        });

        achievementsSlot.setClickHandler((player, info) -> {
            //closeMenu(player);
            if(SQLWrapper.isSounds(player.getUniqueId().toString()))
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
            openMenu(MenuType.ACHIEVEMENTS, player);
        });

        settingsSlot.setClickHandler((player, info) -> {
            //closeMenu(player);
            if(SQLWrapper.isSounds(player.getUniqueId().toString()))
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
            openMenu(MenuType.SETTINGS, player);
        });

        quitSlot.setClickHandler((player, info) -> closeMenu(player));



    }

}
