import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;


public class Ban extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("/ban")) {
            if (e.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                int x = Integer.parseInt(e.getMessage().getContentRaw().split(" ")[1]);

                if (x <= 7) {
                    for (Member member : e.getMessage().getMentionedMembers()) {
                        member.ban(x).queue();
                        e.getChannel().sendMessage("Ban successful").queue();
                    }
                } else {
                    e.getChannel().sendMessage("Ban should be a maximum of 7 days only").queue();
                }
            } else {
                e.getChannel().sendMessage("You do not have permissions to ban someone!").queue();
            }
        }
    }
}
