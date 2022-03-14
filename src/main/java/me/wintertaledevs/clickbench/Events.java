package me.wintertaledevs.clickbench;

import me.wintertaledevs.clickbench.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.wintertaledevs.clickbench.ClickBench.plugin;

public class Events implements Listener {

    @EventHandler
    void OnInventoryClick(InventoryClickEvent e) {
        //e.getWhoClicked().sendMessage(e.getClick().toString());
        if(e.getClick() != ClickType.RIGHT) return;
        Player player = (Player)e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        Inventory currentInventory = player.getOpenInventory().getTopInventory();
        if(currentInventory.getType() == InventoryType.WORKBENCH || item == null) return;
        if(item.getType() == Material.CRAFTING_TABLE) {
            if((Utils.GetPlayerData(player, "enabled") != null && (boolean)Utils.GetPlayerData(player, "enabled")) && plugin.config.GetConfig().getBoolean("enabled")) {
                e.setCancelled(true);
                Inventory workbench = Bukkit.createInventory(player, InventoryType.WORKBENCH);
                player.openInventory(workbench);
            }
        }
    }

    @EventHandler
    void OnPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(Utils.GetPlayerData(player, "enabled") == null) {
            Utils.SetPlayerData(player, "enabled", true);
        }
    }
}
