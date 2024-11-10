package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class SpawnEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerSpawnLocationEvent event, Logic logic) {

        Player player = event.getPlayer();


    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

    }

}

