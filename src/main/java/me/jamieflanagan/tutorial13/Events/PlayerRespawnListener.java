package me.jamieflanagan.tutorial13.Events;

import me.jamieflanagan.tutorial13.Items.Rope;
import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class PlayerRespawnListener implements Listener {

    Logic logic;

    Plugin plugin;
    public PlayerRespawnListener(Logic logic, Plugin plugin){
        this.logic = logic;
        this.plugin = plugin;
    }



    @EventHandler
    public void handle(PlayerRespawnEvent event){




        Player mainPlayer = event.getPlayer();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n Player respawned: " + mainPlayer.getName() + "\n\n");
        for (Player player: logic.getTetherGroup(mainPlayer)){

            Bukkit.getScheduler().runTaskLater(plugin, () -> player.teleport(mainPlayer.getLocation()), 100L);

            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"\n\n" + player.getName() + " teleporting to:  " + mainPlayer.getName() + "\n\n");

        }
        Rope rope = new Rope(mainPlayer,logic.getTetherGroup(mainPlayer));
        rope.givePlayerRope(mainPlayer);

        for (Player player : logic.getTetherGroup(mainPlayer)) {

            String string5 = ChatColor.GREEN + "TP in 5";
            String string4 = ChatColor.GREEN +"TP in 4";
            String string3 = ChatColor.YELLOW +"TP in 3";
            String string2 = ChatColor.GOLD +"TP in 2";
            String string1 = ChatColor.RED +"TP in 1";

            Bukkit.getScheduler().runTaskLater(plugin, () -> showTitle(string5,player), 0L);
            Bukkit.getScheduler().runTaskLater(plugin, () ->showTitle(string4,player) , 20L);
            Bukkit.getScheduler().runTaskLater(plugin, () ->showTitle(string3,player) , 40L);
            Bukkit.getScheduler().runTaskLater(plugin, () ->showTitle(string2,player) , 60L);
            Bukkit.getScheduler().runTaskLater(plugin, () ->showTitle(string1,player) , 80L);
            Bukkit.getScheduler().runTaskLater(plugin, () ->showTitle("",player) , 100L);
        }

    }

    private void showTitle(String string, Player player){
        player.sendTitle(string,"",0,40,0);

    }



}
