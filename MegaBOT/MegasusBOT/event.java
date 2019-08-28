package MegasusBOT;

import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class event extends ListenerAdapter{
	
	String[] messages = {
			"[member] joined. You must construct additional pylons.",
			"Never gonna give [member] up. Never let [member] down!",
			"Hey! Listen! [member] has joined!",
			"Ha! [member] has joined! You activated my trap card!",
			"We've been expecting you, [member].",
			"It's dangerous to go alone, take [member]!",
			"Swoooosh. [member] just landed.",
			"Brace yourselves. [member] just joined the server.",
			"A wild [member] appeared.",
			"Mrrrr , [member] , i like this guy!"
		};
	
	public void join (GuildMemberJoinEvent event){
		Random r= new Random();
		int n=r.nextInt(messages.length - 1);
		EmbedBuilder join = new EmbedBuilder();
		join.setColor(0x66d8ff);
		join.setTitle(":kissing_heart:");
		join.setDescription(messages[n].replace("[member]", event.getMember().getAsMention()));
		event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
	}
	
	public void leave (GuildMemberLeaveEvent event){
		EmbedBuilder join = new EmbedBuilder();
		join.setColor(0xFF7733);
		join.setTitle(":cry:");
		join.setDescription(event.getMember() + " left us!");
		event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
	}
	
}
