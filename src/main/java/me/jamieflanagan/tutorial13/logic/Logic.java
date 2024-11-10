package me.jamieflanagan.tutorial13.logic;

import me.jamieflanagan.tutorial13.Items.Rope;
import me.jamieflanagan.tutorial13.Teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.swing.plaf.SeparatorUI;
import java.util.ArrayList;
import java.util.HashMap;


public class Logic {

    Plugin plugin;

    public Logic(Plugin plugin) {

        this.plugin = plugin;

        triggerDistance = 5;

        pullStrength = 0.01;

    }

    TeamManager teamManager = new TeamManager();

    HashMap<Player, ArmorStand> stands = new HashMap<>();


    HashMap<Player, ArrayList<Player>> tiedPlayers = new HashMap<>();

    public  HashMap<Player, ArrayList<Player>> getTiedPlayers(){
        return tiedPlayers;
    }

    public HashMap<Player, ArmorStand> getStands() {
        return stands;
    }

    public void tetherPlayers(Player player1, Player player2) {

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n tetherplayers: " + player1.getName() + " " + player2.getName() + "\n\n");

        spawnStandOnPlayer(player1);
        spawnStandOnPlayer(player2);

        addTiedPlayers(player1,player2);
        addTiedPlayers(player2,player1);

        LeadVisuals leadVisuals = new LeadVisuals(plugin);
        leadVisuals.startVisualLeadEffect(player1,player2);


    }

    public Boolean playerHasStandAlready(Player player) {
        if (stands.containsKey(player)) {
            return true;
        }

        return false;

    }

