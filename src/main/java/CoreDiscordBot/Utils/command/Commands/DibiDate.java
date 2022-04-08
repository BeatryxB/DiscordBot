package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.FABDate.Date;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class DibiDate implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {


        //Long.toString( event.getChannel().getId()).equals("960602966975467551")||
        if(Long.toString( event.getChannel().getId()).equals("960602966975467551")||Long.toString( event.getChannel().getId()).equals("931652066458345503")) {
            Date d =new Date();
            new SentMessageRoulette(event, "FAB Date", d.getCurrentDate() + "\n \n" + d.getOfficialDate(),"",Color.PINK,"Date");
        }
    }
}
