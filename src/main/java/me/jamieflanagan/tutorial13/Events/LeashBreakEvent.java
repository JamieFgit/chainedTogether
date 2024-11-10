package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.Tutorial13;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

import static org.bukkit.Bukkit.getServer;

public class LeashBreakEvent implements Listener {


    public LeashBreakEvent(Tutorial13 tutorial13){
        plugin=tutorial13;
    }
    Plugin plugin;

    @EventHandler
    public void onUnleash(EntityUnleashEvent e){
        if(e.getReason() != EntityUnleashEvent.UnleashReason.DISTANCE) return;
        if(!(e.getEntity() instanceof LivingEntity)) return;
        LivingEntity living = (LivingEntity) e.getEntity();
        Entity holder = living.getLeashHolder();

        getServer().getScheduler().runTask(plugin, task -> {
            Optional<Item> lead = living.getNearbyEntities(15.0, 15.0, 15.0).stream()
                    .filter(entity -> entity instanceof Item)
                    .map(entity -> (Item) entity)
                    .filter(item -> item.getItemStack().getType() == Material.LEAD)
                    .findFirst();

            if(!lead.isPresent()) return;
            lead.get().remove();

            living.teleport(holder);
            living.setLeashHolder(holder);
        });
    }
}
