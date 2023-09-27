package signifies.com.spawnutils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Objects;
import java.util.logging.Level;

public class SPUConfig  {


    private FileConfiguration uConfig = null;
    private File uConfigFile = null;
    private static final String fileName = "Config.yml";

    private SpawnUtils instance;

    public SPUConfig(SpawnUtils instance)
    {
        this.instance = instance;
    }


    public void reloaduConfig() {
        if (uConfigFile == null) {
            uConfigFile = new File(instance.getDataFolder(),fileName);
        }
        uConfig = YamlConfiguration.loadConfiguration(uConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(Objects.requireNonNull(instance.getResource(fileName)), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert defConfigStream != null;
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        uConfig.setDefaults(defConfig);
    }

    public FileConfiguration getuConfig() {
        if (uConfig == null) {
            reloaduConfig();
        }
        return uConfig;
    }

    public void saveuConfig() {
        if (uConfig == null || uConfigFile == null) {
            return;
        }
        try {
            getuConfig().save(uConfigFile);
        } catch (IOException ex) {
            instance.getLogger().log(Level.SEVERE, "Could not save config to " + uConfigFile, ex);
        }
    }

    public void saveDefaultuConfig() {
        if (uConfigFile == null) {
            uConfigFile = new File(instance.getDataFolder(), fileName);
        }
        if (!uConfigFile.exists()) {
            instance.saveResource(fileName, false);
        }
    }

}
