package eu.retrox.dragon.actions;

import eu.retrox.dragon.Action;
import org.bukkit.entity.Player;

public class PlayerCommand implements Action {

    @Override
    public void execute(String arg, Player player) {
        player.performCommand(arg.replace("{PLAYER}", player.getName()));
    }
}
