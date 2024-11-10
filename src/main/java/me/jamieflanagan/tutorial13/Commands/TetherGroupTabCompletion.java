package me.jamieflanagan.tutorial13.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TetherGroupTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("chaingroup")) {

            // If the user is completing the first argument
            if (strings.length == 1) {
                ArrayList<String> list = new ArrayList<>();
                list.add("create");
                list.add("delete");
                list.add("remove");
                list.add("add");
                list.add("clearchains");
                list.add("chain");
                list.add("setpullstrength");
                list.add("settriggerdistance");
                return list;
            }

            // If the user is completing the second argument for setpullstrength or settriggerdistance
            if (strings.length == 2) {

                // Check if the first argument is "setpullstrength" or "settriggerdistance"
                if (strings[0].equalsIgnoreCase("setpullstrength") ||
                        strings[0].equalsIgnoreCase("settriggerdistance")) {

                    ArrayList<String> list = new ArrayList<>();
                    list.add("1");
                    list.add("2");
                    list.add("3");
                    list.add("4");
                    list.add("5");

                    return list;
                }
            }
        }

        return null;
    }
}
