import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Invite extends ListenerAdapter {

    String url = "https://discord.com/api/oauth2/authorize?client_id=761072632958681099&permissions=8&scope=bot";

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("/invite")) {
            System.out.println("Invite sent");
            e.getChannel().sendMessage(String.format(url, e.getJDA().getSelfUser().getId())).queue();
        }
    }
}
