package de.tommyyt.testeconomy.listener;

import de.tommyyt.testeconomy.TestEconomy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (!e.getView().getTitle().equals("Shop")) return;
        e.setCancelled(true);

        var cfg = TestEconomy.get().getConfig();
        var items = cfg.getConfigurationSection("shop.items");
        for (String key : items.getKeys(false)) {
            var s = items.getConfigurationSection(key);
            if (s.getInt("slot")!= e.getSlot()) continue;
            Material m = Material.valueOf(s.getString("material"));
            int amount = s.getInt("amount");
            double buy = s.getDouble("buy");
            double sell = s.getDouble("sell");

            if (e.isLeftClick()) {
                if (!TestEconomy.get().vault().get().has(p, buy)) { p.sendMessage("§cKein Geld"); return; }
                TestEconomy.get().vault().get().withdrawPlayer(p, buy);
                p.getInventory().addItem(new ItemStack(m, amount));
                p.sendMessage("§aGekauft");
            } else if (e.isRightClick()) {
                if (!p.getInventory().containsAtLeast(new ItemStack(m), amount)) { p.sendMessage("§cKeine Items"); return; }
                p.getInventory().removeItem(new ItemStack(m, amount));
                TestEconomy.get().vault().get().depositPlayer(p, sell);
                p.sendMessage("§aVerkauft");
            }
        }
    }
}
