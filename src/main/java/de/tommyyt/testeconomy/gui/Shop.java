package de.tommyyt.testeconomy.gui;

import de.tommyyt.testeconomy.TestEconomy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Shop {
    public static void open(Player p) {
        var cfg = TestEconomy.get().getConfig();
        Inventory inv = Bukkit.createInventory(null, cfg.getInt("shop.size"), cfg.getString("shop.title"));

        ConfigurationSection filler = cfg.getConfigurationSection("shop.filler");
        if (filler!= null) {
            ItemStack fill = new ItemStack(Material.valueOf(filler.getString("material")));
            for (int i = 0; i < inv.getSize(); i++) if (inv.getItem(i) == null) inv.setItem(i, fill);
        }

        ConfigurationSection items = cfg.getConfigurationSection("shop.items");
        for (String key : items.getKeys(false)) {
            ConfigurationSection s = items.getConfigurationSection(key);
            Material m = Material.valueOf(s.getString("material"));
            int slot = s.getInt("slot");
            ItemStack item = new ItemStack(m, s.getInt("amount"));
            inv.setItem(slot, item);
        }
        p.openInventory(inv);
    }
}
