import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Clear extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("/clear")) {
            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.red);
                usage.setTitle("🙄 Specify amount to delete");
                usage.setDescription("Usage '" + "(slash) clear [number of messages]");
                event.getChannel().sendMessage(usage.build()).queue();
            } else {
                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(Color.GREEN);
                    success.setTitle("✅ Successfully deleted");
                    event.getChannel().sendMessage(success.build()).queue( message -> message.delete().queueAfter(5, TimeUnit.SECONDS));
                }
                catch (IllegalArgumentException e) {
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Must provide at least 2 or at most 100 messages to be deleted")) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.cyan);
                        error.setTitle("🟠 Too many messages selected");
                        error.setDescription("The maximum messages deleted allowed one at a time is between 2 - 100 only");
                        event.getChannel().sendMessage(error.build()).queue();
                    } else if (e.toString().startsWith("java.lang.IllegalArgumentException: Message Id provided was older than 2 week")) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.cyan);
                        error.setTitle("🟠 Message selected is sent for more than two weeks old.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }
            }
        }
    }
}
