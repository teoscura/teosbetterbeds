package me.teoscura;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerListener;

import static java.lang.Math.abs;

public class BedEnterListener extends PlayerListener {
    TeosBetterBeds plugin;
    public BedEnterListener(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public void onPlayerBedEnter(PlayerBedEnterEvent event){
        Player player = event.getPlayer();
        plugin.beddersList.put(player, true);
        if(plugin.beddersList.size()<=(plugin.getServer().getOnlinePlayers().length-plugin.afkPlayers.size())*(plugin.getPercentage()/100D)){
            for(Player p : plugin.getServer().getOnlinePlayers()){
                p.sendMessage(ChatColor.DARK_AQUA
                        +"[Beds] "
                        + plugin.beddersList.size()
                        +"/"
                        +(plugin.getServer().getOnlinePlayers().length-plugin.afkPlayers.size())
                        +" players sleeping! "
                        + calculateString()
                );
            }
        }
        plugin.bedManager.onUpdateToList();
    }

    public String calculateString(){
        int n = plugin.beddersList.size()-plugin.getServer().getOnlinePlayers().length-plugin.afkPlayers.size();
        return abs(n) + " more for day.";
    }
}
