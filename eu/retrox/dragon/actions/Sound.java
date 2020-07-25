package eu.retrox.dragon.actions;

import eu.retrox.dragon.Action;
import org.bukkit.entity.Player;

public class Sound implements Action {

    @Override
    public void execute(String arg, Player player) {
        player.getWorld().playSound(player.getLocation(), org.bukkit.Sound.valueOf(arg), 3.0F, 0.5F);
    }
}
