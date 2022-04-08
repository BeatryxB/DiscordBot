package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.User.MAJScore;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

public class CommandMAJSCoreRoulette implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        if(Long.toString( event.getChannel().getId()).equals("960554936209403934")||Long.toString( event.getChannel().getId()).equals("931652066458345503")){
            new MAJScore(event);
            new SentMessageRoulette(event,"The Score has been updated");
        }
    }
}