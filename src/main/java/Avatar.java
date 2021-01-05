import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import java.util.List;

public class Avatar extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("/avatar")) {


            List<Member> members = e.getMessage().getMentionedMembers();
            if (members.isEmpty()) {
                e.getChannel().sendMessage(createsEmbed(e.getAuthor())).queue();
            } else {
                e.getChannel().sendMessage(createsEmbed(members.get(0).getUser())).queue();
            }
        }
    }

    public MessageEmbed createsEmbed(User member) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        if (member.getAvatarUrl() == null) {
            embedBuilder.addField("THE PERSON YOU CHOSE HAS NO AVATAR SO SUFFER HAHAHAHAH", "🤣 🤣 🤣 🤣 🤣 🤣", false);
        } else {
            embedBuilder.setImage(member.getAvatarUrl());
        }
        return embedBuilder.build();
    }
}
