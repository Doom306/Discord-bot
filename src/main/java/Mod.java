import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Mod extends ListenerAdapter {
    boolean spam = true;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("/mspam")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.KICK_MEMBERS)) {
                if (spam) {
                    this.spam = false;
                    event.getChannel().sendMessage("FEATURE REMOVED").queue();
                } else {
                    this.spam = true;
                    event.getChannel().sendMessage("FEATURE RETURNED").queue();
                }
            } else {
                event.getChannel().sendMessage("YOU CAN'T USE THIS ONLY MODS CAN LOSER").queue();
            }
        }
    }
}
