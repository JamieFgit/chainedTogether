package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.Items.Rope;
import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    Logic logic;
    public PlayerRespawnListener(Logic logic){
        this.logic = logic;
    }

    @EventHandler
    public void handle(PlayerRespawnEvent event){

        Player mainPlayer = event.getPlayer();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n Player respawned:  " + mainPlayer.getName() + "\n\n");
        for (Player player: logic.getTetherGroup(mainPlayer)){

            player.teleport(mainPlayer.getLocation());
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + player.getName() + "\n\n tp to:  " + mainPlayer.getName() + "\n\n");

        }
        Rope rope = new Rope(mainPlayer,logic.getTetherGroup(mainPlayer));
        rope.givePlayerRope(mainPlayer);
    }



}
