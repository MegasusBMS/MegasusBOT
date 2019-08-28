package MegasusBOT;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import Music.GuildMusicManager;
import Music.PlayerManager;
import Music.TrackScheduler;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class skip{
    public skip(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
        AudioPlayer player = musicManager.player;

        if (player.getPlayingTrack() == null) {
            channel.sendMessage("The player isn't playing aything").queue();

            return;
        }

        scheduler.nextTrack();

        channel.sendMessage("Skipping the current track").queue();
    }
}