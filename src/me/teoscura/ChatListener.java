package me.teoscura;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ChatListener extends PlayerListener {
    TeosBetterBeds plugin;
    public ChatListener(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public void onPlayerChat(PlayerChatEvent event){
        Player player = event.getPlayer();
        plugin.afkManager.removeFromAfkList(player);
    }
}
