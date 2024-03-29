package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class CommandHelp implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {

        String Title = "All the commands are here";
        String ListCommand = "!Help, !help, !h,  !H = Show this menu\n" +
                "!metamorphe, !ditto, !m = Sent one metamorphe\n" +
                "!maid = Call one maid \n" +
                "!d = Play Abyssal Roullet (possible to sent only in the <#960554493471260692> Channel)\n" +
                "!date = Show The abyssal Date (possible in the <#960602966975467551> Channel) \n" +
                "!ping = Sent one Pong \n" +
                "!score = Show the Fab Roullet Scores, only in <#960554936209403934> Channel\n" +
                "!Stats, !stats, !St, !st = Show Your stats for fab roulette, only in <#960554936209403934> Channel\n" +
                "!v, !va, !V, !VS = Play in the Valhallabre only in the <#931322424518119484> Channel\n" +
                "!vs, !vas, !VS, !VAS = Show Your score for Valhallabre, only in <#1039292747770761267> Channel\n" +
                "!target, !Target, !t, !T,  = Show the last target for the FAB Roulette, only in <#960554493471260692> Channel\n";
        String Credit = "Bot Created By BeatryxB \n Any Suggestion ? Ping me or sent me a PM, \n www.beatryxb.com";


        new SentMessageRoulette(event,Title,ListCommand,Credit, Color.CYAN,"AbyssalForestBot");
    }
}
