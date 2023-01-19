package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Play.FabRoulette.FabRouletteScore;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;

public class CommandScore implements CommandExecutor {

    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {

        if (Long.toString(event.getChannel().getId()).equals("961613222178340884") || Long.toString(event.getChannel().getId()).equals("960554936209403934")) {

            new FabRouletteScore(event.getChannel().getId(), event);

        }
    }


}