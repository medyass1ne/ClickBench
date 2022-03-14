package me.wintertaledevs.clickbench.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static me.wintertaledevs.clickbench.ClickBench.plugin;

public class Utils {
    public static String GenC(String desc) {
        return ChatColor.translateAlternateColorCodes('&', desc);
    }

    public static String RemoveSpaces(String str) {
        String rawName = ChatColor.stripColor(str).toLowerCase();
        String[] rawNameList = ChatColor.stripColor(str).toLowerCase().split("");rawNameList[rawName.indexOf(" ")+1] = rawNameList[rawName.indexOf(" ")+1].toUpperCase();
        if(rawName.contains(" ")) rawName = String.join("", rawNameList).replace(" ", "");
        return rawName;
    }

    public static Object GetPlayerData(Player player, String key) {
        if(player == null) return null;
        return plugin.data.GetConfig().get(player.getUniqueId()+"."+key);
    }

    public static void SetPlayerData(Player player, String key, Object value) {
        if(player == null) return;
        plugin.data.GetConfig().set(player.getUniqueId()+"."+key, value);
        plugin.data.Save();
    }
}
