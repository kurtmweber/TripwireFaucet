package net.bourboncraft.tripwirefaucet;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class TripwireFaucet extends JavaPlugin {
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new TripwireListener(), this);
        int pluginId = 14705; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);
        getLogger().info("TripwireListener with bStats registered");
    }

    @Override
    public void onDisable(){
        getLogger().info("TripwireFaucet disabled");
    }
}
