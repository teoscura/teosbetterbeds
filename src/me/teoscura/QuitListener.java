package me.teoscura;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener extends PlayerListener {
    TeosBetterBeds plugin;
    public QuitListener(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        plugin.afkManager.removeFromAfkList(player);
    }
}
