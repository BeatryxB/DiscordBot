package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.FabRoulette.FabRouletteScore;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class CommandValhallarbreScore implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        if (Long.toString(event.getChannel().getId()).equals("1037132744620257290") || Long.toString(event.getChannel().getId()).equals("1039292747770761267")) {
            new FabRouletteScore(event.getChannel().getId(), event);
        }
    }
}
