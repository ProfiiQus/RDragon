package eu.retrox.dragon.listeners;

import eu.retrox.dragon.EntityManager;
import eu.retrox.dragon.RDragon;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;
import java.util.UUID;

public class ChunkListener implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {

        new BukkitRunnable() {
            @Override
            public void run() {
                Set<UUID> toKill = EntityManager.getInstance().getToKill();
                for(Entity e: event.getChunk().getEntities()) {
                    if(toKill.contains(e.getUniqueId())) {
                        e.remove();
                    }
                }
            }
        }.runTaskLater(RDragon.getPlugin(), 1);
    }

}
