package thirtyvirus.scm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MenuLoader {

    public static ItemStack icon = nameItem(Material.COMPASS, ChatColor.GREEN + "Game Menu" + ChatColor.GRAY + " (Right Click)");
    //Main GUI
    public static Inventory createMainGUI(Player player){

        //Create Main GUI
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Game Menu");




        //Warps (Ender Eye)
        inv.setItem(12, nameItem(Material.ENDER_EYE, ChatColor.LIGHT_PURPLE + "Warps"));

        //Abilities (Nether Star)
        inv.setItem(13, nameItem(Material.NETHER_STAR, ChatColor.GREEN + "Abilities"));

        //Social (Player Head)
        ItemStack socials = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) socials.getItemMeta();

        skullMeta.setOwningPlayer((OfflinePlayer)player);
        socials.setItemMeta(skullMeta);
        socials = nameItem(socials, ChatColor.LIGHT_PURPLE + "Socials");
        inv.setItem(14, socials);

        //Store (Gold Ingot)
        inv.setItem(16, nameItem(Material.GOLD_INGOT, ChatColor.GOLD + "Rank Store"));
        //Vote (Diamond)
        inv.setItem(17, nameItem(Material.DIAMOND, ChatColor.BLUE + "Vote for Us"));

        //Tour Old Worlds
        //ItemStack tourWorlds = nameItem(Material.OAK_LOG, ChatColor.GREEN + "Tour Old Worlds!");
        //inv.setItem(26, tourWorlds);

        return inv;
    }

    //Abilities GUI
    public static Inventory createAbilitiesGUI(Player player){

        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Quick Actions");

        //Craft
        ItemStack craft = nameItem(Material.CRAFTING_TABLE, ChatColor.DARK_GREEN + "Craft");
        inv.setItem(0, craft);
        //Anvil
        ItemStack anvil = nameItem(Material.ANVIL, ChatColor.GRAY + "Anvil");
        inv.setItem(9, anvil);
        //Ender Chest
        ItemStack ec = nameItem(Material.ENDER_CHEST, ChatColor.LIGHT_PURPLE + "Ender Chest");
        inv.setItem(18, ec);
        //Feed
        ItemStack feed = nameItem(Material.COOKED_BEEF, ChatColor.GREEN + "Feed");
        inv.setItem(27, feed);
        //Fix
        ItemStack fix = nameItem(Material.GOLDEN_HOE, ChatColor.GOLD + "Fix");
        inv.setItem(36, fix);
        //Fly
        ItemStack fly = nameItem(Material.ELYTRA, ChatColor.BLUE + "Fly");
        inv.setItem(45, fly);

        //Player Vaults
        ItemStack pv1 = nameItem(Material.RED_SHULKER_BOX, ChatColor.RED + "Player Vault 1");
        inv.setItem(29, pv1);
        ItemStack pv2 = nameItem(Material.ORANGE_SHULKER_BOX, ChatColor.RED + "Player Vault 2");
        inv.setItem(30, pv2);
        ItemStack pv3 = nameItem(Material.YELLOW_SHULKER_BOX, ChatColor.YELLOW + "Player Vault 3");
        inv.setItem(31, pv3);
        ItemStack pv4 = nameItem(Material.LIME_SHULKER_BOX, ChatColor.GREEN + "Player Vault 4");
        inv.setItem(32, pv4);
        ItemStack pv5 = nameItem(Material.GREEN_SHULKER_BOX, ChatColor.DARK_GREEN + "Player Vault 5");
        inv.setItem(33, pv5);
        ItemStack pv6 = nameItem(Material.LIGHT_BLUE_SHULKER_BOX, ChatColor.BLUE + "Player Vault 6");
        inv.setItem(38, pv6);
        ItemStack pv7 = nameItem(Material.CYAN_SHULKER_BOX, ChatColor.DARK_AQUA + "Player Vault 7");
        inv.setItem(39, pv7);
        ItemStack pv8 = nameItem(Material.BLUE_SHULKER_BOX, ChatColor.DARK_BLUE + "Player Vault 8");
        inv.setItem(40, pv8);
        ItemStack pv9 = nameItem(Material.PURPLE_SHULKER_BOX, ChatColor.DARK_PURPLE + "Player Vault 9");
        inv.setItem(41, pv9);
        ItemStack pv10 = nameItem(Material.MAGENTA_SHULKER_BOX, ChatColor.LIGHT_PURPLE + "Player Vault 10");
        inv.setItem(42, pv10);

        //Kits
        ItemStack kit1 = nameItem(Material.LEATHER_CHESTPLATE, ChatColor.LIGHT_PURPLE + "[SUB] Kit");
        inv.setItem(47, kit1);
        ItemStack kit2 = nameItem(Material.CHAINMAIL_CHESTPLATE, ChatColor.BLUE + "[VIP] Kit");
        inv.setItem(48, kit2);
        ItemStack kit3 = nameItem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GOLD + "[VIRUS] Kit");
        kit3.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        inv.setItem(49, kit3);
        ItemStack kit4 = nameItem(Material.IRON_CHESTPLATE, ChatColor.GREEN + "[COCOABEAN] Kit");
        inv.setItem(50, kit4);
        ItemStack kit5 = nameItem(Material.DIAMOND_CHESTPLATE, ChatColor.DARK_PURPLE + "[GUCCHI] Kit");
        inv.setItem(51, kit5);

        //Back
        ItemStack back = nameItem(Material.MAGENTA_GLAZED_TERRACOTTA, ChatColor.LIGHT_PURPLE + "Back");
        inv.setItem(8, back);
        //Disgusie
        ItemStack disguise = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) disguise.getItemMeta();

        skullMeta.setOwningPlayer((OfflinePlayer)player);
        disguise.setItemMeta(skullMeta);
        disguise = nameItem(disguise, ChatColor.GREEN + "Disguise");
        inv.setItem(17, disguise);
        //Milk
        ItemStack milk = nameItem(Material.MILK_BUCKET, ChatColor.WHITE + "Milk");
        inv.setItem(26, milk);

        //Cancel Button
        ItemStack cancel = nameItem(Material.BARRIER, ChatColor.RED + "Cancel");
        inv.setItem(53, cancel);

        return inv;
    }

    //Social GUI
    public static Inventory createSocialGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Social");

        //Back Button
        inv.setItem(26, nameItem(Material.BARRIER, ChatColor.RED + "Back"));

        inv.setItem(11, nameItem(Material.CREEPER_HEAD, ChatColor.GREEN + "Guild"));
        inv.setItem(13, nameItem(Material.PLAYER_HEAD, ChatColor.GRAY + "Friends"));
        inv.setItem(15, nameItem(Material.ARMOR_STAND, ChatColor.BLUE + "Party"));
        inv.setItem(17, nameItem(Material.ENDER_PEARL, ChatColor.BLUE + "TPA"));

        return inv;
    }

    //Store GUI
    public static Inventory createStoreGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Store");

        //Back Button
        inv.setItem(26, nameItem(Material.BARRIER, ChatColor.RED + "Back"));



        return inv;
    }

    //TPA GUI
    public static Inventory createTPAGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Store");

        //Back Button
        inv.setItem(26, nameItem(Material.BARRIER, ChatColor.RED + "Back"));



        return inv;
    }

    //Disguise GUI
    public static Inventory createDisguiseGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Disguise Menu");

        //Disguise Eggs
        ItemStack d1 = nameItem(Material.BAT_SPAWN_EGG, ChatColor.GREEN + "Bat Disguise"); inv.setItem(0, d1);
        ItemStack d2 = nameItem(Material.BLAZE_SPAWN_EGG, ChatColor.GREEN + "Blaze Disguise"); inv.setItem(1, d2);
        ItemStack d3 = nameItem(Material.CAVE_SPIDER_SPAWN_EGG, ChatColor.GREEN + "Cave Spider Disguise"); inv.setItem(2, d3);
        ItemStack d4 = nameItem(Material.CHICKEN_SPAWN_EGG, ChatColor.GREEN + "Chicken Disguise"); inv.setItem(3, d4);
        ItemStack d5 = nameItem(Material.COW_SPAWN_EGG, ChatColor.GREEN + "Cow Disguise"); inv.setItem(4, d5);
        ItemStack d6 = nameItem(Material.CREEPER_SPAWN_EGG, ChatColor.GREEN + "Creeper Disguise"); inv.setItem(5, d6);
        ItemStack d7 = nameItem(Material.DONKEY_SPAWN_EGG, ChatColor.GREEN + "Donkey Disguise"); inv.setItem(6, d7);
        ItemStack d8 = nameItem(Material.ELDER_GUARDIAN_SPAWN_EGG, ChatColor.GREEN + "Elder Guardian Disguise"); inv.setItem(7, d8);
        ItemStack d9 = nameItem(Material.ENDERMAN_SPAWN_EGG, ChatColor.GREEN + "Enderman Disguise"); inv.setItem(8, d9);
        ItemStack d10 = nameItem(Material.ENDERMITE_SPAWN_EGG, ChatColor.GREEN + "Endermite Disguise"); inv.setItem(9, d10);
        ItemStack d11 = nameItem(Material.EVOKER_SPAWN_EGG, ChatColor.GREEN + "Evoker Disguise"); inv.setItem(10, d11);
        ItemStack d12 = nameItem(Material.GHAST_SPAWN_EGG, ChatColor.GREEN + "Ghast Disguise"); inv.setItem(11, d12);
        ItemStack d13 = nameItem(Material.GUARDIAN_SPAWN_EGG, ChatColor.GREEN + "Guardian Disguise"); inv.setItem(12, d13);
        ItemStack d14 = nameItem(Material.HORSE_SPAWN_EGG, ChatColor.GREEN + "Horse Disguise"); inv.setItem(13, d14);
        ItemStack d15 = nameItem(Material.HUSK_SPAWN_EGG, ChatColor.GREEN + "Husk Disguise"); inv.setItem(14, d15);
        ItemStack d16 = nameItem(Material.LLAMA_SPAWN_EGG, ChatColor.GREEN + "Llama Disguise"); inv.setItem(15, d16);
        ItemStack d17 = nameItem(Material.MAGMA_CUBE_SPAWN_EGG, ChatColor.GREEN + "Magma Cube Disguise"); inv.setItem(16, d17);
        ItemStack d18 = nameItem(Material.MOOSHROOM_SPAWN_EGG, ChatColor.GREEN + "Mooshroom Disguise"); inv.setItem(17, d18);
        ItemStack d19 = nameItem(Material.MULE_SPAWN_EGG, ChatColor.GREEN + "Mule Disguise"); inv.setItem(18, d19);
        ItemStack d20 = nameItem(Material.OCELOT_SPAWN_EGG, ChatColor.GREEN + "Ocelot Disguise"); inv.setItem(19, d20);
        ItemStack d21 = nameItem(Material.PARROT_SPAWN_EGG, ChatColor.GREEN + "Parrot Disguise"); inv.setItem(20, d21);
        ItemStack d22 = nameItem(Material.PIG_SPAWN_EGG, ChatColor.GREEN + "Pig Disguise"); inv.setItem(21, d22);
        ItemStack d23 = nameItem(Material.POLAR_BEAR_SPAWN_EGG, ChatColor.GREEN + "Polar Bear Disguise"); inv.setItem(22, d23);
        ItemStack d24 = nameItem(Material.RABBIT_SPAWN_EGG, ChatColor.GREEN + "Rabbit Disguise"); inv.setItem(23, d24);
        ItemStack d25 = nameItem(Material.SHEEP_SPAWN_EGG, ChatColor.GREEN + "Sheep Disguise"); inv.setItem(24, d25);
        ItemStack d26 = nameItem(Material.SHULKER_SPAWN_EGG, ChatColor.GREEN + "Shulker Disguise"); inv.setItem(25, d26);
        ItemStack d27 = nameItem(Material.SILVERFISH_SPAWN_EGG, ChatColor.GREEN + "Silverfish Disguise"); inv.setItem(26, d27);
        ItemStack d28 = nameItem(Material.SKELETON_SPAWN_EGG, ChatColor.GREEN + "Skeleton Disguise"); inv.setItem(27, d28);
        ItemStack d29 = nameItem(Material.SKELETON_HORSE_SPAWN_EGG, ChatColor.GREEN + "Skeleton Horse Disguise"); inv.setItem(28, d29);
        ItemStack d30 = nameItem(Material.SLIME_SPAWN_EGG, ChatColor.GREEN + "Slime Disguise"); inv.setItem(29, d30);
        ItemStack d31 = nameItem(Material.SPIDER_SPAWN_EGG, ChatColor.GREEN + "Spider Disguise"); inv.setItem(30, d31);
        ItemStack d32 = nameItem(Material.SQUID_SPAWN_EGG, ChatColor.GREEN + "Squid Disguise"); inv.setItem(31, d32);
        ItemStack d33 = nameItem(Material.STRAY_SPAWN_EGG, ChatColor.GREEN + "Stray Disguise"); inv.setItem(32, d33);
        ItemStack d34 = nameItem(Material.VEX_SPAWN_EGG, ChatColor.GREEN + "Vex Disguise"); inv.setItem(33, d34);
        ItemStack d35 = nameItem(Material.VILLAGER_SPAWN_EGG, ChatColor.GREEN + "Villager Disguise"); inv.setItem(34, d35);
        ItemStack d36 = nameItem(Material.VINDICATOR_SPAWN_EGG, ChatColor.GREEN + "Vindicator Disguise"); inv.setItem(35, d36);
        ItemStack d37 = nameItem(Material.WITCH_SPAWN_EGG, ChatColor.GREEN + "Witch Disguise"); inv.setItem(37, d37);
        ItemStack d38 = nameItem(Material.WITHER_SKELETON_SPAWN_EGG, ChatColor.GREEN + "Wither Skeleton Disguise"); inv.setItem(38, d38);
        ItemStack d39 = nameItem(Material.WOLF_SPAWN_EGG, ChatColor.GREEN + "Wolf Disguise"); inv.setItem(39, d39);
        ItemStack d40 = nameItem(Material.ZOMBIE_SPAWN_EGG, ChatColor.GREEN + "Zombie Disguise"); inv.setItem(40, d40);
        ItemStack d41 = nameItem(Material.ZOMBIE_HORSE_SPAWN_EGG, ChatColor.GREEN + "Zombie Horse Disguise"); inv.setItem(41, d41);
        ItemStack d42 = nameItem(Material.ZOMBIE_PIGMAN_SPAWN_EGG, ChatColor.GREEN + "Zombie Pigman Disguise"); inv.setItem(42, d42);
        ItemStack d43 = nameItem(Material.ZOMBIE_VILLAGER_SPAWN_EGG, ChatColor.GREEN + "Zombie Villager Disguise"); inv.setItem(43, d43);

        //Undisguise Button
        ItemStack undisguise = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) undisguise.getItemMeta();

        skullMeta.setOwningPlayer((OfflinePlayer)player);
        undisguise.setItemMeta(skullMeta);
        undisguise = nameItem(undisguise, ChatColor.GREEN + "Undisguise");
        inv.setItem(48, undisguise);

        //Back Button
        ItemStack cancel = nameItem(Material.BARRIER, ChatColor.GREEN + "Cancel");
        inv.setItem(49, cancel);

        //More Disguises
        ItemStack more = nameItem(Material.OAK_SIGN, ChatColor.GREEN + "More Disguises! [WIP]");
        inv.setItem(50, more);

        return inv;
    }
    //Disguises Page 2
    public static Inventory createDisguiseGUI2(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Disguise Menu Page 2");

        ItemStack d1 = nameItem(Material.DIRT, ChatColor.GREEN + "Dirt Block"); inv.setItem(10, d1);
        ItemStack d2 = nameItem(Material.SAND, ChatColor.GREEN + "Sand Block"); inv.setItem(11, d2);
        ItemStack d3 = nameItem(Material.STONE, ChatColor.GREEN + "Stone Block"); inv.setItem(12, d3);
        ItemStack d4 = nameItem(Material.COBBLESTONE, ChatColor.GREEN + "Cobblestone Block"); inv.setItem(13, d4);
        ItemStack d5 = nameItem(Material.SANDSTONE, ChatColor.GREEN + "Sandstone Block"); inv.setItem(14, d5);
        ItemStack d6 = nameItem(Material.OAK_LOG, ChatColor.GREEN + "Log Block"); inv.setItem(15, d6);
        ItemStack d7 = nameItem(Material.BOOKSHELF, ChatColor.GREEN + "Bookshelf Block"); inv.setItem(16, d7);
        ItemStack d8 = nameItem(Material.IRON_BLOCK, ChatColor.GREEN + "Iron Block"); inv.setItem(19, d8);
        ItemStack d9 = nameItem(Material.GLOWSTONE, ChatColor.GREEN + "Glowstone Block"); inv.setItem(20, d9);
        ItemStack d10 = nameItem(Material.END_STONE_BRICKS, ChatColor.GREEN + "End Brick Block"); inv.setItem(21, d10);
        ItemStack d11 = nameItem(Material.CRAFTING_TABLE, ChatColor.GREEN + "Crafting Table"); inv.setItem(22, d11);
        ItemStack d12 = nameItem(Material.FURNACE, ChatColor.GREEN + "Furnace"); inv.setItem(23, d12);
        ItemStack d13 = nameItem(Material.ANVIL, ChatColor.GREEN + "Anvil"); inv.setItem(24, d13);
        ItemStack d14 = nameItem(Material.BREWING_STAND, ChatColor.GREEN + "Brewing Stand"); inv.setItem(25, d14);
        //Back Button
        ItemStack cancel = nameItem(Material.BARRIER, ChatColor.GREEN + "Cancel");
        inv.setItem(49, cancel);

        return inv;
    }

    //Money Menu
    public static Inventory createMoneyGUI(){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Money Manager");

        //Back Button
        ItemStack cancel = nameItem(Material.BARRIER, ChatColor.GREEN + "Cancel");
        inv.setItem(49, cancel);

        return inv;
    }
    //Home Menu
    public static Inventory createHomeGUI(){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " Home Manager");

        //Back Button
        ItemStack cancel = nameItem(Material.BARRIER, ChatColor.GREEN + "Cancel");
        inv.setItem(49, cancel);

        return inv;
    }

    //Tour GUI
    public static Inventory createTourGUI(){
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Subio" + ChatColor.DARK_PURPLE + "Craft" + ChatColor.DARK_GRAY + " World Tour Menu");

        //Fish - Survival Island S1
        ItemStack sis1 = nameItem(Material.SALMON, ChatColor.GREEN + "Survival Island S1");
        inv.setItem(9, sis1);

        //Cocoa Bean - Survival Island S2
        ItemStack sis2 = nameItem(Material.COOKED_SALMON, ChatColor.GREEN + "Survival Island S2");
        inv.setItem(11, sis2);

        //Compass - Minecraft LP
        ItemStack spawn = nameItem(Material.COMPASS, ChatColor.GREEN + "Minecraft LP");
        inv.setItem(13, spawn);

        //Fire - First World
        ItemStack creative = nameItem(Material.DIRT, ChatColor.GREEN + "First Minecraft World");
        inv.setItem(15, creative);

        //Cobblestone - SkyBlock S1
        ItemStack skyblock = nameItem(Material.COBBLESTONE, ChatColor.GREEN + "SkyBlock S1");
        inv.setItem(17, skyblock);

        //Cancel Button
        ItemStack cancelButton = nameItem(Material.BARRIER, ChatColor.GREEN + "Cancel");
        inv.setItem(26, cancelButton);

        return inv;
    }

    //Renames Given Item
    public static ItemStack nameItem(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        //lots of options for changing item meta data
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    //Creates item that is renamed given material and name
    public static ItemStack nameItem(Material item, String name){
        return nameItem(new ItemStack(item), name);
    }

}
