package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import static CoreDiscordBot.Play.User.UserList.userList;

public class CommandTarget implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        if(Long.toString( event.getChannel().getId()).equals("960554493471260692")||Long.toString( event.getChannel().getId()).equals("931652066458345503")||Long.toString( event.getChannel().getId()).equals("961613222178340884")) {
            if (userList.getUserTarget().isEmpty()) {
                new SentMessageRoulette(event, "Their is nobody targeted");
            } else {
                new SentMessageRoulette(event, "The last target is " + userList.getLastTarget().getPseudo());
            }
        }
    }
}
