package me.jamieflanagan.tutorial13.logic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class LeadVisuals {

    private final Plugin plugin; // Store plugin reference
    private Player player1;
    private Player player2;
    private int taskID;

    public LeadVisuals(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startVisualLeadEffect(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        startVisualLead();
    }

    private void startVisualLead() {
        taskID = new BukkitRunnable() {
            @Override
            public void run() {
                if (player1 == null || player2 == null || !player1.isOnline() || !player2.isOnline()) {
                    this.cancel();
                    return;
                }

                // Get locations of both players' heads (0.5 above their feet), lowered by 1 block
                Location player1Loc = player1.getLocation().add(0, 0.5, 0);
                Location player2Loc = player2.getLocation().add(0, 0.5, 0);

                // Calculate direction vector and distance
                Vector direction = player2Loc.toVector().subtract(player1Loc.toVector()).normalize();
                double distance = player1Loc.distance(player2Loc);

                // Draw particles along the line
                double spacing = 0.3;  // Distance between each particle
                Location particleLocation = player1Loc.clone(); // Start from player1 location

                for (double d = 0; d < distance; d += spacing) {
                    particleLocation.add(direction.clone().multiply(spacing));

                    // Spawn stationary HEART particles, with no spread (0,0,0) and minimal size (0.01)
                    player1Loc.getWorld().spawnParticle(Particle.CRIT, particleLocation, 1, 0, 0, 0, 0.01);
                }
            }
        }.runTaskTimer(plugin, 0L, 2L).getTaskId(); // Use the plugin instance
    }

    public void stopVisualLead() {
        // Cancels the task to stop the visual effect
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
