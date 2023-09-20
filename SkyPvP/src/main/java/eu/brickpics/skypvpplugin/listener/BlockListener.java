package eu.brickpics.skypvpplugin.listener;

        import org.bukkit.GameMode;
        import org.bukkit.Material;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.BlockBreakEvent;
        import org.bukkit.event.block.BlockBurnEvent;
        import org.bukkit.event.block.BlockPlaceEvent;
        import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
        import org.bukkit.event.player.PlayerInteractEvent;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission("skypvp.build")) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().hasPermission("skypvp.build")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void enderChest(PlayerInteractEvent e){

        if(e.getClickedBlock().getType() == Material.ENDER_CHEST || e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE || e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.ANVIL || e.getClickedBlock().getType() == Material.WORKBENCH){

            e.setCancelled(true);
        }


    }

    @EventHandler
    public void armorStand(PlayerArmorStandManipulateEvent e){

        e.setCancelled(true);



    }

}
