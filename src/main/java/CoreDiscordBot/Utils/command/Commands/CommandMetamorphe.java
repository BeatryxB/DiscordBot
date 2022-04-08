package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class CommandMetamorphe implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        new SentMessageRoulette(event, "<@143418107292155904> Vous offre ce Metamorphe : https://matias.ma/nsfw/");
    }
}
