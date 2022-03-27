package net.bourboncraft.tripwirefaucet;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class TripwireFaucet extends JavaPlugin {
    public static int bStatsId = 14705;
    public static int spigotId = 100861;

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new TripwireListener(), this);
        Metrics metrics = new Metrics(this, bStatsId);

        new UpdateChecker(this, spigotId).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is not a new update available.");
            } else {
                getLogger().info("There is a new update available.");
                getLogger().info("Available version: " + version);
                getLogger().info("Currently installed version: " + this.getDescription().getVersion());
            }
        });
    }

    @Override
    public void onDisable(){
        getLogger().info("TripwireFaucet disabled");
    }
}
