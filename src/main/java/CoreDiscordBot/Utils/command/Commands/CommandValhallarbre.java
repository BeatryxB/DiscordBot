package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.FabRoulette.FabRouletteCore;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class CommandValhallarbre implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        if (Long.toString(event.getChannel().getId()).equals("931322424518119484") || Long.toString(event.getChannel().getId()).equals("1037132744620257290")) {

            Long idChannel = event.getChannel().getId();

            new FabRouletteCore(idChannel,event);

        }
    }
}
