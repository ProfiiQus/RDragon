package eu.retrox.dragon.listeners;

import eu.retrox.dragon.ActionParser;
import eu.retrox.dragon.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class DragonDeathListener implements Listener {

    @EventHandler
    public void onEnderDragonDeath(EntityDeathEvent event) {

        if(!event.getEntity().getType().equals(EntityType.ENDER_DRAGON))
            return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        if(!(entity.getKiller() instanceof Player))
            return;

        Player player = entity.getKiller();

        FileConfiguration config = ConfigurationManager.getInstance().getConfig();
        List<String> actionList = (ArrayList<String>) config.get("action-list");

        ActionParser.getInstance().executeActions(actionList, player);
    }
}
