package CoreDiscordBot.Utils.command;

import org.javacord.api.event.message.MessageCreateEvent;

public interface CommandExecutor {

    void run(MessageCreateEvent event, Command command, String[] arg);

}
