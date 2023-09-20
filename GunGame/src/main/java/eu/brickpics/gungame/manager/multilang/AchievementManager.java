package eu.brickpics.gungame.manager.multilang;

import eu.brickpics.gungame.manager.util.Log;
import eu.brickpics.gungame.storage.Data;
import eu.brickpics.gungame.storage.SQLWrapper;
import net.geknxddelt.informationsystem.api.TitlesAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.*;

public class AchievementManager {

    public static HashMap<Integer, String> indexIdHashMap = new HashMap<>();

    public static void initHashMap() {
        AchievementManager.indexIdHashMap.put(0, "swimdeath");
        AchievementManager.indexIdHashMap.put(1, "fairplay");
        AchievementManager.indexIdHashMap.put(2, "scaredycat");
        AchievementManager.indexIdHashMap.put(3, "top5");
        AchievementManager.indexIdHashMap.put(4, "top1");

        AchievementManager.indexIdHashMap.put(5, "leather");
        AchievementManager.indexIdHashMap.put(6, "leatherec");

        AchievementManager.indexIdHashMap.put(7, "gold");
        AchievementManager.indexIdHashMap.put(8, "goldec");

        AchievementManager.indexIdHashMap.put(9, "chain");
        AchievementManager.indexIdHashMap.put(10, "chainec");

        AchievementManager.indexIdHashMap.put(11, "iron");
        AchievementManager.indexIdHashMap.put(12, "ironec");

        AchievementManager.indexIdHashMap.put(13, "dia");
        AchievementManager.indexIdHashMap.put(14, "diaec");

        AchievementManager.indexIdHashMap.put(15, "kills10");
        AchievementManager.indexIdHashMap.put(16, "kills100");
        AchievementManager.indexIdHashMap.put(17, "kills1000");

        AchievementManager.indexIdHashMap.put(18, "deaths10");
        AchievementManager.indexIdHashMap.put(19, "deaths100");
        AchievementManager.indexIdHashMap.put(20, "deaths1000");
    }

    public static ResourceBundle ac_en = ResourceBundle.getBundle("achievements", Locale.ENGLISH);
    public static ResourceBundle ac_de = ResourceBundle.getBundle("achievements", Locale.GERMAN);



    public static String getAchievementName(int i, Player p) {
        Locale l = MessageManager.getLocaleFromPlayer(p);

        String s = indexIdHashMap.get(i);

        if (l.getLanguage().equalsIgnoreCase("en"))
            return ac_en.getString(s);
        if (l.getLanguage().equalsIgnoreCase("de"))
            return ac_de.getString(s);

        return ac_en.getString(s);
    }

    public static String getAchievementDescription(int i, Player p){
        Locale l = MessageManager.getLocaleFromPlayer(p);

        String s = indexIdHashMap.get(i);

        if (l.getLanguage().equalsIgnoreCase("en"))
            return ac_en.getString(s + "_desc");
        if (l.getLanguage().equalsIgnoreCase("de"))
            return ac_de.getString(s + "_desc");

        return ac_en.getString(s + "_desc");
    }

    public static boolean hasAchievement(int id, String uuid) {
        char[] achievementString = SQLWrapper.getAchievements(uuid).toCharArray();
        return achievementString[id] != '0';
    }



    public static void setAchievement(int id, boolean b, String uuid) {
        char[] achievementString = SQLWrapper.getAchievements(uuid).toCharArray();
        Player p = Bukkit.getPlayer(UUID.fromString(uuid));

        if (b)
            achievementString[id] = '1';
        else
            achievementString[id] = '0';



        try {
            if (SQLWrapper.getACNotify(uuid) == 1) {

                p.sendMessage(Data.PREFIX + "Achievement unlocked: §6" + AchievementManager.getAchievementName(id, p));
                if (SQLWrapper.isSounds(uuid))
                    p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1F, 1F);
                if (SQLWrapper.isTitles(uuid)) {
                    try {
                        TitlesAPI.sendTitle(Bukkit.getPlayer(UUID.fromString(uuid)), 20, 20, 20, "Achievement", AchievementManager.getAchievementName(id, Bukkit.getPlayer(UUID.fromString(uuid))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        StringBuilder s = new StringBuilder();
        for (char c : achievementString) {
            s.append(c);
        }
        SQLWrapper.setAchievements(uuid, s.toString());
    }
}
