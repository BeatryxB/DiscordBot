package CoreDiscordBot.Utils.command;

import CoreDiscordBot.Utils.Main;
import CoreDiscordBot.Utils.command.Commands.*;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;

public class MessageManager {

    private static CommandRegistry registry = new CommandRegistry();

    static{
        registry.addCommand(new Command("ping", "Ping the bot", new CommandPing(), "ping", "p?"));
        registry.addCommand(new Command("dice", "Roll one dice", new CommandDice(), "d", "d"));
        registry.addCommand(new Command("Score", "Score Of the Dice Game", new CommandScore(), "score", "s"));
        registry.addCommand(new Command("MAJ Score List", "Maj Score List", new CommandMAJSCoreRoulette(), "update", "maj"));
        registry.addCommand(new Command("Date", "DAte in dibi", new DibiDate(), "date", "dateabyssale"));
        registry.addCommand(new Command("Metamorphe", "SayTh offre métamorphe", new CommandMetamorphe(), "m", "ditto", "metamorphe"));
        registry.addCommand(new Command("Maid", "Creepy Maid", new CommandMaid(), "maid"));
        registry.addCommand(new Command("Help", "Help", new CommandHelp(), "Help", "h", "H", "help"));
    }

    private static final String PREFIX = Main.getConfigManager().getToml().getString("bot.prefix");

    public static void create(MessageCreateEvent event){

        if(event.getMessageContent().startsWith(PREFIX)){
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(PREFIX.length());
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1 , args.length -1 );

            String[] finalArgs = args;
            registry.getByAliases(commandName).ifPresent((cmd)->{
                cmd.getExecutor().run(event, cmd, finalArgs);
            });
        }
    }

}
