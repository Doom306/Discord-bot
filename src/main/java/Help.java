import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Help extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("/help")) {
            event.getChannel().sendMessage(createEmbed()).queue();
        }
    }

    public MessageEmbed createEmbed() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Legendary Bot bot");
        embedBuilder.addField("Ok so what can we do with this bot?....", "EASY!", false);
        embedBuilder.addField("1.) /help", "It tells you what you can do with this bot as you can see", false);
        embedBuilder.addField("2.) /spam [times to repeat] [word to repeat]", "SIMPLE IT SPAMS", false);
        embedBuilder.addField("3.) /kick [user to kick]", "KICK THE PERSON OUT OF HERE", false);
        embedBuilder.addField("4.) /ban [user to ban]", "BAN THE PERSON FROM YOUR SERVER", false);
        embedBuilder.addField("5.) /avatar [user to show avatar]", "SHOWS THE AVATAR FROM THE PERSON YOU CHOOSE", false);
        embedBuilder.addField("5.) /abtBot", "ABOUT THE BOT", false);
        embedBuilder.addField("NEW!!!", "", false);
        embedBuilder.addField("6.) /role [ROLE TO BE GIVEN]", "MAKE A CHANNEL AND THE BOT WILL SEND A MESSAGE FOR YOU TO REACT TO GET YOUR (1) ROLE (ONLY)", false);
        embedBuilder.addField("7.) /mspam", "DISABLE / ENABLE ( /spam ) command", false);
        embedBuilder.addField("7.) /invite", "THE BOT WILL SEND THE LINK FOR YOU TO ADD IT TO YOUR SERVER", false);
        embedBuilder.addField("COMING SOON!!!", "😀 😀 😀 😀 😀 😀 😀 😀", false);
        embedBuilder.addField("8.) /giveaway [NAME OF CHANNEL]", "MAKE A CHANNEL AND THE BOT WILL SEND A MESSAGE FOR YOU TO REACT TO JOIN THE GIVEAWAY", false);
        embedBuilder.addField("9.) /clear [AMOUNT TO CLEAR]", "THE BOT WILL CLEAR THE AMOUNT OF MESSAGES YOU WANT", false);
        embedBuilder.addField("10.) /music [song to play]", "THE BOT WILL PLAY THE SONG YOU WANT", false);
        embedBuilder.setColor(Color.cyan);

        return embedBuilder.build();
    }
}
