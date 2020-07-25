package eu.retrox.dragon.actions;

import eu.retrox.dragon.Action;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerMessage implements Action {

    @Override
    public void execute(String arg, Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', arg.replace("{PLAYER}", player.getName())));
    }
}
