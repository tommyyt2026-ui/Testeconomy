package de.tommyyt.testeconomy.commands;

import de.tommyyt.testeconomy.TestEconomy;
import de.tommyyt.testeconomy.gui.Shop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;
        Shop.open(p);
        return true;
    }
}
