package me.jamieflanagan.tutorial13.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static javax.sql.rowset.spi.SyncFactory.getLogger;

public class TeamManager {


    public void createTeam(String teamName) {
        // Get the Scoreboard Manager
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) {

            return;
        }

        Scoreboard scoreboard = manager.getMainScoreboard();

        // Create the team if it doesn't exist
        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);

        }

        // Set collision rule to not allow teammates to collide
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);



    }


    public void addPlayerToTeam(Player player, String teamName) {
        // Get the Scoreboard Manager
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        // Get the main scoreboard
        Scoreboard scoreboard = manager.getMainScoreboard();

        // Get the team
        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            team.addEntry(player.getName());
            player.setScoreboard(scoreboard); // Update player's scoreboard to reflect the team

        }
    }


    public void addEntityToTeam(Entity entity, String teamName) {
        // Get the Scoreboard Manager
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        // Get the main scoreboard
        Scoreboard scoreboard = manager.getMainScoreboard();

        // Get the team
        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            team.addEntry(entity.getUniqueId().toString()); // Use UUID for entities

        }
    }


}
