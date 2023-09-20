package eu.brickpics.staffsys.punishment;

import eu.brickpics.staffsys.StaffSys;
import org.apache.commons.dbutils.ResultSetIterator;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import eu.brickpics.staffsys.util.DateUtil;
import org.jetbrains.annotations.NotNull;

public class PunishmentManager {

    public void punish(Punishment.PunishmentType type, String reason, Player staff, OfflinePlayer punished, String input){

        Punishment punishment = new Punishment(punished.getUniqueId(), reason, LocalDateTime.now(), DateUtil.parseDate(input), type, staff.getUniqueId());

        try {
            CaseManager.registerCase(punishment);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sb = "New Case: Person: " +
                (Bukkit.getOfflinePlayer(punishment.getPunished()).getName()) +
                ", Punishment: " +
                punishment.getPunishmentType() +
                ", End: " +
                Objects.requireNonNull(DateUtil.parseDate(input)) +
                ", Staffmember: " +
                staff.getName();
        Bukkit.getLogger().info(sb);

    }

    private Punishment getPunishmentByResultSet(ResultSet rs) throws SQLException {
        return new Punishment(
                UUID.fromString(rs.getString("UUID")),
                rs.getString("REASON"), LocalDateTime.parse(rs.getString("STARTTIME")),
                LocalDateTime.parse(rs.getString("ENDTIME")),
                Punishment.PunishmentType.valueOf(rs.getString("PUNISHMENT")),
                UUID.fromString(rs.getString("STAFFUUID"))
        );
    }

    private List<Punishment> getPunishmentsByResultSet(ResultSet rs) throws SQLException {
        List<Punishment> punishments = new ArrayList<>();
        while (rs.next()) {
            punishments.add(getPunishmentByResultSet(rs));
        }
        return punishments;
    }



















    public List<Punishment> getPunishementsOfPlayer(String name) {
        PreparedStatement preparedStatement = null;
        String getCasesOfPlString = "SELECT * FROM StaffSystemCases WHERE NAME=?";

        return getPunishments(name, preparedStatement, getCasesOfPlString);
    }

    public List<Punishment> getPunishementsOfStaffMember(String uuid) {
        PreparedStatement preparedStatement = null;
        String getCasesOfPlString = "SELECT * FROM StaffSystemCases WHERE STAFFUUID=?";

        return getPunishments(uuid, preparedStatement, getCasesOfPlString);
    }




    @NotNull
    private List<Punishment> getPunishments(String uuid, PreparedStatement preparedStatement, String getCasesOfPlString) {
        ArrayList<Punishment> results = new ArrayList<>();

        try {
            StaffSys.getMysql().getConnection().setAutoCommit(false);
            preparedStatement = StaffSys.getMysql().getConnection().prepareStatement(getCasesOfPlString);

            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();
            StaffSys.getMysql().getConnection().commit();

            ResultSet rs = preparedStatement.getResultSet();

            int i = 0;
            while (rs.next()) {
                i++;

                results.add(new Punishment(
                        UUID.fromString(rs.getString("UUID")),
                        rs.getString("REASON"), DateUtil.parseDate(rs.getString("STARTTIME")),
                        DateUtil.parseDate(rs.getString("ENDTIME")),
                        Punishment.PunishmentType.valueOf(rs.getString("PUNISHMENT")),
                        UUID.fromString(rs.getString("STAFFUUID"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                    StaffSys.getMysql().getConnection().setAutoCommit(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return results;
    }


}
