package me.jamieflanagan.tutorial13.Commands;

import me.jamieflanagan.tutorial13.logic.Logic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class TetherGroupCommand implements CommandExecutor {


    public TetherGroupCommand(Logic logic) {
        this.logic = logic;
    }

    Logic logic;


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player sender) {


            if (command.getName().equalsIgnoreCase("chaingroup")) {

                if (strings.length == 1) {

                    String arg1 = strings[0];

                    if (arg1.equals("create")) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n create command recognized \n\n");

                        logic.createTetherGroup(sender);

                    }

                    if (arg1.equals("delete")) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n delete command recognized \n\n");

                        logic.deleteTetherGroup(sender);
                    }

                    if (arg1.equals("clearchains")) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n clearchains command recognized \n\n");

                        logic.clearTethers(logic.getGroup(sender));
                    }
                    if (arg1.equals("chain")) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n chain command recognized \n\n");

                        logic.tetherGroupTogether(logic.getGroup(sender));
                    }


                }

                if(strings.length== 2) {

                    String arg1 = strings[0];
                    String arg2 = strings[1];

                    if (arg1.equals("add")) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n add command recognized \n\n");

                        Player player = Bukkit.getServer().getPlayerExact(arg2);
                        if (player != null) {
                            logic.addPlayerToGroup(player, logic.getGroup(sender));
                        }
                    }

                    if (arg1.equals("remove")) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n remove command recognized \n\n");

                        Player player = Bukkit.getServer().getPlayerExact(arg2);
                        if (player != null) {
                            logic.removePlayerFromGroup(player, logic.getGroup(sender));
                        }
                    }

                    if (arg1.equalsIgnoreCase("setpullstrength")) {

                        if (strings.length == 2) {


                            try {
                                double pullStrength = Double.parseDouble(arg2); // Use Double or Integer based on your logic

                                // Only set pull strength if arg2 is a number
                                logic.setPullStrength(pullStrength);


                            } catch (NumberFormatException e) {
                                // Handle the case where arg2 is not a valid number
                                sender.sendMessage("Error: Pull strength must be a valid number.");
                            }
                        }

                    }

                    if (arg1.equalsIgnoreCase("settriggerdistance")) {

                        if (strings.length == 2) {


                            try {
                                double triggerDistance = Double.parseDouble(arg2); // Use Double or Integer based on your logic

                                // Only set pull strength if arg2 is a number
                                logic.setTriggerDistance(triggerDistance);


                            } catch (NumberFormatException e) {
                                // Handle the case where arg2 is not a valid number
                                sender.sendMessage("Error: Trigger distance must be a valid number.");
                            }
                        }

                    }


                }



            }
        }
        return false;
    }

}


