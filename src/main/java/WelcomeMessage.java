import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;


public class WelcomeMessage extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member member = e.getMember();
        member.getUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage("Hello I am a Legendary Bot. To learn more about me type /help. Thank you have a good day!!!").queue();
        });
        
        Objects.requireNonNull(e.getGuild().getDefaultChannel()).sendMessage("Welcome to my discord. I hope you enjoy your time here").queue();
    }
}
