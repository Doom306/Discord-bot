import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Spam extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("Message received form " + event.getAuthor().getName());
        System.out.println(event.getMessage().getContentRaw());
        if (event.getMessage().getContentRaw().equalsIgnoreCase("/abtBot")) {
            event.getChannel().sendMessage(createEmbed()).queue();
        }
    }

    public MessageEmbed createEmbed() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("ABOUT THIS BOT");
        embedBuilder.addField("This bot is created by @Wow", "The creator took one week to make this bot as it is right now and currently still working on it", true);
        embedBuilder.setFooter("Type /help to know what to do with this bot!!!");
        embedBuilder.setColor(Color.green);
        return embedBuilder.build();
    }
}
