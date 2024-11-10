package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.Items.Rope;
import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;

public class ItemClickListener implements Listener {

    Logic logic;

    public ItemClickListener(Logic logic) {
        this.logic = logic;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getItemMeta().getItemName().equals("rope")) {

            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                logic.getRopes().get(player).pullTargetPlayer();
            }

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                logic.getRopes().get(player).changeTargetPlayer();
            }
        }


    }

}
