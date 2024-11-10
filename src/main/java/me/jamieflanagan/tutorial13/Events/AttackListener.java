package me.jamieflanagan.tutorial13.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;

public class AttackListener implements Listener {

    @EventHandler
    public void handle(EntityDamageByEntityEvent event) {
        // Check if the attacker is a player
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // Check if the player is holding a lead (Material.LEAD)
            if (player.getInventory().getItemInMainHand().getType() == Material.LEAD) {

                event.setCancelled(true);
            }
        }
    }
}
