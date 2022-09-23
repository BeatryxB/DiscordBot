package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class CommandPing implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        event.getChannel().sendMessage("pong !");
    }
}
