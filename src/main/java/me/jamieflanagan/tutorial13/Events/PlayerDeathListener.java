package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.Items.Rope;
import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    Logic logic;
    private long lastExecutionTime = 0;  // Stores the last time the event was executed in milliseconds

    public PlayerDeathListener(Logic logic) {
        this.logic = logic;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        long currentTime = System.currentTimeMillis();  // Get the current time in milliseconds

        // Check if 10 seconds have passed since the last execution
        if (currentTime - lastExecutionTime >= 10000) {  // 10000 ms = 10 seconds
            // Update the last execution time
            lastExecutionTime = currentTime;

            Player mainPlayer = event.getEntity();

            // Log the player death event
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + mainPlayer.getName() + " has died!");

            // Handle players in the tether group
            for (Player player : logic.getTetherGroup(mainPlayer)) {

                logic.getStands().get(player).teleport(player.getBedSpawnLocation());
                logic.getStands().get(player).teleport(player.getBedSpawnLocation());

                // Optional: Add logic for players in the tether group (e.g., teleport, health, etc.)
                // For now, let's just log and set health to 0
                player.setHealth(0);
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + player.getName() + " has been set to 0 health due to death of " + mainPlayer.getName());
            }

            // Create rope after death and give to the players in the tether group
            Rope rope = new Rope(mainPlayer, logic.getTetherGroup(mainPlayer));
            rope.givePlayerRope(mainPlayer);

            // Optional: You can broadcast a message to everyone
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.RED + mainPlayer.getName() + " has died, and their tether group has been affected!");
            }
        } else {
            // Log if the event is triggered too soon
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Player death event triggered too soon. Please wait a few seconds.");
        }
    }
}
