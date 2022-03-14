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

    public static String GetFromLocale(String key) {
        return plugin.locale.GetConfig().getString(plugin.config.GetConfig().getString("language")+"."+key);
    }

    public static void SetDefaultLocale(FileManager locale) {
        //English
        String l = "english";
        locale.GetConfig().addDefault(l + ".cbnoargs", "&6Developed by &eYessify\n&6Usage: \n&e/clickbench toggle -> Toggle the plugin ON/OFF for you only\n/clickbench toggle g -> Toggle the plugin ON/OFF globally\n/clickbench reload -> Reload the plugin files");
        locale.GetConfig().addDefault(l + ".onlyplayer", "&cThis command can only be used by players.");
        locale.GetConfig().addDefault(l + ".globaltoggleon", "&6All plugin features are now &aenabled &b(Globally)");
        locale.GetConfig().addDefault(l + ".globaltoggleoff", "&6All plugin features are now &cdisabled &b(Globally)");
        locale.GetConfig().addDefault(l + ".selftoggleon", "&6All plugin features are now &aenabled &b(You only)");
        locale.GetConfig().addDefault(l + ".selftoggleoff", "&6All plugin features are now &cdisabled &b(You only)");
        locale.GetConfig().addDefault(l + ".needpermission", "&cYou need the permission \"{permission}\" to use this command.");
        locale.GetConfig().addDefault(l + ".pluginreload", "&6All plugin files has been reloaded.");
        //French
        l = "french";
        locale.GetConfig().addDefault(l + ".cbnoargs", "&6Développé par &eYessify\n&6Usage: \n&e/clickbench toggle -> Basculez le plugin ON / OFF pour vous uniquement\n/clickbench toggle g -> Basculer le plugin ON / OFF globalement\n/clickbench reload -> Recharger les fichiers du plugin");
        locale.GetConfig().addDefault(l + ".onlyplayer", "&cCette commande ne peut être utilisée que par les joueurs.");
        locale.GetConfig().addDefault(l + ".globaltoggleon", "&6Toutes les fonctionnalités du plugin sont maintenant &aactivées &b(Globalement)");
        locale.GetConfig().addDefault(l + ".globaltoggleoff", "&6Toutes les fonctionnalités du plugin sont maintenant désactivées &b(Globalement)");
        locale.GetConfig().addDefault(l + ".selftoggleon", "&6Toutes les fonctionnalités du plugin sont maintenant &aactivées &b(vous uniquement)");
        locale.GetConfig().addDefault(l + ".selftoggleoff", "&6Toutes les fonctionnalités du plug-in sont maintenant &cdésactivées &b(vous uniquement)");
        locale.GetConfig().addDefault(l + ".needpermission", "&cVous avez besoin de la permission \"{permission}\" pour utiliser cette commande.");
        locale.GetConfig().addDefault(l + ".pluginreload", "&6Tous les fichiers du plugin ont été rechargés.");
        //Spanish
        l = "spanish";
        locale.GetConfig().addDefault(l + ".cbnoargs", "&6Desarrollado por Yessify\n&6Uso: \n&e/clickbench toggle -> Active o desactive el complemento solo para usted\n/clickbench toggle g -> Activa o desactiva el complemento globalmente\n/clickbench reload -> Vuelva a cargar los archivos del complemento");
        locale.GetConfig().addDefault(l + ".onlyplayer", "&cEste comando solo puede ser utilizado por los jugadores.");
        locale.GetConfig().addDefault(l + ".globaltoggleon", "&6Todas las funciones del complemento ahora están &ahabilitadas &b(a nivel mundial)");
        locale.GetConfig().addDefault(l + ".globaltoggleoff", "&6Todas las funciones del complemento ahora están &cdeshabilitadas &b(globalmente)");
        locale.GetConfig().addDefault(l + ".selftoggleon", "&6Todas las funciones del complemento ahora están &ahabilitadas &b(solo usted)");
        locale.GetConfig().addDefault(l + ".selftoggleoff", "&6Todas las funciones del complemento ahora están &cdeshabilitadas &b(solo usted)");
        locale.GetConfig().addDefault(l + ".needpermission", "&cNecesita el permiso \"{permission}\" para usar este comando.");
        locale.GetConfig().addDefault(l + ".pluginreload", "&6Todos los archivos del complemento se han recargado.");
    }
}
