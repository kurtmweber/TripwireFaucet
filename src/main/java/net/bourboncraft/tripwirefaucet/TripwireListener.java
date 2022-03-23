package net.bourboncraft.tripwirefaucet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class TripwireListener implements Listener {
    @EventHandler
    public void onTripwireClicked(PlayerInteractEvent e){
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
}
