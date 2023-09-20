package eu.brickpics.stairseat;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class SeatManager {
    public static HashMap<Player, Seat> playerSeats = new HashMap<>();

    public static Seat getSeat(Player p) { return playerSeats.get(p); }

    public static Player getPlayer(Seat a) {
        for (Map.Entry<Player, Seat> playerSeatEntry : playerSeats.entrySet()) {
            if (playerSeatEntry.getValue().equals(a)) {
                return playerSeatEntry.getKey();
            }
        }
        return null;
    }


    public static Arrow makeArrow(Location location)  {
        //Arrow arrow = (Arrow) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARROW);
        //TODO: Make invis
        //NBTEntity nbtEntity = new NBTEntity(arrow);

        //Bukkit.getLogger().info("Spawning Arrow");

        Arrow arrow = (Arrow) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARROW);
        NBTEntity nbtent = new NBTEntity(arrow);
        nbtent.setByte("pickup", (byte) 0);
        nbtent.setString("NoGravity", "1b");

        return arrow;
    }

    public static void sitPlayerDown(Player player, Block block) {

        Bukkit.getLogger().info("Sitting down player");

        Arrow arrow = makeArrow(block.getLocation().clone().add(0.5, 0, 0.5));
        //TODO: Turn around player

        arrow.setPassenger(player);

        playerSeats.put(player, new Seat(block, arrow));

    }

    public static void standPlayerUp(Player player) {
        try {
            player.teleport(player.getLocation());
        } catch (NullPointerException e) {
            Bukkit.getLogger().info("Spieler ist Offline und konnte nicht aus dem Sitz geworfen werden");
        }
        getSeat(player).getArrow().remove();
        playerSeats.remove(player, getSeat(player));
    }
}
