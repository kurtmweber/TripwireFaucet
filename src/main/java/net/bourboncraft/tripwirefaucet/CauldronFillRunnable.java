package net.bourboncraft.tripwirefaucet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.data.Levelled;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class CauldronFillRunnable implements Runnable {
    private final Integer newLevel;

    CauldronFillRunnable(Integer i){
        newLevel = i;
    }
    
    @Override
    public void run(){
        Levelled cauldronLevel;

        World w = CauldronFiller.cauldron.getWorld();
        w.spawnParticle(Particle.WATER_DROP, CauldronFiller.hook.getLocation(), 1000, 0.1, 0.1, 0.1);
        w.spawnParticle(Particle.WATER_SPLASH, CauldronFiller.hook.getLocation(), 1000, 0.1, 0.1, 0.1);
        w.spawnParticle(Particle.WATER_BUBBLE, CauldronFiller.hook.getLocation(), 1000, 0.1, 0.1, 0.1);

        if (CauldronFiller.cauldron.getType().equals(Material.CAULDRON)){
            CauldronFiller.cauldron.setType(Material.WATER_CAULDRON);
        } else {
            cauldronLevel = (Levelled) CauldronFiller.cauldron.getBlockData();
            cauldronLevel.setLevel(newLevel);
            CauldronFiller.cauldron.setBlockData(cauldronLevel);
        }

        if (newLevel < 3){
            Bukkit.getScheduler().scheduleSyncDelayedTask(getServer().getPluginManager().getPlugin("TripwireFaucet"), new CauldronFillRunnable(newLevel + 1), 10);
        }
    }
}
