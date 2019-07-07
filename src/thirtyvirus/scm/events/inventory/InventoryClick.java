package thirtyvirus.scm.events.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import thirtyvirus.scm.SubioCraftMenu;
import thirtyvirus.scm.MenuLoader;


//InventoryClick - Handles actions related to the inventory
public class InventoryClick implements Listener{

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)) return;

        Inventory inv = event.getInventory();
        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        Player player = (Player) event.getWhoClicked();

        //Main GUI
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Game Menu")){
            mainGUI(event, player, item);
            return;
        }

        //Abilities Menu
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Quick Actions")){
            abilitiesGUI(event, player, item);
            return;
        }

        //Social GUI
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Social")){
            socialGUI(event, player, item);
            return;
        }

        //Store GUI
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Store")){
            storeGUI(event, player, item);
            return;
        }

        //_____________________________________________________________________________\\

        //Disguise Menu
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Disguise Menu")){
            disguiseGUI(event, player, item);
            return;
        }

        //Disguise Menu 2
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Disguise Menu Page 2")){
            disguiseGUI2(event, player, item);
            return;
        }

        //Money GUI
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Money Manager")){
            moneyGUI(event, player, item);
            return;
        }

        //World Tours Menu ???
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " World Tour Menu")){
            tourGUI(event, player, item);
            return;
        }

        //Anvil GUI (hotfix for deleted items)
        if (event.getView().getTitle().equals("SubioCraft Anvil")){
            if (event.isShiftClick() == false){
                event.setCancelled(true);
                SubioCraftMenu.chatAlert(player, "You must ONLY Shift Click! Sorry, the Bukkit anvil API is very broken -thirtyvirus");
            }
            return;
        }
    }

    //Inform Player of dropping compass
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        Item item = event.getItemDrop();
        if (item.getItemStack().equals(MenuLoader.icon)) {
            Player player = event.getPlayer();

            SubioCraftMenu.chatAlert(player, "Dropped Compass!");
            SubioCraftMenu.chatAlert(player, "Get a new one with '/sc'");
            player.getWorld().playEffect(player.getLocation(), Effect.POTION_BREAK, 1);
        }

        if (item.getItemStack().getType().equals(null)){
            event.getPlayer().closeInventory();
        }

    }

    //Main GUI
    @SuppressWarnings("deprecation")
    public void mainGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory(); //player.closeInventory();


        //Warps Menu
        if (item.getType() == Material.ENDER_EYE){
            player.chat("/warps");
        }

        //Abilities menu
        if (item.getType() == Material.NETHER_STAR){
            String name = Bukkit.getServer().getName();
            if (name.equals("SubioCraft Hub")){
                player.closeInventory();
                SubioCraftMenu.chatAlert(player, "You may not use those in the Hub!");
                player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_SHULKER, 1, 1);
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.openInventory(MenuLoader.createAbilitiesGUI(player));
            }


        }

        //Player head - Social
        if (item.getType() == Material.PLAYER_HEAD){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createSocialGUI(player));
        }


        //Gold Ingot - Store
        if (item.getType() == Material.GOLD_INGOT){
            player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1);
            player.chat("/buy");
            //player.openInventory(MenuLoader.createStoreGUI(player));
        }

        //Diamond (Vote)
        if (item.getType() == Material.DIAMOND){
            player.getWorld().playEffect(player.getLocation(), Effect.EXTINGUISH, 1);
            player.chat("/vote");
        }

    }

    //Abilities GUI
    @SuppressWarnings("deprecation")
    public void abilitiesGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory();

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createMainGUI(player));
        }

        //Left bar
        if (item.getType() == Material.CRAFTING_TABLE){
            if (player.hasPermission("subiocraft.vip")){
                player.performCommand("wb");
                player.getWorld().playEffect(player.getLocation(), Effect.IRON_TRAPDOOR_CLOSE, 1);
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.ANVIL){
            player.performCommand("anvil");
            if (player.hasPermission("subiocraft.vip")){
                player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1);
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.ENDER_CHEST){
            if (player.hasPermission("subiocraft.vip")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("ec");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.COOKED_BEEF){
            if (player.hasPermission("subiocraft.cocoabean")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("feed");
                player.closeInventory();
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GREEN + "[COCOABEAN]" + ChatColor.AQUA + " players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.GOLDEN_HOE){
            if (player.hasPermission("subiocraft.cocoabean")){
                player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_USE, 1);
                player.performCommand("fix");
                player.closeInventory();
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GREEN + "[COCOABEAN]" + ChatColor.AQUA + " players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }

        if (item.getType() == Material.ELYTRA){
            if (player.hasPermission("subiocraft.gucchi")){
                player.getWorld().playEffect(player.getLocation(), Effect.FIREWORK_SHOOT, 1);
                player.performCommand("fly");
                player.closeInventory();
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.DARK_PURPLE + "[GUCCHI]" + ChatColor.AQUA + " players. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }

        //Right bar
        if (item.getType() == Material.MAGENTA_GLAZED_TERRACOTTA){
            if (player.hasPermission("subiocraft.vip")){
                player.getWorld().playEffect(player.getLocation(), Effect.PORTAL_TRAVEL, 1);
                player.performCommand("back");
                player.closeInventory();
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }

        //Head - Disguise Menu
        if (item.getType() == Material.PLAYER_HEAD){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createDisguiseGUI(player));
        }

        if (item.getType() == Material.MILK_BUCKET){
            player.performCommand("milk");
            if (player.hasPermission("subiocraft.vip")){
                player.getWorld().playEffect(player.getLocation(), Effect.BREWING_STAND_BREW, 1);
                player.closeInventory();
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }

        //10 player vaults (different color shulker boxes)
        if (item.getType() == Material.RED_SHULKER_BOX){
            if (player.hasPermission("subiocraft.subscriber")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 1");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.LIGHT_PURPLE + "[SUB]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.ORANGE_SHULKER_BOX){
            if (player.hasPermission("subiocraft.vip")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 2");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.YELLOW_SHULKER_BOX){
            if (player.hasPermission("subiocraft.virus")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 3");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GOLD + "[VIRUS]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.LIME_SHULKER_BOX){
            if (player.hasPermission("subiocraft.virus")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 4");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GOLD + "[VIRUS]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.GREEN_SHULKER_BOX){
            if (player.hasPermission("subiocraft.virus")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 5");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GOLD + "[VIRUS]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.LIGHT_BLUE_SHULKER_BOX){
            if (player.hasPermission("subiocraft.cocoabean")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 6");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GREEN + "[COCOABEAN]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.CYAN_SHULKER_BOX){
            if (player.hasPermission("subiocraft.cocoabean")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 7");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GREEN + "[COCOABEAN]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.BLUE_SHULKER_BOX){
            if (player.hasPermission("subiocraft.gucchi")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 8");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.DARK_PURPLE + "[GUCCHI]" + ChatColor.AQUA + " players. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.PURPLE_SHULKER_BOX){
            if (player.hasPermission("subiocraft.gucchi")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 9");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.DARK_PURPLE + "[GUCCHI]" + ChatColor.AQUA + " players. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.MAGENTA_SHULKER_BOX){
            if (player.hasPermission("subiocraft.gucchi")){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.performCommand("pv 10");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.DARK_PURPLE + "[GUCCHI]" + ChatColor.AQUA + " players. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }

        //Kits
        if (item.getType() == Material.LEATHER_CHESTPLATE){
            if (player.hasPermission("subiocraft.subscriber")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("kit sub");
                if (player.hasPermission("subiocraft.subscriber")){ player.closeInventory(); }
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.LIGHT_PURPLE + "[SUB]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.CHAINMAIL_CHESTPLATE && !item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
            if (player.hasPermission("subiocraft.vip")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("kit vip");
                if (player.hasPermission("subiocraft.vip")){ player.closeInventory(); }
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for [VIP] players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.CHAINMAIL_CHESTPLATE && item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
            if (player.hasPermission("subiocraft.virus")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("kit virus");
                if (player.hasPermission("subiocraft.virus")){ player.closeInventory(); }
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GOLD + "[VIRUS]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.IRON_CHESTPLATE){
            if (player.hasPermission("subiocraft.cocoabean")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("kit cocoabean");
                if (player.hasPermission("subiocraft.cocoabean")){ player.closeInventory(); }
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.GREEN + "[COCOABEAN]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }
        if (item.getType() == Material.DIAMOND_CHESTPLATE){
            if (player.hasPermission("subiocraft.gucchi")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("kit gucchi");
                if (player.hasPermission("subiocraft.gucchi")){ player.closeInventory(); }
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for " + ChatColor.DARK_PURPLE + "[GUCCHI]" + ChatColor.AQUA + " players and above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }
        }

    }

    //Social GUI
    @SuppressWarnings("deprecation")
    public void socialGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory();// player.closeInventory();

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createMainGUI(player));
        }

        //Guild Menu
        if (item.getType() == Material.CREEPER_HEAD){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            SubioCraftMenu.chatAlert(player, "Guilds not Implimented yet, hang tight!");
            //player.performCommand("partyandfriendsgui:clangui");
        }
        //Friends Menu
        if (item.getType() == Material.PLAYER_HEAD){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.performCommand("partyandfriendsgui:friendsgui");
        }
        //Party Menu
        if (item.getType() == Material.ARMOR_STAND){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.performCommand("partyandfriendsgui:partygui");
        }
        //TPA Menu
        if (item.getType() == Material.ENDER_PEARL){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createTPAGUI(player));
        }

    }

    //Store GUI
    @SuppressWarnings("deprecation")
    public void storeGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory();// player.closeInventory();

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createMainGUI(player));
        }

    }
    //Vote GUI


    //Disguise GUI
    @SuppressWarnings("deprecation")
    public void disguiseGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory();// player.closeInventory();

        //Disguises

        if (player.hasPermission("subiocraft.virus")){
            player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);

            if (item.getType() == Material.BAT_SPAWN_EGG) { player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d bat"); }
            if (item.getType() == Material.BLAZE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d blaze"); }
            if (item.getType() == Material.CAVE_SPIDER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d cave_spider"); }
            if (item.getType() == Material.CHICKEN_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d chicken"); }
            if (item.getType() == Material.COW_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d cow"); }
            if (item.getType() == Material.CREEPER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d creeper"); }
            if (item.getType() == Material.DONKEY_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d donkey"); }
            if (item.getType() == Material.ELDER_GUARDIAN_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d elder_guardian"); }
            if (item.getType() == Material.ENDERMAN_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d enderman"); }
            if (item.getType() == Material.ENDERMITE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d endermite"); }
            if (item.getType() == Material.EVOKER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d evoker"); }
            if (item.getType() == Material.GHAST_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d ghast"); }
            if (item.getType() == Material.GUARDIAN_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d guardian"); }
            if (item.getType() == Material.HORSE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d horse"); }
            if (item.getType() == Material.HUSK_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d husk"); }
            if (item.getType() == Material.LLAMA_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d llama"); }
            if (item.getType() == Material.MAGMA_CUBE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d magma_cube"); }
            if (item.getType() == Material.MOOSHROOM_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d mushroom_cow"); }
            if (item.getType() == Material.MULE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d mule"); }
            if (item.getType() == Material.OCELOT_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d ocelot"); }
            if (item.getType() == Material.PARROT_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d parrot"); }
            if (item.getType() == Material.PIG_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d pig"); }
            if (item.getType() == Material.POLAR_BEAR_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d polar_bear"); }
            if (item.getType() == Material.RABBIT_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d rabbit"); }
            if (item.getType() == Material.SHEEP_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d sheep"); }
            if (item.getType() == Material.SHULKER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d shulker"); }
            if (item.getType() == Material.SILVERFISH_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d silverfish"); }
            if (item.getType() == Material.SKELETON_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d skeleton"); }
            if (item.getType() == Material.SKELETON_HORSE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d skeleton_horse"); }
            if (item.getType() == Material.SLIME_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d slime"); }
            if (item.getType() == Material.SPIDER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d spider"); }
            if (item.getType() == Material.SQUID_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d squid"); }
            if (item.getType() == Material.STRAY_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d stray"); }
            if (item.getType() == Material.VEX_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d vex"); }
            if (item.getType() == Material.VILLAGER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d villager"); }
            if (item.getType() == Material.VINDICATOR_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d vindicator"); }
            if (item.getType() == Material.WITCH_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d witch"); }
            if (item.getType() == Material.WITHER_SKELETON_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d wither_skeleton"); }
            if (item.getType() == Material.WOLF_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d wolf"); }
            if (item.getType() == Material.ZOMBIE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d zombie"); }
            if (item.getType() == Material.ZOMBIE_HORSE_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d zombie_horse"); }
            if (item.getType() == Material.ZOMBIE_PIGMAN_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d pig_zombie"); }
            if (item.getType() == Material.ZOMBIE_VILLAGER_SPAWN_EGG){ player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1); player.performCommand("d zombie_villager"); }
        }
        else{
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
            SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for VIRUS players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
        }



        //Undisguise
        if (item.getType() == Material.PLAYER_HEAD){
            if (player.hasPermission("subiocraft.virus")){
                player.getWorld().playEffect(player.getLocation(), Effect.CHORUS_FLOWER_GROW, 1);
                player.performCommand("und");
            }
            else{
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1);
                SubioCraftMenu.chatAlert(player, "Sorry! This feature is reserved for VIRUS players or above. Get these ranks using the" + ChatColor.GOLD + " Gold Block" + ChatColor.AQUA + " in your compass menu!");
            }

        }

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createAbilitiesGUI(player));
        }

        //More Disguises Button
        if (item.getType() == Material.OAK_SIGN){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createDisguiseGUI2(player));
        }


    }
    //Disguise GUI2
    @SuppressWarnings("deprecation")
    public void disguiseGUI2(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory();// player.closeInventory();

        ///disguiseplayer VirusCam falling_block sand
        if (item.getType() == Material.GLOWSTONE){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block glowstone"); }
        if (item.getType() == Material.END_STONE_BRICKS){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block end_bricks"); }
        if (item.getType() == Material.CRAFTING_TABLE){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block workbench"); }
        if (item.getType() == Material.ANVIL){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block anvil"); }
        if (item.getType() == Material.FURNACE){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block furnace"); }
        if (item.getType() == Material.COBBLESTONE){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block cobblestone"); }
        if (item.getType() == Material.SAND){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block sand"); }
        if (item.getType() == Material.STONE){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block stone"); }
        if (item.getType() == Material.OAK_LOG){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block log"); }
        if (item.getType() == Material.BOOKSHELF){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block bookshelf"); }
        if (item.getType() == Material.SANDSTONE){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block sandstone"); }
        if (item.getType() == Material.DIRT){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block dirt"); }
        if (item.getType() == Material.IRON_BLOCK){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block iron_block"); }
        if (item.getType() == Material.BREWING_STAND){ player.closeInventory(); Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "disguiseplayer " + player.getName() + " falling_block brewing_stand"); }

        //getServer().dispatchCommand(getServer().getConsoleSender(),"");

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createDisguiseGUI(player));
        }

    }
    //Money GUI
    @SuppressWarnings("deprecation")
    public void moneyGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory();// player.closeInventory();

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createAbilitiesGUI(player));
        }

    }





    //Tour GUI
    @SuppressWarnings("deprecation")
    public void tourGUI(InventoryClickEvent event, Player player, ItemStack item){

        //Prevent item duplication via shift clicking
        event.setCancelled(true); player.updateInventory(); //player.closeInventory();

        //Fish - Survival Island S1
        if (item.getType() == Material.SALMON){
            player.getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 1);
            SubioCraftMenu.chatAlert(player, "Teleported to Survival Island s1!");
            SubioCraftMenu.teleportPlayerToServer(player, "survival_island_old");
            //player.teleport(new Location(Bukkit.getWorld("survival_island_old"), -402, 68, 516));
        }

        //Cocoa Bean - Survival Island S2
        if (item.getType() == Material.COOKED_SALMON){
            player.getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 1);
            SubioCraftMenu.chatAlert(player, "Teleported to Survival Island S2!");
            SubioCraftMenu.chatAlert(player, "WITH ANTONEEEEEEEEE");
            SubioCraftMenu.teleportPlayerToServer(player, "survival_island");
            //player.teleport(new Location(Bukkit.getWorld("survival_island"), -294, 67, -3));
        }

        //Compass - Minecraft LP
        if (item.getType() == Material.COMPASS){
            player.getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 1);
            SubioCraftMenu.chatAlert(player, "Teleported to The Minecraft LP!");
            SubioCraftMenu.teleportPlayerToServer(player, "lp");
            //player.teleport(new Location(Bukkit.getWorld("hub"), -1021, 58, -875));
        }

        //Fire - First World
        if (item.getType() == Material.DIRT){
            player.getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 1);
            SubioCraftMenu.chatAlert(player, "Teleported to The First World!");
            SubioCraftMenu.teleportPlayerToServer(player, "first_world");
            //player.teleport(new Location(Bukkit.getWorld("hub"), 1020, 61, 993));
        }

        //Cobblestone - SkyBlock S1
        if (item.getType() == Material.COBBLESTONE){
            player.getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 1);
            SubioCraftMenu.chatAlert(player, "Teleported to SkyBlock S1!");
            SubioCraftMenu.teleportPlayerToServer(player, "skyblock_s1");
            //player.teleport(new Location(Bukkit.getWorld("hub"), 1015, 60, -1));
        }

        //Cancel Button
        if (item.getType() == Material.BARRIER){
            player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
            player.openInventory(MenuLoader.createMainGUI(player));
        }

    }


    //Delete Compass when dropped
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        event.setCancelled(event.getEntity().getItemStack().equals(MenuLoader.icon));
    }
}
