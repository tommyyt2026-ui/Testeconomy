package de.tommyyt.testeconomy.placeholders;

import de.tommyyt.testeconomy.TestEconomy;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {
    @Override public @NotNull String getIdentifier() { return "testeconomy"; }
    @Override public @NotNull String getAuthor() { return "TommyYT_"; }
    @Override public @NotNull String getVersion() { return "1.0.0"; }
    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return "";
        if (params.equals("balance")) {
            return String.valueOf((int)TestEconomy.get().vault().get().getBalance(player));
        }
        return null;
    }
}