    public void spawnStandOnPlayer(Player player) {

        if (!playerHasStandAlready(player)) {

            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

            PotionEffect invisibility = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, true, false);
            PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 255, true, false);
            PotionEffect absorption = new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 255, true, false);

            stand.addPotionEffect(invisibility);
            stand.addPotionEffect(regeneration);
            stand.addPotionEffect(absorption);

            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setSilent(true);
            //stand.setSize(0);
            stand.setAI(false);
            stand.setMarker(true);


            teamManager.createTeam(player.getName());
            //teamManager.addPlayerToTeam(player, player.getName());
            //teamManager.addEntityToTeam(stand, player.getName());

            stand.setInvisible(true);

            stands.put(player, stand);

        }
    }

    public boolean canCreateTetherGroup(Player player) {


        for (Object object : tetherGroups.keySet()) {
            if (object instanceof Player host) {

                if (host.equals(player)) {

                    return false;


                }
            }
        }

        return true;
    }

    public void createTetherGroup(Player player) {

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n calling createTetherGroup \n\n");


        if (canCreateTetherGroup(player)) {

            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n can create tether group \n\n");

            ArrayList<Player> tetherGroup = new ArrayList<Player>();
            tetherGroup.add(player);

            tetherGroups.put(player, tetherGroup);
            updateGroupGUI(tetherGroup);

            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n group created " + tetherGroup.toString() + "\n\n");

        }

    }

    public void deleteTetherGroup(Player player) {

        ArrayList<Player> group = tetherGroups.get(player);

        if (tetherGroups.containsKey(player)) {
            tetherGroups.remove(player);
            updateGroupGUI(group);
        }


    }

    HashMap<Player, ArrayList<Player>> tetherGroups = new HashMap<>();

    public ArrayList<Player> getGroup(Player host) {

        return tetherGroups.get(host);

    }

    public void addPlayerToGroup(Player player, ArrayList<Player> group) {

        group.add(player);

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n group status: " + group.toString() + "\n\n");

        updateGroupGUI(group);

    }

    public void removePlayerFromGroup(Player player, ArrayList<Player> group) {

        group.remove(player);

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n group status: " + group.toString() + "\n\n");

        clearGUI(player);

    }

    public void createGUI(Player player, ArrayList<Player> group) {


    }


    public void clearGUI(Player player) {


    }

    public void updateGroupGUI(ArrayList<Player> group) {

        for (Player player : group) {

            createGUI(player, group);

        }

    }

    public void tetherGroupTogether(ArrayList<Player> group) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n tethergrouptogether: " + group.toString() + "\n\n");

        // Loop through the group, tether each player to the previous and next one in the list
        for (int i = 0; i < group.size(); i++) {
            Player current = group.get(i);
            // If this is not the first player, tether to the previous one
            if (i > 0) {
                Player previous = group.get(i - 1);
                tetherPlayers(previous, current); // Tether current to previous
            }

            // If this is not the last player, tether to the next one
            if (i < group.size() - 1) {
                Player next = group.get(i + 1);
                tetherPlayers(current, next); // Tether current to next
            }

            givePlayerRope(current);
        }
    }


    public void clearTethers(ArrayList<Player> group) {



    }
    public double getTriggerDistance(){
        return triggerDistance;
    }
    public  double getPullStrength(){
        return pullStrength;
    }

    public void setPullStrength(double d) {

        if (d > 0 && d < 5) {

            pullStrength = d / 100*triggerDistance;
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n setting pull strength " + String.valueOf(d) + "\n\n");
        }
    }

    double pullStrength;

    public  void setTriggerDistance(double d) {

        if (d > 0 && d < 10) {
            triggerDistance = d * 2.5;
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n setting trigger distance " + String.valueOf(d) + "\n\n");

        }
    }



    double triggerDistance;

    public void pullPlayers(Player player1, Player player2) {

        double separation = player2.getLocation().distance(player1.getLocation());

        Vector player1Location = player1.getLocation().toVector();
        Vector player2Location = player2.getLocation().toVector();

        if (separation > triggerDistance) {
            // Calculate direction from player1 to player2
            Vector direction = player2Location.subtract(player1Location).normalize();

            // Player 1 is pulled towards Player 2
            Vector velocity1 = direction.multiply(pullStrength * separation*separation);
            player1.setVelocity(velocity1);

            // Player 2 is pulled towards Player 1 (reverse direction)
            Vector velocity2 = direction.multiply(-pullStrength * separation* separation); // Reverse direction
            player2.setVelocity(velocity2);
        }
    }



    public void addTiedPlayers(Player mainPlayer, Player ropedPlayer1, Player ropedPlayer2) {

        if (tiedPlayers.get(mainPlayer) != null) {

            tiedPlayers.get(mainPlayer).add(ropedPlayer1);
            tiedPlayers.get(mainPlayer).add(ropedPlayer2);
        } else {

            ArrayList<Player> players = new ArrayList<>();
            players.add(ropedPlayer1);
            players.add(ropedPlayer2);

            tiedPlayers.put(mainPlayer, players);
        }

    }

    public void addTiedPlayers(Player mainPlayer, Player ropedPlayer1) {

        if (tiedPlayers.get(mainPlayer) != null) {

            tiedPlayers.get(mainPlayer).add(ropedPlayer1);
        } else {

            ArrayList<Player> players = new ArrayList<>();
            players.add(ropedPlayer1);

            tiedPlayers.put(mainPlayer, players);
        }

    }

    public void pushPlayerTowardsTiedPlayers(Player player1){

        for (Player player2: tiedPlayers.get(player1)){

            pullPlayers(player1,player2);

        }

    }

    public void givePlayerRope(Player player){
        Rope rope = new Rope(player,getTiedPlayers().get(player));
        rope.givePlayerRope(player);
        ropes.put(player,rope);
    }

    HashMap<Player,Rope> ropes = new HashMap<>();

    public HashMap<Player,Rope> getRopes(){
        return  ropes;
    }

    public ArrayList<Player> getTetherGroup(Player anyPlayer){

        for (Player player : tetherGroups.keySet()){
            if (tetherGroups.get(player).contains(anyPlayer)){
                return tetherGroups.get(player);
            }

        }
        return null;
    }

}


