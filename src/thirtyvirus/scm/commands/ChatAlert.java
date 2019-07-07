package thirtyvirus.scm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thirtyvirus.scm.SubioCraftMenu;

public class ChatAlert implements CommandExecutor {

    //ChatAlert Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("subiocraft.moderator")){

                if (args[0].equals("all")){

                    for(Player p : Bukkit.getOnlinePlayers()){
                        String message = "";
                        for (int counter = 1; counter < args.length; counter++){
                            message = message + args[counter] + " ";
                        }
                        SubioCraftMenu.chatAlert(p, message);
                    }

                    SubioCraftMenu.chatAlert(player, "Message Broadcasted!");
                    return true;
                }

                @SuppressWarnings("deprecation")
                Player player2 = Bukkit.getServer().getPlayer(args[0]);

                String message = "";
                for (int counter = 1; counter < args.length; counter++){
                    message = message + args[counter] + " ";
                }

                SubioCraftMenu.chatAlert(player2, message);
                SubioCraftMenu.chatAlert(player, "Message Sent!");
            }

        }
        else{
            if (args[0].equals("all")){

                for(Player p : Bukkit.getOnlinePlayers()){
                    String message = "";
                    for (int counter = 1; counter < args.length; counter++){
                        message = message + args[counter] + " ";
                    }
                    SubioCraftMenu.chatAlert(p, message);
                }
                return true;
            }

            @SuppressWarnings("deprecation")
            Player player2 = Bukkit.getServer().getPlayer(args[0]);

            String message = "";
            for (int counter = 1; counter < args.length; counter++){
                message = message + args[counter] + " ";
            }
            SubioCraftMenu.chatAlert(player2, message);
        }

        return true;
    }
}