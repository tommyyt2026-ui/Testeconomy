package de.tommyyt.testeconomy.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHook {
    private Economy econ;

    public boolean setup() {
        RegisteredServiceProvider<Economy> rsp = TestEconomy.get().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ!= null;
    }

    public Economy get() { return econ; }
}
