package thirtyvirus.scm.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import thirtyvirus.scm.SubioCraftMenu;
import thirtyvirus.scm.helpers.BookUtil;

//SafeRestart - Kicks all players from game with message "Be Right Back!"
public class Welcome implements CommandExecutor {

    //Moderate Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        //Sent from player
        Player player = (Player) sender;

        //First login chat stuff
        SubioCraftMenu.chatAlert(player, "Welcome to SubioCraft! Enjoy your stay :D");
        player.chat("/sc");

        //Book GUI for welcoming new players
        switch (Bukkit.getServer().getName()){
            case "SubioCraft Hub":
                welcomeHub(player);
                break;
            case "SubioCraft Survival":
                welcomeSurvival(player);
                break;
            case "SubioCraft SkyBlock":
                welcomeSkyBlock(player);
                break;
            case "SubioCraft Apocalypse":
                welcomeApocalypse(player);
                break;
            case "SubioCraft Creative":
                welcomeCreative(player);
                break;
            default:
                break;
        }

        return true;
    }

    //Show Book GUI Welcome Message for Hub
    //19 Characters per Line Max (not including color or style)
    public void welcomeHub(Player player){
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setAuthor("ThirtyVirus");
        meta.setTitle("Welcome to SubioCraft!");

        List<String> pages = new ArrayList<String>();

        String frontPage = "";
        frontPage = addLine(frontPage, "      &7§lWelcome to:");
        frontPage = addLine(frontPage, "      &9§lSubio&D§lCraft");
        frontPage = addLine(frontPage, "");
        frontPage = addLine(frontPage, "§rWe offer an upgraded");
        frontPage = addLine(frontPage, " Survival experience");
        frontPage = addLine(frontPage, "and a classic SkyBlock");
        frontPage = addLine(frontPage, "  mode. We also offer");
        frontPage = addLine(frontPage, "      &5§lUber Items§r");
        frontPage = addLine(frontPage, "  with unique effects!");
        frontPage = addLine(frontPage, "");
        frontPage = addLine(frontPage, "&7§lRight Click your compass to warp and use quick actions!");

        pages.add(frontPage);


        meta.setPages(pages);
        book.setItemMeta(meta);

        BookUtil.openBook(book, player);
    }

    //Show Book GUI Welcome Message for Survival
    public void welcomeSurvival(Player player){

    }

    //Show Book GUI Welcome Message for SkyBlock
    public void welcomeSkyBlock(Player player){

    }

    //Show Book GUI Welcome Message for Apocalypse
    public void welcomeApocalypse(Player player){

    }

    //Show Book GUI Welcome Message for Creative
    public void welcomeCreative(Player player){

    }

    public String addLine(String page, String line){ return page + ChatColor.translateAlternateColorCodes('&', line) + "\n"; }

}