package signifies.com.spawnutils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum SPPermissions {

    STAFF_CHAT("SpawnUtils.staffchat"),
    RELOAD("SpawnUtils.reload"),
    NOTIFY("SpawnUtils.whitelist.notify"),

    COMMAND_WHITELIST("SpawnUtils.whitelist"),
    BYPASS_STATUS("SpawnUtils.status"),
    OVERRIDE("SpawnUtils.whitelist.override"),
    NOTIFICATIONS("SpawnUtils.notify"),
    CMS_COMMAND("CMS.access"),
    BUILD_SET_WARPS("warps.set"),
    BUILD_COMMAND_WARP("warps.warp"),
    BUILD_COMMAND_WARP_LIST("Warps.list"),
    PLUGIN_DONATOR_2 ("plugin.donator.2");

    private String key;

    SPPermissions(String key) {
    }

    public boolean checkPermission(CommandSender sender)
    {
        //System.out.println(check(sender));
        return sender.hasPermission(getKey());
    }

    public boolean checkPermission(Player p){
        //System.out.println(check(p));
        return p.hasPermission(getKey());
    }

    public String getKey() {
        return key;
    }

}
