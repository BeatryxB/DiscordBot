package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Data.Adding;
import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Data.Modifying;
import CoreDiscordBot.Play.FabRoulette.FabRouletteCore;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.sql.SQLException;

public class CommandDice implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {
        //Long.toString( event.getChannel().getId()).equals("960554493471260692")||
        if (Long.toString(event.getChannel().getId()).equals("960554493471260692") || Long.toString(event.getChannel().getId()).equals("961613222178340884")) {

            Long idChannel = event.getChannel().getId();

            new FabRouletteCore(idChannel, event);
        }
    }

}