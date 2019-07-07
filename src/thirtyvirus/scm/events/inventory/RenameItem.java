package thirtyvirus.scm.events.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import thirtyvirus.scm.MenuLoader;


public class RenameItem implements Listener {

    @EventHandler// (priority = EventPriority.LOWEST)
    public void playerRenameItem(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        //Prevent breaking Compass using Anvil
        if (event.getView().getType() == InventoryType.ANVIL) {
            if (event.getRawSlot() == 2) {
                if (event.getView().getItem(0).getType() != Material.AIR && event.getView().getItem(2).getType() != Material.AIR) {

                    if (event.getView().getItem(0).isSimilar(MenuLoader.icon) || event.getView().getItem(1).isSimilar(MenuLoader.icon) || event.getView().getItem(2).isSimilar(MenuLoader.icon)) {
                        event.setCancelled(true);
                        player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_SHULKER, 1, 1);
                    }

                }
            }
        }


    }

    @EventHandler
    public void playerCraftEvent(CraftItemEvent event){
        Player player = (Player) event.getWhoClicked();
        //Prevent using compass in Crafting Table

        ItemStack[] item = event.getInventory().getMatrix();

        for (int counter = 0; counter < item.length; counter++){
            if (item[counter] != null) { if (item[counter].isSimilar(MenuLoader.icon)) { event.setCancelled(true); player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_SHULKER, 1, 1); return; }}
        }

    }

}
