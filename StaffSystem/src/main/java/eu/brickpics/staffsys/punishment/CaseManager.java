package eu.brickpics.staffsys.punishment;

import eu.brickpics.staffsys.StaffSys;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class CaseManager {


    public static void registerCase(Punishment punishment) throws SQLException {

        PreparedStatement createCase = null;
        String createCaseString = "INSERT INTO StaffSystemCases(UUID, NAME, STARTTIME, ENDTIME, PUNISHMENT, REASON, STAFFUUID) VALUES (UUID=?, NAME=?, STARTTIME=?, ENDTIME=?, PUNISHMENT=?, REASON=?, STAFFUUID=?)";

        UUID uuid = punishment.getPunished();
        String name = Bukkit.getOfflinePlayer(punishment.getPunished()).getName();

        LocalDateTime startTime = LocalDateTime.now();
        String start = startTime.toString();

        LocalDateTime endTime = punishment.getEndTime();
        String end = endTime.toString();

        Punishment.PunishmentType type = punishment.getPunishmentType();
        String reason = punishment.getReason();
        UUID staffuuid = punishment.getStaffUUID();

        //1y2M3w4d5h6m7s

        try {
            createCase = StaffSys.getMysql().getConnection().prepareStatement(createCaseString);

            createCase.setString(1, uuid.toString());
            createCase.setString(2, name);
            createCase.setString(3, start);
            createCase.setString(4, end);
            createCase.setString(5, type.toString());
            createCase.setString(6, reason);
            createCase.setString(7, staffuuid.toString());
            createCase.executeUpdate();

            StaffSys.getMysql().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (StaffSys.getMysql().getConnection() != null) {
                try {
                    Bukkit.getLogger().severe("SQLException. Rollback");
                    StaffSys.getMysql().getConnection().rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        if (createCase != null) {
            createCase.close();
        }
        assert StaffSys.getMysql().getConnection() != null;

        //StaffSys.getMysql().getConnection().setAutoCommit(true);

        //StaffSys.getMysql().update("INSERT INTO StaffSystemCases(UUID, NAME, STARTTIME, ENDTIME, PUNISHMENT, REASON, STAFFUUID) VALUES ('" + uuid.toString() + "', '" + name + "', '" + start + "', '" + end + "', '" + type.toString() + "', '" + reason + "', '" + staffuuid.toString() + "');");
    }
}
