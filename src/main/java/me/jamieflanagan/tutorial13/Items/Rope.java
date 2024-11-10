package me.jamieflanagan.tutorial13.Items;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Rope {
    int currentTiedPlayer = 0;
    ItemStack rope;
    ItemMeta ropeMeta;
    ArrayList<Player> tiedPlayers;
    Player player;

    public Rope(Player player, ArrayList<Player> tiedPlayers) {

        this.player = player;

        this.tiedPlayers = tiedPlayers;

        rope = new ItemStack(Material.LEAD, 1);
        ropeMeta = rope.getItemMeta();

        ArrayList ropeLore = new ArrayList();
        ropeLore.add(ChatColor.YELLOW + "Left click to pull Player");
        ropeLore.add(ChatColor.YELLOW + "Right click to switch Player");

        ropeMeta.setLore(ropeLore);
        ropeMeta.setEnchantmentGlintOverride(true);
        ropeMeta.setItemName("rope");

        updateDisplayName();

        rope.setItemMeta(ropeMeta);

    }

    public void changeTargetPlayer() {
        currentTiedPlayer = (currentTiedPlayer + 1) % (tiedPlayers.size());
        updateDisplayName();

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n changing target player to :  "
                + tiedPlayers.get(currentTiedPlayer).getName() + "\n\n");
    }

    private void updateDisplayName() {
        String displayName = ChatColor.AQUA + "Current target: " + ChatColor.RED + tiedPlayers.get(currentTiedPlayer).getName();
        ropeMeta.setDisplayName(displayName);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(displayName));
    }

    public void givePlayerRope(Player player) {
        player.getInventory().addItem(rope);
    }

    double pullStrength = 0.35;

    private boolean canPull = true; // Track if pulling is allowed
    private final long pullCooldown = 20L; // 40 ticks (2 seconds)

    public void pullTargetPlayer() {

        if (canPull) {

            canPull = false;

            Player player2 = tiedPlayers.get(currentTiedPlayer);

            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n" +player.getName() + " Pulling player: " + player2  + "\n\n");

            double separation = player2.getLocation().distance(player.getLocation());

            Vector player1Location = player.getLocation().toVector();
            Vector player2Location = player2.getLocation().toVector();

            // Calculate direction from player2 to player1
            Vector direction = player1Location.subtract(player2Location).normalize();

            // Player 2 is pulled towards Player 1
            Vector velocity2 = direction.multiply(pullStrength * separation);
            player2.setVelocity(velocity2);

            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("tutorial13"), () -> {
                canPull = true;
            }, pullCooldown);

        }
        else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n Cant pull yet"  + "\n\n");
        }
    }


}
