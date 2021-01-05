import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;

import java.util.Objects;

public class Kick extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("/kick")) {
            if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.KICK_MEMBERS)) {
                for (Member member : e.getMessage().getMentionedMembers()) {
                    member.kick().queue();
                    e.getChannel().sendMessage("Kick successful").queue();
                }
            } else {
                e.getChannel().sendMessage("You do not have permissions to kick someone!").queue();
            }
        }
    }
}
