package net.bourboncraft.tripwirefaucet;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;

import static org.bukkit.Bukkit.getServer;

public class CauldronFiller {
    public static Block hook;
    public static Block cauldron;
    private final Integer currentLevel;

    CauldronFiller(Integer level, Block hook, Block cauldron){
        CauldronFiller.hook = hook;
        CauldronFiller.cauldron = cauldron;
        currentLevel = level;

        fillCauldron();
    }

    private void fillCauldron(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(getServer().getPluginManager().getPlugin("TripwireFaucet"), new CauldronFillRunnable(currentLevel + 1), 10);
    }


}
