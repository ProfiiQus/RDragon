package eu.retrox.dragon.actions.parser;

import eu.retrox.dragon.actions.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class ActionParser {

    private HashMap<String, Action> availableActions;

    private static ActionParser instance;

    private ActionParser() {
        this.availableActions = new HashMap<String, Action>() {
            {
                put("[broadcast]", new Broadcast());
                put("[player-command]", new PlayerCommand());
                put("[message]", new PlayerMessage());
                put("[server-command]", new ServerCommand());
                put("[sound]", new Sound());
            }
        };
    }

    public static ActionParser getInstance() {
        if(instance == null) instance = new ActionParser();
        return instance;
    }

    public void executeActions(List<String> actions, Player player) {
        for(String s: actions) {
            String[] args = s.split(" ", 2);
            if(availableActions.containsKey(args[0])) {
                availableActions.get(args[0]).execute(args[1], player);
            }
        }
    }
}
