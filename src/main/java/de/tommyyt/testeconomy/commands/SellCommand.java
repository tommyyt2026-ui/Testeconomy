package de.tommyyt.testeconomy.commands;

import de.tommyyt.testeconomy.TestEconomy;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) { p.sendMessage("§cItem in Hand"); return true; }
        double price = TestEconomy.get().getConfig().getDouble("sell-prices." + item.getType().name());
        if (price <= 0) { p.sendMessage("§cNicht verkaufbar"); return true; }
        int amount = args.length > 0 && args[0].equals("all")? item.getAmount() : 1;
        item.setAmount(item.getAmount() - amount);
        TestEconomy.get().vault().get().depositPlayer(p, price * amount);
        p.sendMessage("§aVerkauft für " + (price * amount));
        return true;
    }
}
