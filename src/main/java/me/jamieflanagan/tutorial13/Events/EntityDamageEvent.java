package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageEvent implements Listener {

    Logic logic;
    public EntityDamageEvent(Logic logic){
        this.logic = logic;

    }


    @EventHandler

    public void handle(org.bukkit.event.entity.EntityDamageEvent event){

        }




}
