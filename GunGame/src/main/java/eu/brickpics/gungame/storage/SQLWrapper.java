package eu.brickpics.gungame.storage;

import eu.brickpics.gungame.main.GunGame;
import eu.brickpics.gungame.manager.multilang.AchievementManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class SQLWrapper {
    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
            if (rs.next())
                return (rs.getString("UUID") != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid))
            GunGame.mysql.update("INSERT INTO GunGameStats(UUID, KILLS, DEATHS, HIGHSCORE, SOUNDS, ACNOTIFY, TITLES, ACHIEVEMENTS) VALUES ('" + uuid + "', '0', '0', '0', '1', '1', '1', '000000000000000000000');");
    }

    public static Integer getKills(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("KILLS")) == null) ;
                i = Integer.valueOf(rs.getInt("KILLS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static Integer getDeaths(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("DEATHS")) == null) ;
                i = Integer.valueOf(rs.getInt("DEATHS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }

    public static Integer getHighScore(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("HIGHSCORE")) == null) ;
                i = Integer.valueOf(rs.getInt("HIGHSCORE"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }

    public static void setKills(String uuid, Integer kills) {
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setKills(uuid, kills);
        }
    }

    public static void setDeaths(String uuid, Integer deaths) {
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }

    public static void setHighScore(String uuid, Integer highscore) {
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET HIGHSCORE= '" + highscore + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDeaths(uuid, highscore);
        }
    }

    public static void addKills(String uuid, Integer kills) {
        if (playerExists(uuid)) {
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() + kills.intValue()));
        } else {
            createPlayer(uuid);
            addKills(uuid, kills);
        }
    }

    public static void addDeaths(String uuid, Integer deaths) {
        if (playerExists(uuid)) {
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() + deaths.intValue()));
        } else {
            createPlayer(uuid);
            addDeaths(uuid, deaths);
        }
    }

    public static void removeKills(String uuid, Integer kills) {
        if (playerExists(uuid)) {
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() - kills.intValue()));
        } else {
            createPlayer(uuid);
            removeKills(uuid, kills);
        }
    }

    public static void removeDeaths(String uuid, Integer deaths) {
        if (playerExists(uuid)) {
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() - deaths.intValue()));
        } else {
            createPlayer(uuid);
            removeDeaths(uuid, deaths);
        }
    }


    public static Integer getSounds(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("SOUNDS")) == null) ;
                i = Integer.valueOf(rs.getInt("SOUNDS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }

    public static boolean isSounds(String uuid) {
        return getSounds(uuid) == 1;
    }

    public static void setSounds(String uuid, boolean b) {
        Integer i;
        if (b)
            i = 1;
        else
            i = 0;
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET SOUNDS= '" + i + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setSounds(uuid, b);
        }
    }


    public static Integer getACNotify(String uuid) {
        int i = 0;
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("ACNOTIFY")) == null)) ;
                i = rs.getInt("ACNOTIFY");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getACNotify(uuid);
        }
        return i;
    }

    public static void setACNotify(String uuid, Integer acnotify) {
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET ACNOTIFY= '" + acnotify + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setACNotify(uuid, acnotify);
        }
    }



    public static int getTitles(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("TITLES")) == null);
                i = Integer.valueOf(rs.getInt("TITLES"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getTitles(uuid);
        }
        return i;
    }

    public static boolean isTitles(String uuid) {
        return getTitles(uuid) == 1;
    }

    public static void setTitles(String uuid, boolean b) {
        Integer i;
        if (b)
            i = 1;
        else
            i = 0;
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET TITLES= '" + i + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setTitles(uuid, b);
        }
    }


    public static boolean playerExistsInLobby(String uuid) {
        try {
            ResultSet rs = GunGame.mysql.query("SELECT * FROM LobbyData WHERE UUID= '" + uuid + "'");
            if (rs.next())
                return (rs.getString("UUID") != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayerInLobby(String uuid) {
        if (!playerExistsInLobby(uuid))
            GunGame.mysql.update("INSERT INTO LobbyData(UUID, ACCEPTDATA, ACTIVEHIDE, ACTIVEGADGET, ACTIVEHAT, ACTIVEPARTICLE, SPAWNLOC, COLOR, MOVEMENT) VALUES ('" + uuid + "', '0', '0', '0', '0', '0', '0', '3', '0');");
    }

    public static Integer getColor(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExistsInLobby(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM LobbyData WHERE UUID= '" + uuid + "'");
                if (!rs.next() || Integer.valueOf(rs.getInt("COLOR")) == null);
                i = Integer.valueOf(rs.getInt("COLOR"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayerInLobby(uuid);
            getColor(uuid);
        }
        return i;
    }

    public static HashMap<Integer, String> gungame_rang = new HashMap<Integer, String>();

    public static void setGunGameRanking() {

        ResultSet rs = GunGame.mysql.query("SELECT UUID FROM GunGameStats ORDER BY KILLS DESC LIMIT 5");

        int i = 0;

        try {
            while(rs.next()) {
                i++;
                gungame_rang.put(i, rs.getString("UUID"));
                if (!AchievementManager.hasAchievement(3, rs.getString("UUID")))
                    AchievementManager.setAchievement(3, true, rs.getString("UUID"));

                if (i == 1) {
                    if (!AchievementManager.hasAchievement(4, rs.getString("UUID")))
                        AchievementManager.setAchievement(4, true, rs.getString("UUID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAchievements(String uuid) {
        String s = "";
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GunGame.mysql.query("SELECT * FROM GunGameStats WHERE UUID= '" + uuid + "'");
                if (!rs.next() || String.valueOf(rs.getString("ACHIEVEMENTS")) == null);
                s = String.valueOf(rs.getString("ACHIEVEMENTS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getAchievements(uuid);
        }
        return s;
    }

    public static void setAchievements(String uuid, String s) {
        if (playerExists(uuid)) {
            GunGame.mysql.update("UPDATE GunGameStats SET ACHIEVEMENTS= '" + s + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setAchievements(uuid, s);
        }
    }
}
