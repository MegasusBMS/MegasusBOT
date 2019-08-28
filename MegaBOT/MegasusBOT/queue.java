package MegasusBOT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import Music.GuildMusicManager;
import Music.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class queue{
    public queue(GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();

        if (queue.isEmpty()) {
            channel.sendMessage("The queue is empty").queue();

            return;
        }

        int trackCount = Math.min(queue.size(), 20);
        List<AudioTrack> tracks = new ArrayList<>(queue);
        EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Current Queue (Total: " + queue.size() + ")");

        for (int i = 0; i < trackCount; i++) {
            AudioTrack track = tracks.get(i);
            AudioTrackInfo info = track.getInfo();

            builder.appendDescription(String.format(
                    "%s - %s\n",
                    info.title,
                    info.author
            ));
        }

        channel.sendMessage(builder.build()).queue();
    }
}