package net.bourboncraft.tripwirefaucet;

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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

public class TripwireListener implements Listener {
    @EventHandler
    public void onTripwireClicked(@NotNull PlayerInteractEvent e){
        Block hook = e.getClickedBlock();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getHand().equals(EquipmentSlot.HAND) && hook.getType().equals(Material.TRIPWIRE_HOOK)){
            Block cauldron = hook.getRelative(BlockFace.DOWN);

            if (cauldron.getType().equals(Material.CAULDRON)){
                new CauldronFiller(0, hook, cauldron);
            }

            if (cauldron.getType().equals(Material.WATER_CAULDRON)){
                /*Levelled dbd = (Levelled)d.getBlockData();
                int i = dbd.getLevel();
                getLogger().info("Initial level: " + i);
                for (; i < dbd.getMaximumLevel(); i++){
                    getLogger().info("Setting to: " + i);

                    /**/

                    /*Bukkit.getScheduler().scheduleSyncDelayedTask(getServer().getPluginManager().getPlugin("TripwireFaucet"), new CauldronFillRunnable(dbd, d), 10);
                }*/
                new CauldronFiller(((Levelled)cauldron.getBlockData()).getLevel(), hook, cauldron);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.isOp()){
            Plugin pu = getServer().getPluginManager().getPlugin("TripwireFaucet");
            assert pu != null;

            String curVer = pu.getDescription().getVersion();
            new UpdateChecker((JavaPlugin) pu, TripwireFaucet.spigotId).getVersion((version) -> {
                if (!curVer.equals(version)){
                    p.sendMessage("There is a new TripwireFaucet version available!");
                }
            });
        }
    }
}
