package me.teoscura;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerListener;

public class BedLeaveListener extends PlayerListener {
    TeosBetterBeds plugin;
    public BedLeaveListener(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public void onPlayerBedLeave(PlayerBedLeaveEvent event){
        Player player = event.getPlayer();
        plugin.beddersList.remove(player);
        plugin.bedManager.onUpdateToList();
    }
}
