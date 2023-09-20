package eu.brickpics.skypvpplugin.commands;

import eu.brickpics.skypvpplugin.listener.JoinListener;
import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.*;


public class CMDKit implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;

        //defines Kit inv with 9 Slots
        Inventory inv = Bukkit.getServer().createInventory(p,9,"§b§lSkyPVP §6§lKits");


        //Setup Item in kit GUI (Slot 0) and Name of Kit ("§6§lAssassin")
        inv.setItem(0, new ItemManager(DIAMOND_SWORD).setDisplayName("§6§lAssassin").build());

        //Setup Item in kit GUI (Slot 1) and Name of Kit ("§6§lTank")
        inv.setItem(1, new ItemManager(IRON_CHESTPLATE).setDisplayName("§6§lTank").build());

        //Setup Item in kit GUI (Slot 2) and Name of Kit ("§6§lRod")
        inv.setItem(2, new ItemManager(FISHING_ROD).setDisplayName("§6§lRod").build());






    if(p.getLocation().getBlockY() >= 127) {
        //opens Kit inv
        p.openInventory(inv);

    }else{
        p.sendMessage("§8┃ §2SkyPvP §8× §7" + " §6§lYou can only change Kit at Spawn!");
        }




        return false;
    }
}
