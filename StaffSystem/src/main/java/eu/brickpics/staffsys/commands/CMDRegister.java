package eu.brickpics.staffsys.commands;

import eu.brickpics.staffsys.StaffSys;
import eu.brickpics.staffsys.punishment.Punishment;
import eu.brickpics.staffsys.punishment.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDRegister implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        assert sender instanceof Player;
        Player p = (Player) sender;
        StaffSys.getPunishmentManager().punish(Punishment.PunishmentType.valueOf(args[0]), "test", p,
                Bukkit.getOfflinePlayer(args[1]), args[2]);
        return true;
    }
}
