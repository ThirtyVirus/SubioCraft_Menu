package thirtyvirus.scm.events.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import thirtyvirus.scm.SubioCraftMenu;

//PlayerJoin - Handles actions to be taken when a player joins the game
public class PlayerJoin implements Listener {

    @SuppressWarnings("unused")
    private SubioCraftMenu plugin;

    public PlayerJoin(SubioCraftMenu plugin){
        this.plugin = plugin;
    }

    //When the player joins
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        OfflinePlayer player = (OfflinePlayer) event.getPlayer();

        long current = System.currentTimeMillis();
        long first = player.getFirstPlayed();

        if (current - 30000 < first || !player.hasPlayedBefore()){
            event.getPlayer().chat("/welcome");
        }
    }

}
