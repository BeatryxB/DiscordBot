package CoreDiscordBot.Play.User;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class SentMessageRoulette {

    private final MessageCreateEvent event;
    private String label;
    private final String message;
    private String resultText;
    private Object color;
    private  String author;
    private EmbedBuilder builder;

    public SentMessageRoulette(MessageCreateEvent event, String label, String message, String resultText, Object color, String author) {
        this.event = event;
        this.label = label;
        this.message = message;
        this.resultText = resultText;
        this.color = color;
        this.builder = new EmbedBuilder();
        this.author = author;
        sentmessageBuilder();
    }

    public SentMessageRoulette(MessageCreateEvent event, String message) {
        this.event = event;
        this.message = message;
        sentmessage();
    }

    private void sentmessage(){
        event.getChannel().sendMessage(message);
    }

    private void sentmessageBuilder(){
        builder = new EmbedBuilder().setTitle(label).
                setDescription(message).
                setAuthor(author).
                setFooter(resultText).setColor((Color) color);
        event.getChannel().sendMessage(builder);
    }
}
