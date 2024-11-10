package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    Logic logic;
    public MoveEvent(Logic logic){

        this.logic = logic;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();



        if (logic.getStands().containsKey(player)) {
            Entity entity = logic.getStands().get(player);
            entity.teleport(player.getLocation().add(0, 0.5, 0));
            entity.setVelocity(player.getVelocity());
            logic.pushPlayerTowardsTiedPlayers(player);
        }




    }

}