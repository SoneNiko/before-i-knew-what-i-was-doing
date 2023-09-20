package eu.brickpics.mentionsys.util;

import eu.brickpics.appearancemanager.SQLManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Registration {
    public static boolean isRegistered(UUID uuid) {
        boolean b = true;

        try {
            PreparedStatement preparedStatement = SQLManager.getInstance().getConnenction().prepareStatement("SELECT * FROM minecraft_players_information WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                b = rs.getString("REGISTRATED").equals("yes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    }
}
