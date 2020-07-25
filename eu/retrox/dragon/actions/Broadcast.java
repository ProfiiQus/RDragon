package eu.retrox.dragon.actions;

import eu.retrox.dragon.Action;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Broadcast implements Action {

    @Override
    public void execute(String arg, Player player) {
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', arg.replace("{PLAYER}", player.getName())));
    }
}
