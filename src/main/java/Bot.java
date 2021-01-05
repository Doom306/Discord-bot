import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Bot {

    GatewayIntent[] gatewayIntents = new GatewayIntent[]{GatewayIntent.GUILD_MEMBERS};
    ListenerAdapter[] listenerAdapters = new ListenerAdapter[]{new Clear(), new Mod(), new Help(), new Ban(), new Challenge(), new Kick(), new WelcomeMessage(), new Spam(), new Invite(), new RoleReactions(), new Avatar()};
    private String token;
    JDA jda;

    public Bot(String token) { this.token = token; }

    public void build() {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, gatewayIntents);
        jdaBuilder.addEventListeners(listenerAdapters);
        jdaBuilder.setActivity(Activity.playing("type /help"));

        try {
            jda = jdaBuilder.build();
            jda.awaitReady();
            botInitialized();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void botInitialized() {
        Thread thread = new Thread(() -> {
            TextChannel textChannel = jda.getTextChannelById(793973248344981525L);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();

                if (message != null && !message.equals("") && !message.equals("\n")) {
                    textChannel.sendMessage(message).queue();
                }
            }
        });
        thread.start();
    }
}
