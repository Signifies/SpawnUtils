package signifies.com.spawnutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SPUtils {

    /**
     * Plugin prefix.
     */
    public static String prefix = ChatColor.translateAlternateColorCodes('&',"&6SpawnUtils&7->");

    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    private String permission = color("&cUnknown command. Type \"/help\" for help.");



    public String defaultMessage(boolean value, String msg)
    {
        return value ? color(permission) : color(msg);
    }

    public ArrayList<String> commandList()
    {
        ArrayList<String> value = new ArrayList<String>();

        value.add("     &f----- &7Util Commands &f-----");

        return value;
    }

    public void sendText(List<String> text, Player sender)
    {
        int amt = Bukkit.getServer().getOnlinePlayers().size();
        int max = Bukkit.getServer().getMaxPlayers();

        for(String txt: text)
        {
            txt = txt.replace("{online_players}", ""+amt);
            txt = txt.replace("{max_players}", ""+max);
            txt = txt.replace("{player}",sender.getName());
            txt = txt.replace("{uuid}",sender.getUniqueId().toString());
            txt = txt.replace("{display_name}",sender.getDisplayName());
            txt = txt.replace("{IP}", sender.getAddress().toString());
            //txt = txt.replace("{time}",getStamp().toString());
            sender.sendMessage(color(txt));
        }
    }
    public void sendText(ArrayList<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("{player}",sender.getName());
            sender.sendMessage(color(txt));
        }
    }
    public void sendText(ArrayList<String> text, Player sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("{player}",sender.getName());
            sender.sendMessage(color(txt));
        }
    }

    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return prefix;
    }

    public void clearPlayer(Player p)
    {
        for(int i=0; i < 100; i++)
        {
            p.sendMessage("");
        }
        p.sendMessage(color("&7Your chat has been &7&nCleared&c, by an Admin, &a&n" + p.getName()));
    }

    public void selfClear(CommandSender sender) {
        for(int i=0; i <100; i++) {
            sender.sendMessage("");
        }
        sender.sendMessage( ChatColor.GRAY + "You have cleared your own chat, "+ ChatColor.GREEN +sender.getName());
    }

    public void clear() {
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            for(int i=0; i <100; i ++)
            {
                p.sendMessage("");
            }
        }
        Bukkit.broadcastMessage(color("&7The chat has been &acleared."));
    }




    /**
     * Method that handles all the color formatting
     *
     * @param message
     * @return
     */
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String check(boolean value, String name)
    {
        return  value ? name +ChatColor.GREEN +" [Enabled]"  : name + ChatColor.DARK_RED+" [Disabled]";
    }
    public String msg(String configPath, boolean removeColor, String[] values)
    {
        String result = configPath;
        if(result !=null)
        {
            if(removeColor)
            {
                result = color(result);
            }
            for(int k=0; k < values.length; k++)
            {
                String replaced = (values[k] != null) ? values[k] : "NULL";
                result = result.replace("{"+ k +"}",replaced);
            }
            return removeColor ? result : color(result);
        }
        log("Error finding a message!",1);
        return null;
    }

    public String msg(String path, String... replaced)
    {
        return msg(path,false,replaced);
    }

    public String msg(String path)
    {
        return msg(path,false,new String[0]);
    }


    public void clearArmor(Player p)
    {
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
    }

    public void information(Plugin plugin, Player author)
    {
        String text = color("&0---- &6Your Information &0----");
        author.sendMessage(color(text));
        author.sendMessage(color("&cIP: &a"+ author.getAddress().toString()));
        author.sendMessage(color("&cUUID: &3"+author.getUniqueId().toString()));
        author.sendMessage(color("&cName: &e"+author.getName()));
        String format = color("&0------------------------");
        author.sendMessage(format);
        String os =  System.getProperty("os.name");
        author.sendMessage(color("&4OS: &a&l"+os));
        double freeD= (double) new File(plugin.getDataFolder() + "/..").getFreeSpace() /1073741824;
        double totalD= (double) new File(plugin.getDataFolder() + "/..").getTotalSpace() /1073741824;
        author.sendMessage(ChatColor.AQUA+"Disk space used: "+ChatColor.GREEN+new DecimalFormat("#.##").format(totalD-freeD)+ChatColor.YELLOW+"/"+new DecimalFormat("#.##").format(totalD)+ChatColor.YELLOW+" GB ("+new DecimalFormat("#.##").format(((totalD-freeD)/totalD)*100)+"% used)");

        double free = (double) Runtime.getRuntime().freeMemory() / 1048576;
        double total = (double) Runtime.getRuntime().totalMemory() / 1048576;
        author.sendMessage(ChatColor.RED + "RAM Used: " + ChatColor.GREEN + new DecimalFormat("#.###").format(total - free) + ChatColor.YELLOW + "/" + new DecimalFormat("#.###").format(total) + ChatColor.YELLOW + " MB (" + new DecimalFormat("#.##").format(((total - free) / total) * 100) + "% used)");
        author.sendMessage(ChatColor.RED+"Number of cores: "+ChatColor.YELLOW+Runtime.getRuntime().availableProcessors());
        author.sendMessage(ChatColor.RED+"Java version: "+ChatColor.YELLOW+System.getProperty("java.version"));
        int c=0;
        for(World w: Bukkit.getWorlds())c+=w.getLoadedChunks().length;
        author.sendMessage(ChatColor.RED+"Chunks loaded: "+ChatColor.YELLOW+c);
    }
    public String getUsers()
    {
        StringBuilder sb = new StringBuilder();
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            sb.append(p.getName() + ", ");
        }
        return ""+sb.toString();
    }

    public void runCommands(ArrayList<String> commands)
    {
        for(String cmd : commands)
        {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
        }
    }

    public void runCommand(String cmd) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
        log("&4[!]Command execution: &6" +cmd,1);
    }

    public void runCommands(List<String> commands, Player p, String inform) {
        p.sendMessage(color(inform));
        for (String cmd : commands)
        {
            cmd = cmd.replace("{player}",p.getName());
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);

        }
    }


    public void log(String msg, int priority) {
        if(SpawnUtils.DEBUG || priority > 0) {
            Bukkit.getServer().getConsoleSender().sendMessage(prefix + color("&f[&4LOG&f]&r &6" + msg));
        }
    }

    public ArrayList<String> warps(List<String> s)
    {
        ArrayList<String> value = new ArrayList<>();
        value.add("     &f----- &bWarps &f-----");
        value.add("&6"+s);
        value.add("&7------------------");
        return value;
    }

    //TODO our get prefix method will be the replacement value inside our chat API.

    public void enforceWhitelist(CommandSender staff) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(!p.isWhitelisted() && !(SPPermissions.BYPASS_STATUS.checkPermission(p))) {
                p.kickPlayer(color("&4[!] Server whitelist has been enforced!"));
            }
        }
        staff.sendMessage(color("&7The whitelist has been &aenforced."));
        //TODO Notifications API here.
        //adminNotifications(staff.getName(), "Enforced the whitelist.");
    }
    public void enforceWhitelist(CommandSender staff, String msg) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(!p.isWhitelisted() && !(SPPermissions.BYPASS_STATUS.checkPermission(p))) {
                p.kickPlayer(color(msg));
            }
        }
        //TODO Notifications API here.
    }
}
