package signifies.com.spawnutils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SpawnUtils  extends JavaPlugin {

    public static boolean DEBUG = true;
    private SPUConfig spuConfig;

    public void onEnable() {
        spuConfig = new SPUConfig(this);
        loadConfiguration();
        System.out.println("LOL IMAGINE CODING SPIGOT PLUGINS AGAIN.");
        //TODO registering commands will go here, if there are any.
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this),this);
    }

    void loadConfiguration() {
        spuConfig.saveDefaultuConfig();
        spuConfig.saveuConfig();
    }


    private void registerCmd(String command, CommandExecutor commandExecutor) {
        Objects.requireNonNull(Bukkit.getServer().getPluginCommand(command)).setExecutor(commandExecutor);
    }

    public SPUConfig getConf() {
        return spuConfig;
    }
}
