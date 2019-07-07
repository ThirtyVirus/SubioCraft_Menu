package thirtyvirus.scm.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import thirtyvirus.scm.SubioCraftMenu;
import thirtyvirus.scm.MenuLoader;


public class Compass implements CommandExecutor {

    //sc Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        Inventory inv = (Inventory)player.getInventory();
        if (inv.contains(MenuLoader.icon)){
            SubioCraftMenu.chatAlert(player, "You already have a compass!");
            player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_SHULKER, 1, 1);
        }
        else{
            inv.addItem(MenuLoader.icon);
            SubioCraftMenu.chatAlert(player, "Given a new Compass!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
        }

        return true;
    }

}