package thirtyvirus.scm;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import thirtyvirus.scm.commands.*;
import thirtyvirus.scm.events.inventory.InventoryClick;
import thirtyvirus.scm.events.inventory.RenameItem;
import thirtyvirus.scm.events.player.PlayerJoin;
import thirtyvirus.scm.events.player.PlayerUse;

//SubioCraft Menu - Author: ThirtyVirus
public class SubioCraftMenu extends JavaPlugin {

    private PluginDescriptionFile descFile = getDescription();
    private PluginManager pm = getServer().getPluginManager();
    private Logger logger = getLogger();

    // static String prefix = "&6&l[&3&lS&d&lC&6&l]&r&B ";
    public static final String prefix = ChatColor.GOLD + "" + ChatColor.BOLD + "[" + ChatColor.AQUA + ChatColor.BOLD + "S" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "C" + ChatColor.GOLD + ChatColor.BOLD + "]" + ChatColor.RESET + ChatColor.AQUA + " ";

    // player Permissions
    private Permission subscriber = new Permission("subiocraft.subscriber");
    private Permission vip = new Permission("subiocraft.vip");
    private Permission virus = new Permission("subiocraft.virus");
    private Permission cocoabean = new Permission("subiocraft.cocoabean");
    private Permission gucchi = new Permission("subiocraft.gucchi");

    // staff Permissions
    private Permission helper = new Permission("subiocraft.helper");
    private Permission builder = new Permission("subiocraft.builder");
    private Permission moderator = new Permission("subiocraft.moderator");
    private Permission admin = new Permission("subiocraft.admin");

    //TODO
    //Prevent doing inventory actions with compass (moving to chest, villager, anvil, ect...)
    //sban changes
    //    Tab auto-complete for infraction types
    //    more infraction types
    //    verify bans work?
    //    Formatting in ban message
    //    do not count reasons other than those in list
    //Different first time login book messages for each server
    //Help command for each gamemode custom to subio (open book menu)
    //Book menu that comes up on store purchase / donation.
    //Update disguise menu for new mobs in 1.13.2
    //add more buttons to quick actions menu (ex: rename)

    // processes to be carried out at server start
    public void onEnable(){
        registerCommands();
        registerEvents();
        registerPermissions();

        logger.info(descFile.getName() + " V: " + descFile.getVersion() + " has been enabled");
    }

    // processes to be carried out at server stop
    public void onDisable(){
        logger.info(descFile.getName() + " V: " + descFile.getVersion() + " has been disabled");
    }

    // register Commands
    public void registerCommands(){

        //Player Commands
        getCommand("welcome").setExecutor(new Welcome());;
        getCommand("sc").setExecutor(new Compass());;
        getCommand("gui").setExecutor(new Gui());;

        //Staff Commands
        getCommand("chatAlert").setExecutor(new ChatAlert());;
        getCommand("sban").setExecutor(new Sban());;
        getCommand("safeRestart").setExecutor(new SafeRestart());

    }

    // register Events
    public void registerEvents(){
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new InventoryClick(),this);
        pm.registerEvents(new RenameItem(), this);
        pm.registerEvents(new PlayerUse(), this);
    }

    // register Permissions
    public void registerPermissions(){

        // player Rank Permissions
        pm.addPermission(subscriber);
        pm.addPermission(vip);
        pm.addPermission(virus);
        pm.addPermission(cocoabean);
        pm.addPermission(gucchi);

        // staff Rank Permissions
        pm.addPermission(helper);
        pm.addPermission(builder);
        pm.addPermission(moderator);
        pm.addPermission(admin);

    }

    // chat Alert
    public static void chatAlert(Player player, String message){
        message = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(prefix + message);
    }

    //teleport Player between servers
    public static void teleportPlayerToServer(final Player player, final String server){
        player.performCommand("server " + server);

    }

}
