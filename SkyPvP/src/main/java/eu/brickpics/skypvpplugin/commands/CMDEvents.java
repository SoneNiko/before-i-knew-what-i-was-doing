package eu.brickpics.skypvpplugin.commands;

import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Material.*;

public class CMDEvents implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player p = (Player) sender;

        if (p.hasPermission("SkyPvP.events")) {

            Inventory admininv = Bukkit.getServer().createInventory(p,9,"§6§lEvent Menu");

            admininv.setItem(0, new ItemManager(IRON_BOOTS).setDisplayName("§6§lSPEED").build());
            admininv.setItem(1, new ItemManager(FEATHER).setDisplayName("§6§lBUNNY").build());
            admininv.setItem(2, new ItemManager(GOLDEN_APPLE).setDisplayName("§6§lGOLDEN APPLE").build());

            p.openInventory(admininv);
        }



        return false;
    }
}
