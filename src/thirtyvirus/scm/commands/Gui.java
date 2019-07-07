package thirtyvirus.scm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thirtyvirus.scm.MenuLoader;

//GUI - Handles construction of menus
public class Gui implements CommandExecutor {

    // GUI Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        player.openInventory(MenuLoader.createMainGUI(player));
        return true;
    }
}