package me.teoscura;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Date;

public class MoveListener extends PlayerListener {
    TeosBetterBeds plugin;
    public MoveListener(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public void onPlayerMove(PlayerMoveEvent event){ //Used as a base for when the player will be automatically afk'd
        Player player = event.getPlayer();
        plugin.afkManager.removeFromAfkList(player);
        plugin.whoMoved.put(player, new Date());
    }
}
