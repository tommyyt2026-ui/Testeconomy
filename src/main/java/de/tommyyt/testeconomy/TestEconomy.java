package de.tommyyt.testeconomy;

import de.tommyyt.testeconomy.commands.PayCommand;
import de.tommyyt.testeconomy.commands.SellCommand;
import de.tommyyt.testeconomy.commands.ShopCommand;
import de.tommyyt.testeconomy.economy.VaultHook;
import de.tommyyt.testeconomy.listener.ShopListener;
import de.tommyyt.testeconomy.placeholders.Placeholders;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestEconomy extends JavaPlugin {
    private static TestEconomy instance;
    private VaultHook vault;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        if (!getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().severe("Vault fehlt");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        vault = new VaultHook();
        if (!vault.setup()) {
            getLogger().severe("Kein Economy Plugin");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("pay").setExecutor(new PayCommand());
        getCommand("sell").setExecutor(new SellCommand());
        getCommand("shop").setExecutor(new ShopCommand());
        getServer().getPluginManager().registerEvents(new ShopListener(), this);

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholders().register();
        }
    }

    public static TestEconomy get() { return instance; }
    public VaultHook vault() { return vault; }
}
