package me.jamieflanagan.tutorial13;

import me.jamieflanagan.tutorial13.Commands.TetherGroupCommand;
import me.jamieflanagan.tutorial13.Commands.TetherGroupTabCompletion;
import me.jamieflanagan.tutorial13.Events.*;
import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tutorial13 extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n PLUGIN LOADED \n\n");

        Logic gameLogic = new Logic(this);


        getCommand("chaingroup").setExecutor(new TetherGroupCommand(gameLogic));
        getCommand("chaingroup").setTabCompleter(new TetherGroupTabCompletion());

        getServer().getPluginManager().registerEvents(new MoveEvent(gameLogic),this);
        getServer().getPluginManager().registerEvents(new LeashBreakEvent(this),this);
        getServer().getPluginManager().registerEvents(new EntityDamageEvent(gameLogic),this);
        getServer().getPluginManager().registerEvents(new ItemClickListener(gameLogic),this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(gameLogic,this),this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(gameLogic,this),this);
        getServer().getPluginManager().registerEvents(new AttackListener(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
