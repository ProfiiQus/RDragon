package eu.retrox.dragon.commands;

import eu.retrox.dragon.ConfigurationManager;
import eu.retrox.dragon.EntityManager;
import eu.retrox.dragon.RDragon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class RDragonCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration config = ConfigurationManager.getInstance().getConfig();
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("rdragon.spawn")) {
                sendMessage(player, ChatColor.translateAlternateColorCodes('&', config.getString("messages.not-enough-permissions")));
                return false;
            }
        }

        World world = Bukkit.getWorld(config.getString("world"));
        if(world == null) {
            sendMessage(commandSender, ChatColor.translateAlternateColorCodes('&', config.getString("messages.world-not-found")));
            return false;
        }

        List<Entity> entities = world.getEntities();
        boolean wasSpawned = false;
        for(Entity e: entities) {
            if(e.getType().equals(EntityType.ENDER_DRAGON)) {
                wasSpawned = true;
                if(e.getLocation().getChunk().isLoaded()) {
                    e.remove();
                } else {
                    Set<UUID> toKill = EntityManager.getInstance().getToKill();
                    toKill.add(e.getUniqueId());
                }
                Location loc = getLocation(world, config);
                spawnDragon(loc);
            }
        }
        sendMessage(commandSender, ChatColor.translateAlternateColorCodes('&', config.getString("messages.successfully-killed")));
        if(!wasSpawned) {
            Location loc = getLocation(world, config);
            spawnDragon(loc);
        }

        return false;
    }

    private void spawnDragon(Location location) {
        Entity entity = location.getWorld().spawnEntity(location, EntityType.ENDER_DRAGON);
        EnderDragon dragon = (EnderDragon) entity;
        new BukkitRunnable() {

            @Override
            public void run() {
                dragon.setPhase(EnderDragon.Phase.LEAVE_PORTAL);
            }
        }.runTaskLater(RDragon.getPlugin(), 10);
    }

    private Location getLocation(World world, FileConfiguration config) {
        return new Location(world, config.getDouble("spawn-location.x"), config.getDouble("spawn-location.y"), config.getDouble("spawn-location.z"));
    }

    private void sendMessage(CommandSender sender, String message) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(message);
        } else {
            sender.sendMessage(ChatColor.stripColor(message));
        }
    }
}
