import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleReactions extends ListenerAdapter {

    long channelID;
    String channelName = "roles";
    long messageID;
    List<Role> role = new ArrayList<>();

    @Override
    public void onTextChannelCreate(@NotNull TextChannelCreateEvent event) {
        if (event.getChannel().getName().equals(channelName)) {
            event.getChannel().sendMessage("React to this message to get the role of " + role).queue();
            channelID = event.getChannel().getIdLong();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("React to this message to get the role of")) {
            messageID = e.getMessage().getIdLong();
        }
            if (e.getMessage().getContentRaw().startsWith("/role")) {
                role = e.getMessage().getMentionedRoles();
                e.getGuild().createTextChannel(channelName).queue();

            }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getTextChannel().getIdLong() == channelID) {
            if (event.getMessageIdLong() == messageID) {
                System.out.println("Reaction received from " + Objects.requireNonNull(event.getUser()).getName());
                event.getGuild().addRoleToMember(Objects.requireNonNull(event.getMember()), role.get(0)).queue();
                System.out.println("Role given");
            }
        }
    }


    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if (event.getChannel().getIdLong() == channelID) {
            if (event.getMessageIdLong() == messageID) {
                event.getGuild().removeRoleFromMember(event.getUserId(), role.get(0)).queue();
                System.out.println("Reaction removed by " + event.getUserId());
                System.out.println("Role removed");
            }
        }
    }
}
