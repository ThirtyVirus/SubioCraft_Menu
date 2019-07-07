package thirtyvirus.scm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thirtyvirus.scm.SubioCraftMenu;

//SafeRestart - Kicks all players from game with message "Be Right Back!"
public class SafeRestart implements CommandExecutor {

    //Moderate Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Sent From Console
        if (!(sender instanceof Player)) {
            for (Player p : Bukkit.getOnlinePlayers()){
                p.kickPlayer(SubioCraftMenu.prefix + "The Server is Restarting.\n" + ChatColor.DARK_AQUA + "We will be back online in a minute! :)");
            }
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
        }

        //Sent from player
        Player player = (Player) sender;
        if(player.hasPermission("subiocraft.admin")){

            for (Player p : Bukkit.getOnlinePlayers()){
                p.kickPlayer(SubioCraftMenu.prefix + "The Server is Restarting.\n" + ChatColor.DARK_AQUA + "We will be back online in a minute! :)");
            }
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
        }

        return true;
    }
}