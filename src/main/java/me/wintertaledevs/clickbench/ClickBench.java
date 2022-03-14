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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ClickBench extends JavaPlugin implements Listener, CommandExecutor {

    public static ClickBench plugin;
    public String prefix = "&e[&6ClickBench&e] ";
    public FileManager config;
    public FileManager data;
    public FileManager locale;

    @Override
    public void onEnable() {
        plugin = this;

        config = new FileManager(getDataFolder(), "config.yml");
        data = new FileManager(getDataFolder(), "data.yml");
        locale = new FileManager(getDataFolder(), "locale.yml");

        config.GetConfig().addDefault("prefix", "&e[&6ClickBench&e] ");
        config.GetConfig().addDefault("language", "english");
        config.GetConfig().addDefault("enabled", true);
        Utils.SetDefaultLocale(locale);
        config.Save();
        data.Save();
        locale.Save();
        prefix = config.GetConfig().getString("prefix");


        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("clickbench").setExecutor(this);

        System.out.println(ChatColor.GREEN + ChatColor.stripColor(prefix + "Plugin Enabled"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Utils.GenC(prefix + Utils.GetFromLocale("cbnoargs")));
            return true;
        }
        String cmd = args[0];
        if(cmd.equalsIgnoreCase("toggle")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Utils.GenC(prefix + Utils.GetFromLocale("onlyplayer")));
                return true;
            }
            Player player = (Player) sender;
            if(args.length >= 2) {
                boolean g = (args[1].equalsIgnoreCase("global") || args[1].equalsIgnoreCase("globally") || args[1].equalsIgnoreCase("g"));
                if (g && player.hasPermission("cb.globaltoggle")) {
                    boolean newstate = !((boolean)config.GetConfig().get("enabled"));
                    config.GetConfig().set("enabled", newstate);
                    String state = Utils.GetFromLocale("globaltoggleon");
                    if (!newstate) state = Utils.GetFromLocale("globaltoggleoff");
                    player.sendMessage(Utils.GenC(prefix + state));
                    return true;
                } else if(!player.hasPermission("cb.globaltoggle")) {
                    player.sendMessage(Utils.GenC(prefix + Utils.GetFromLocale("needpermission").replaceAll("\\{permission}", "cb.globaltoggle")));
                    return true;
                }
            }
            boolean newstate = true;
            if(Utils.GetPlayerData(player, "enabled") != null) newstate = !((boolean)Utils.GetPlayerData(player, "enabled"));
            Utils.SetPlayerData(player, "enabled", newstate);
            String state = Utils.GetFromLocale("selftoggleon");
            if(!newstate) state = Utils.GetFromLocale("selftoggleoff");
            player.sendMessage(Utils.GenC(prefix + state));
        } else if(cmd.equalsIgnoreCase("reload")) {
            if(sender instanceof Player && !sender.hasPermission("cb.reload")) {
                sender.sendMessage(Utils.GenC(prefix + Utils.GetFromLocale("needpermission").replaceAll("\\{permission}", "cb.reload")));
                return true;
            }
            config.Reload();
            data.Reload();
            locale.Reload();
            sender.sendMessage(Utils.GenC(prefix + Utils.GetFromLocale("pluginreload")));
        }

        return true;
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.GREEN + ChatColor.stripColor(prefix + "Plugin Disabled"));
    }
}
