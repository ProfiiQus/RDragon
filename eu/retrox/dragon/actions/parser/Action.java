package eu.retrox.dragon.actions.parser;

import org.bukkit.entity.Player;

public interface Action {

    void execute(String arg, Player player);
}
