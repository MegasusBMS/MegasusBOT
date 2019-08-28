package MegasusBOT;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class join {

	public join(GuildMessageReceivedEvent event) {
		AudioManager am = event.getGuild().getAudioManager();
		TextChannel tc = event.getChannel();
		
				if(am.isConnected()){
					EmbedBuilder j = new EmbedBuilder();
					j.setTitle(":cry: I'm connected to other channel");
					j.setDescription("First i need leave from here");
					tc.sendMessage(j.build()).queue();
					return;
				}
				
				GuildVoiceState vs = event.getMember().getVoiceState();
				
				if(!vs.inVoiceChannel()){
					EmbedBuilder j = new EmbedBuilder();
					j.setTitle(":sweat_smile: You are not connected to a voice channel");
					j.setDescription("First join to a voice channel");
					tc.sendMessage(j.build()).queue();
					return;
				}
				
				VoiceChannel  vc = vs.getChannel();
				Member sm = event.getGuild().getSelfMember();
				if(!sm.hasPermission(vc,Permission.VOICE_CONNECT)){
					EmbedBuilder j = new EmbedBuilder();
					j.setTitle(":cry: I dont have permision to connect to a voice channel");
					j.setDescription("say to owner or an administrator to give me this permision");
					tc.sendMessage(j.build()).queue();
					return;
				}
				am.openAudioConnection(vc);
				EmbedBuilder j = new EmbedBuilder();
				j.setTitle(":smile: Joining your voice channel");
				j.setDescription("Let's play some music");
				tc.sendMessage(j.build()).queue();
	}

}
