package de.tommyyt.testeconomy.commands;

import de.tommyyt.testeconomy.TestEconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;
        if (args.length!= 2) {
            p.sendMessage("§c/pay <spieler> <betrag>");
            return true;
        }
        Player t = Bukkit.getPlayer(args[0]);
        if (t == null) {
            p.sendMessage("§cSpieler nicht gefunden");
            return true;
        }
        double amount;
        try { amount = Double.parseDouble(args[1]); } catch (Exception e) { p.sendMessage("§cZahl eingeben"); return true; }
        if (!TestEconomy.get().vault().get().has(p, amount)) { p.sendMessage("§cKein Geld"); return true; }
        TestEconomy.get().vault().get().withdrawPlayer(p, amount);
        TestEconomy.get().vault().get().depositPlayer(t, amount);
        p.sendMessage("§a" + amount + " an " + t.getName());
        t.sendMessage("§a" + amount + " von " + p.getName());
        return true;
    }
}
