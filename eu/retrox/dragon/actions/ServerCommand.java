package eu.retrox.dragon.actions;

import eu.retrox.dragon.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerCommand implements Action {

    @Override
    public void execute(String arg, Player player) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), arg.replace("{PLAYER}", player.getName()));
    }
}
