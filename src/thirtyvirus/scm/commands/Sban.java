package thirtyvirus.scm.commands;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thirtyvirus.scm.SubioCraftMenu;


//GUI - Handles construction of menus
public class Sban implements CommandExecutor {

    //sban Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Console
        if (!(sender instanceof Player)) {
            sbann(args[0], args);
        }

        Player player = (Player) sender;
        if (player.hasPermission("subiocraft.admin")){
            if (args.length == 0) {
                SubioCraftMenu.chatAlert(player, "Sban Infractions: chat, annoying, griefing, bullying, doxing, hacking, ban_evasion, chargeback");
                return true;
            }
            sbann(args[0], args);
        }
        else{
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            SubioCraftMenu.chatAlert(player, "&cYou do not have proper permissions!");
        }



        return true;
    }

    public void sbann(String player, String[] infractions){

        int minutesMuted = 0;
        int daysBanned = 0;
        boolean permBan = false;

        String reasons = "";
        for (int counter = 1; counter < infractions.length; counter++){
            reasons = reasons + infractions[counter] + ", ";
        }
        reasons = reasons.substring(0, reasons.length() - 2);

        for (String infraction : infractions){

            switch (infraction){
                case "chat":
                    minutesMuted += 10;
                    break;
                case "annoying":
                    minutesMuted += 10;
                    break;
                case "griefing":
                    daysBanned += 7;
                    break;
                case "bullying":
                    daysBanned += 7;
                    break;
                case "doxing":
                    daysBanned += 7;
                    break;
                case "hacking":
                    permBan = true;
                    break;
                case "ban_evasion":
                    permBan = true;
                    break;
                case "chargeback":
                    permBan = true;
                    break;
            }
        }

        //Mute
        if (minutesMuted > 0) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "litebans:mute " + player + " " + minutesMuted + "m " + reasons);

        //Tempban with custom Formatting
        if (daysBanned > 0 && !permBan) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "litebans:tempban " + player + " " + daysBanned + "d " + reasons);
        }

        //Permban with custom Formatting
        if (permBan) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "litebans:ban " + player + " " + reasons);
        }
    }
}
