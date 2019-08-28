package MegasusBOT;

import java.net.MalformedURLException;
import java.net.URL;

import Music.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Play{
        public Play(String[] args, GuildMessageReceivedEvent event) {
        	PlayerManager manager = PlayerManager.getInstance();
        	TextChannel channel = event.getChannel();
        	String url = args[1];
        	if(url.isEmpty())
        		return;
        	if (!isURL(url) && !url.startsWith("ytsearch:")) {
                // Use the youtube api for search instead, making a lot of requests with "ytsearch:" will get you blocked
                channel.sendMessage("Please provide a valid youtube, soundcloud or bandcamp link").queue();
                return;
                }
            manager.loadAndPlay(event.getChannel(), url);

            manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);
        }
        public boolean isURL(String imput){
        	try{
        		new URL(imput);
        		return true;
        	}catch(MalformedURLException e){
        		return false;
        	}
        }
}
