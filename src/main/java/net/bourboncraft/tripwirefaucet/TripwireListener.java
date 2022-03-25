package net.bourboncraft.tripwirefaucet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

public class TripwireListener implements Listener {
    @EventHandler
    public void onTripwireClicked(@NotNull PlayerInteractEvent e){
        Block b = e.getClickedBlock();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getHand().equals(EquipmentSlot.HAND) && b.getType().equals(Material.TRIPWIRE_HOOK)){
            Block d = b.getRelative(BlockFace.DOWN);

            if (d.getType().equals(Material.CAULDRON)){
                d.setType(Material.WATER_CAULDRON);
            }

            if (d.getType().equals(Material.WATER_CAULDRON)){
                Levelled dbd = (Levelled)d.getBlockData();
                dbd.setLevel(dbd.getMaximumLevel());
                d.setBlockData(dbd);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.isOp() && UpdateChecker.updateAvailable){
            p.sendMessage("There is an update available for TripwireFaucet!");
        }
    }
}
