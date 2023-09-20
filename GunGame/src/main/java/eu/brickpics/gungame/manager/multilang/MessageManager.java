package eu.brickpics.gungame.manager.multilang;

import net.geknxddelt.informationsystem.api.LanguageAPI;
import org.bukkit.entity.Player;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    public static ResourceBundle rs_de = ResourceBundle.getBundle("messages", Locale.GERMAN);
    public static ResourceBundle rs_en = ResourceBundle.getBundle("messages", Locale.ENGLISH);

    public static String getString(String id, Player p) {
        Locale l = getLocaleFromPlayer(p);

        if (l.getLanguage().equalsIgnoreCase("en"))
            return rs_en.getString(id);
        if (l.getLanguage().equalsIgnoreCase("de"))
            return rs_de.getString(id);

        return rs_en.getString(id);
    }

    public static Locale getLocaleFromPlayer(Player p) {
        int i = LanguageAPI.getLanguagePlayer(p);
        switch (i) {
            case 1:
                return Locale.GERMAN;
            case 0:
            default:
                return Locale.ENGLISH;
        }

    }


}
