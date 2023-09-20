package eu.brickpics.skypvpplugin.commands;

import eu.brickpics.skypvpplugin.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.util.EulerAngle;

import static org.bukkit.Material.*;

public class CMDKitArmorStand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;

        if(p.hasPermission("SkyPvP.villager")) {
            Location loc = p.getLocation();
            ArmorStand kitstand = Bukkit.getWorld("world").spawn(loc, ArmorStand.class);
            kitstand.setCustomName("§b§lSkyPVP §6§lKits");
            kitstand.setCustomNameVisible(true);
            kitstand.setGravity(false);
            kitstand.setArms(true);
            kitstand.setBasePlate(false);
            kitstand.setHeadPose(new EulerAngle(Math.toRadians(8),Math.toRadians(0),Math.toRadians(0)));
            kitstand.setLeftLegPose(new EulerAngle(Math.toRadians(24),Math.toRadians(0),Math.toRadians(0)));
            kitstand.setRightLegPose(new EulerAngle(Math.toRadians(343),Math.toRadians(0),Math.toRadians(0)));
            kitstand.setLeftArmPose(new EulerAngle(Math.toRadians(0),Math.toRadians(0),Math.toRadians(0)));
            kitstand.setRightArmPose(new EulerAngle(Math.toRadians(276),Math.toRadians(12),Math.toRadians(0)));


            kitstand.setHelmet(new ItemManager(Material.SKULL_ITEM).setSkullOwner("po_ro").setData((short) SkullType.PLAYER.ordinal()).build());
            kitstand.setChestplate(new ItemManager(DIAMOND_CHESTPLATE).setUnbreakable(true).build());
            kitstand.setLeggings(new ItemManager(IRON_LEGGINGS).setUnbreakable(true).build());
            kitstand.setBoots(new ItemManager(DIAMOND_BOOTS).setUnbreakable(true).build());
            kitstand.setItemInHand(new ItemManager(DIAMOND_SWORD).setUnbreakable(true).build());



        }



        return false;
    }
}
