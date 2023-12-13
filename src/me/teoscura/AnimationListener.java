package me.teoscura;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerListener;

public class AnimationListener extends PlayerListener {
    TeosBetterBeds plugin;
    public AnimationListener(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public void onPlayerAnimation(PlayerAnimationEvent event){ //Any action from the player removes them from the afk list
        Player player = event.getPlayer();
        plugin.afkManager.removeFromAfkList(player);
    }
}
