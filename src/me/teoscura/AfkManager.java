package me.teoscura;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkManager implements CommandExecutor {
    TeosBetterBeds plugin;
    public AfkManager(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        addToAfkList(player);
        return true;
    }
    public void addToAfkList(Player player){
        if(!plugin.afkPlayers.containsKey(player)) {
            plugin.afkPlayers.put(player, true);
            player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "AFK enabled" + ChatColor.DARK_GRAY + "]");
            plugin.bedManager.onUpdateToList();
        }
    }
    public void removeFromAfkList(Player player){ //Any action from the player removes them from the afk list
        if(plugin.afkPlayers.containsKey(player)){
            plugin.afkPlayers.remove(player);
            player.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.GRAY+"AFK disabled"+ChatColor.DARK_GRAY+"]");
            plugin.bedManager.onUpdateToList();
        }
    }
}
