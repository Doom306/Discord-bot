import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Challenge extends ListenerAdapter {
    Mod mod = new Mod();
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("/spam")) {
            if (mod.spam) {
                int i = 1;
                int x = Integer.parseInt(e.getMessage().getContentRaw().split(" ")[1]);
                int xLength = 0;

                if (x > 0 && x < 10) {
                    xLength = 1;
                } else if (x > 9 && x < 100) {
                    xLength = 2;
                } else if (x > 99 && x < 1000) {
                    xLength = 3;
                }

                if (xLength != 0) {
                    String message = e.getMessage().getContentRaw();

                    String answer = message.substring(message.indexOf("/spam ") + (7 + xLength));
                    StringBuilder fMessage = new StringBuilder();
                    int count = 7;
                    while (i <= x) {
                        if (count == i) {
                            e.getChannel().sendMessage(fMessage).queue();
                            fMessage = new StringBuilder();
                            count += 10;
                        }
                        fMessage.append("\n" + answer);
                        i++;
                    }
                    e.getChannel().sendMessage(fMessage).queue();
                } else {
                    e.getChannel().sendMessage("Repeated Message is to big (FROM 1 - 999 ONLY)").queue();

                }
            } else {
                e.getChannel().sendMessage("Mods disable this wonderfully made feature... SHAME ON THEM!!!").queue();
            }
        }
    }
}
