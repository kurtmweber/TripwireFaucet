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

        new UpdateChecker(this, 100861).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is not a new update available.");
                UpdateChecker.updateAvailable = false;
            } else {
                UpdateChecker.updateAvailable = true;
                getLogger().info("There is a new update available.");
            }
        });
    }

    @Override
    public void onDisable(){
        getLogger().info("TripwireFaucet disabled");
    }
}
