package thirtyvirus.scm.events.player;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import thirtyvirus.scm.MenuLoader;

public class PlayerUse implements Listener {

    @EventHandler(priority=EventPriority.HIGH)
    public void OnPlayerUse(PlayerInteractEvent event){

        //Compass Right Click
        if (event.getAction() == Action.RIGHT_CLICK_AIR){
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            if(item == null) return;
            if(item.equals(MenuLoader.icon)){
                player.getWorld().playEffect(player.getLocation(), Effect.CLICK2, 1);
                player.openInventory(MenuLoader.createMainGUI(player));
            }
        }

    }
}
