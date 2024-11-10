package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class playerLeaveEvent implements Listener {

    Logic logic;
    public playerLeaveEvent(Logic logic){

        this.logic = logic;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event, Logic logic){

        Player player = event.getPlayer();




    }

}
