package eu.retrox.dragon;

import eu.retrox.dragon.commands.RDragonCommand;
import eu.retrox.dragon.listeners.ChunkListener;
import eu.retrox.dragon.listeners.DragonDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RDragon extends JavaPlugin {

    public static RDragon plugin;

    @Override
    public void onEnable() {
        RDragon.plugin = this;
        setupConfiguration();
        setupCommands();
        setupListeners();
    }

    private void setupConfiguration() {
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.setup();
    }

    private void setupCommands() {
        this.getCommand("rdragon").setExecutor(new RDragonCommand());
    }

    private void setupListeners() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new ChunkListener(), this);
        manager.registerEvents(new DragonDeathListener(), this);
    }

    public static RDragon getPlugin() {
        return plugin;
    }

}
