package me.skap.anti.swearspam.Anti;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

class ChatReader extends JavaPlugin implements Listener{

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        if(!getConfig().contains("words")){
            List<String> words = new ArrayList<String>();
            words.add("ass");
            words.add("fuck");
            getConfig().set("words", words);
        }
        saveConfig();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        List<String> words = getConfig().getStringList("words");
        for(int i = 0; i < words.size(); i++){
            if(msg.contains(words.get(i))){
                e.setCancelled(true);
                e.getPlayer().sendMessage("You are not allowed to swear!");
            }
        }
    }

}