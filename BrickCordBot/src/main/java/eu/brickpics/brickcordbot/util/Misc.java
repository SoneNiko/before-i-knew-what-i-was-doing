package eu.brickpics.brickcordbot.util;

public class Misc {
    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
