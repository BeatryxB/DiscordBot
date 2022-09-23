package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.sql.SQLException;

public class CommandTarget implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        if(Long.toString( event.getChannel().getId()).equals("960554493471260692")||Long.toString( event.getChannel().getId()).equals("931652066458345503")||Long.toString( event.getChannel().getId()).equals("961613222178340884")) {

            try {
                new SentMessageRoulette(event, "The last target is " + new Getting().getPseudoLastUserPlayInLife());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
