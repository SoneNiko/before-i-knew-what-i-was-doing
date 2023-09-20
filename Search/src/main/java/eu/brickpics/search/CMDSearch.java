package eu.brickpics.search;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CMDSearch implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            return false;
        }
        Player player = null;
        try {
            player = Bukkit.getPlayer(args[0]);
        } catch (Exception e) {
            sender.sendMessage("Player does not exist");
            return false;
        }
        assert player != null;


        Connection con = null;
        try {
            con = SQLManager.getInstance().getConnenction();
        } catch (SQLException exception) {
            return false;
        }

        try {
            PreparedStatement preparedStatement = con.prepareStatement("SHOW TABLES;");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                PreparedStatement preparedStatement1 = con.prepareStatement("SELECT * FROM ? WHERE mc_uuid=? OR uuid=?");
            }
        } catch (SQLException exception) {
            return false;
        }
        return true;
    }
}
