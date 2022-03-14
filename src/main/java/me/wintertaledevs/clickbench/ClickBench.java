package me.wintertaledevs.clickbench;

import me.wintertaledevs.clickbench.Utils.FileManager;
import me.wintertaledevs.clickbench.Utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ClickBench extends JavaPlugin implements Listener, CommandExecutor {

    public static ClickBench plugin;
    public String prefix = "&e[&6ClickBench&e] ";
    public FileManager config;
    public FileManager data;

    @Override
    public void onEnable() {
        plugin = this;

        config = new FileManager(getDataFolder(), "config.yml");
        data = new FileManager(getDataFolder(), "data.yml");

        config.GetConfig().addDefault("prefix", "&e[&6ClickBench&e] ");
        config.GetConfig().addDefault("enabled", true);
        config.Save();
        data.Save();
        prefix = config.GetConfig().getString("prefix");


        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("clickbench").setExecutor(this);

        System.out.println(ChatColor.GREEN + ChatColor.stripColor(prefix + "Plugin Enabled"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Utils.GenC(prefix + "&6Developed by &eYessify\n&6Usage: \n&e/clickbench toggle -> Toggle the plugin ON/OFF for you only\n/clickbench toggle g -> Toggle the plugin ON/OFF globally"));
            return true;
        }
        String cmd = args[0];
        if(!(sender instanceof Player)) {
            sender.sendMessage(Utils.GenC(prefix + "&cThis command can only be used by players."));
            return true;
        }
        Player player = (Player) sender;
        if(cmd.equalsIgnoreCase("toggle")) {
            if(args.length >= 2) {
                boolean g = (args[1].equalsIgnoreCase("global") || args[1].equalsIgnoreCase("globally") || args[1].equalsIgnoreCase("g"));
                if (g && player.hasPermission("cb.globaltoggle")) {
                    boolean newstat = !((boolean)config.GetConfig().get("enabled"));
                    config.GetConfig().set("enabled", newstat);
                    String stat = "&aenabled";
                    if (!newstat) stat = "&cdisabled";
                    player.sendMessage(Utils.GenC(prefix + "&6All plugin features are now " + stat + " &b(Globally)"));
                    return true;
                } else if(!player.hasPermission("cb.globaltoggle")) {
                    player.sendMessage(Utils.GenC(prefix + "&cYou need the permission \"cb.globaltoggle\" to use this command."));
                }
            }
            boolean newstat = true;
            if(Utils.GetPlayerData(player, "enabled") != null) newstat = !((boolean)Utils.GetPlayerData(player, "enabled"));
            Utils.SetPlayerData(player, "enabled", newstat);
            String stat = "&aenabled";
            if(!newstat) stat = "&cdisabled";
            player.sendMessage(Utils.GenC(prefix + "&6All plugin features are now " + stat + " &b(You only)"));
        }

        return true;
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.GREEN + ChatColor.stripColor(prefix + "Plugin Disabled"));
    }
}
