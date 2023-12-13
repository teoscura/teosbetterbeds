package me.teoscura;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkLister implements CommandExecutor {
    TeosBetterBeds plugin;
    public AfkLister(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if(!plugin.afkPlayers.isEmpty()){
            player.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.GRAY+"Afk"+ChatColor.DARK_GRAY+"]"+ChatColor.GRAY+" Afk Players: " + plugin.afkPlayers.size()+"/"+plugin.getServer().getOnlinePlayers().length);
            for(Player p: plugin.afkPlayers.keySet()){
                player.sendMessage(ChatColor.DARK_GRAY+"[>"+ChatColor.GRAY+p.getDisplayName());
            }
            return true;
        }
        player.sendMessage(ChatColor.DARK_GRAY+"["+ChatColor.GRAY+"Afk"+ChatColor.DARK_GRAY+"]"+ChatColor.GRAY+"There are no afk players to list");
        return true;
    }
}
