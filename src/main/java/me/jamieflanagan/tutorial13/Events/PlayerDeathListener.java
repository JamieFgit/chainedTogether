package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.Items.Rope;
import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerDeathListener implements Listener {

    Logic logic;
    private long lastExecutionTime = 0;  // Stores the last time the event was executed in milliseconds

    public PlayerDeathListener(Logic logic, Plugin plugin) {
        this.logic = logic;
        this.plugin = plugin;
    }

    Plugin plugin;

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


            // Create rope after death and give to the players in the tether group
            Rope rope = new Rope(mainPlayer, logic.getTetherGroup(mainPlayer));
            rope.givePlayerRope(mainPlayer);

        }

        logic.setCanPull(false);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n cant pull" + "\n\n");

        Bukkit.getScheduler().runTaskLater(plugin, () -> canpull(), 200L);
    }

    private void canpull() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n can pull" + "\n\n");
        logic.setCanPull(true);
    }



}

