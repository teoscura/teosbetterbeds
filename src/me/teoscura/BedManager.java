package me.teoscura;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Duration;
import static org.apache.commons.lang3.ThreadUtils.sleep;

public class BedManager implements CommandExecutor {
    TeosBetterBeds plugin;
    private Boolean freeToWork = true;
    public BedManager(TeosBetterBeds teosBetterBeds) {
        plugin = teosBetterBeds;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings.length == 1){
                double newPercentage = Double.parseDouble(strings[0]);
                plugin.setPercentage(newPercentage);
            }
            else{
                player.sendMessage(ChatColor.RED +"This command is supposed to take one argument from 0 to 100! >> /setbedspercentage newPercentage");
                player.sendMessage("Current percentage value "+plugin.getPercentage());
                return false;
            }
            plugin.bedManager.onUpdateToList();
            return true;
        }
        return true;
    }
    public void onUpdateToList(){
        if(plugin.beddersList.size()>= (double) (plugin.getServer().getOnlinePlayers().length - plugin.afkPlayers.size()) *(plugin.getPercentage()/100D)){
            //do the funny bed stuff
            if(freeToWork){
                if(plugin.getServer().getWorld("world").getTime()>12250L){
                    freeToWork = false;
                    try {
                        for(Player p : plugin.getServer().getOnlinePlayers()){
                            p.sendMessage(ChatColor.DARK_AQUA+"[Beds] Enough players are sleeping! Turning it to day.");
                        }
                        sleep(Duration.ofMillis(100));
                        plugin.getServer().getWorld("world").setTime(0L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    freeToWork = true;
                }
            }
        }
        else{
            System.out.println(ChatColor.DARK_AQUA+"[Beds] Not enough people sleeping! " +
                    plugin.beddersList.size()+"/"+
                    Math.floor((plugin.getServer().getOnlinePlayers().length-plugin.afkPlayers.size())*(plugin.getPercentage()/100))
            );
        }
    }

}
