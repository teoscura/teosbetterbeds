package me.teoscura;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TeosBetterBeds extends JavaPlugin {

    AfkManager afkManager = new AfkManager(this);
    BedManager bedManager = new BedManager(this);
    MoveListener mvlistener = new MoveListener(this);
    QuitListener qtlistener = new QuitListener(this);
    ChatListener ctlistener = new ChatListener(this);
    AnimationListener  anlistener = new AnimationListener(this);
    BedEnterListener belistener = new BedEnterListener(this);
    BedLeaveListener bllistener = new BedLeaveListener(this);
    HashMap<Player, Boolean> afkPlayers = new HashMap<>();
    HashMap<Player, Boolean> beddersList = new HashMap<>();
    HashMap<Player, Date> whoMoved = new HashMap<>();
    private final String percentagePath = "bedspercentage.txt";
    private double percentage = 50.0;
    public void setPercentage(double input){
        percentage = input;
    }
    public double getPercentage(){
        return percentage;
    }
    public double loadConfig(String path) throws IOException {
        double readvalue;
        BufferedReader buf = new BufferedReader(new FileReader(path));
        if(buf.ready()){
            readvalue = Double.parseDouble(buf.readLine());
        }
        else{
            return 50D;
        }
        return readvalue;
    }
    public void saveConfig(String path) throws IOException {
        BufferedWriter buf = new BufferedWriter(new FileWriter(path));
        String str = String.valueOf(getPercentage());
        buf.write(str);
    }
    @Override
    public void onEnable() {
        try {
            setPercentage(loadConfig(percentagePath));
        } catch (IOException e) {
            System.out.println("Couldn't parse a value from the settings, setting default value of 50%");
            setPercentage(50D);
        }
        PluginManager pm = getServer().getPluginManager();
        System.out.println("Teo's better beds is enabled!");
        getCommand("afk").setExecutor(new AfkManager(this));
        getCommand("setbedspercentage").setExecutor(new BedManager(this));
        getCommand("afklist").setExecutor(new AfkLister(this));
        pm.registerEvent(Event.Type.PLAYER_MOVE, mvlistener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, qtlistener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, ctlistener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_ANIMATION, anlistener, Event.Priority.Normal, this);
        //pm.registerEvent(Event.Type.PLAYER_INVENTORY, afkManager, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_BED_ENTER, belistener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_BED_LEAVE, bllistener, Event.Priority.Normal, this);

        //noinspection Convert2Lambda
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run() {
                for(Player p : getServer().getOnlinePlayers()) {
                    if(whoMoved.containsKey(p)){
                        if (TimeUnit.MINUTES.convert(Math.abs(new Date().getTime() - whoMoved.get(p).getTime()), TimeUnit.MILLISECONDS) > 5) {
                            afkManager.addToAfkList(p);
                        }
                    }
                }
            }
        }, 0L, 100L);
    }

    @Override
    public void onDisable() {
        try {
            saveConfig(percentagePath);
        } catch (IOException e) {
            System.out.println("Failed to save percentage to file!");
            throw new RuntimeException(e);
        }
        System.out.println("Teo's better beds is disabled!");
    }

    public HashMap<Player, Boolean> getAfkPlayers(){
        return afkPlayers;
    }
}
