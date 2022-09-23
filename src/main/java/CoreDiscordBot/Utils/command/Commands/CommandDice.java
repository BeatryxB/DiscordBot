package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Data.Adding;
import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Data.Modifying;
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
        if (Long.toString(event.getChannel().getId()).equals("960554493471260692") || Long.toString(event.getChannel().getId()).equals("931652066458345503") || Long.toString(event.getChannel().getId()).equals("961613222178340884")) {

            try {
                if (new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())) == -1) {
                    new Adding().addUser(String.valueOf(event.getMessage().getAuthor().getId()), event.getMessage().getAuthor().getName());
                }

                play(event);
            }
            catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void play(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
        switch (new Getting().getStateOfPeoplePlayById(String.valueOf(event.getMessage().getAuthor().getId()))) {
            case 1:

            case 2:
                playInLife(event);
                break;

            case 3:
                new SentMessageRoulette(event, "Dead can't Shot", "You're dead today, you cannot play", "Your dice is useless when you're die", Color.MAGENTA, event.getMessage().getAuthor().getName());
                break;

            case 4:
                tryReviveConduct(event);
                break;
        }

    }

    private void playInLife(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
            int result = (int) (Math.random() * 6) + 1;

            if (new Getting().checkIfPeopleShot()) {
                new Adding().addShot(String.valueOf(event.getMessage().getAuthor().getId()), result);
                new SentMessageRoulette(event, "there is nobody in the Target list, so you are luck, you are the first target");
            } else {
                if (result == 1) {
                    if (new Getting().getIdLastUserPlayInLife() == new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId()))) {
                        new SentMessageRoulette(event, "<@" + event.getMessage().getAuthor().getId() + ">");
                        new SentMessageRoulette(event, "Suicide", "you has commit suicide <@" + event.getMessage().getAuthor().getId() + ">", "The dice result was " + result, Color.red, event.getMessage().getAuthor().getName());
                    } else {
                        String discordId = new Getting().getDiscordIdLastUserPlayInLife();
                        new SentMessageRoulette(event, "<@" + discordId + ">");
                        new SentMessageRoulette(event, "win", "you've killed <@" + discordId + ">, but now you are the target", "The dice result was " + result, Color.red, event.getMessage().getAuthor().getName());
                    }
                    new Modifying().killLastPeopleInLife(new Getting().getIdLastUserPlayInLife(), new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));

                } else {
                    new SentMessageRoulette(event, "miss", "your shot was so predictable, now you are the target", "The dice result was " + result, Color.white, event.getMessage().getAuthor().getName());
                }
                new Adding().addShot(String.valueOf(event.getMessage().getAuthor().getId()), result);

            }

    }
    private void tryReviveConduct(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
        int result1 = (int) (Math.random() * 6) + 1;
        int result2 = (int) (Math.random() * 6) + 1;
        if (result1 == result2) {
            new SentMessageRoulette(event, "You can revive", "You've became the phoenix today, congratulation ! But now you are the target", "The revive dice results was " + result1 + " and " + result2, Color.GREEN, event.getMessage().getAuthor().getName());
            new Modifying().revivePeoplePlay(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
        }
        else{
            tryReviveCase(event, result1, result2);
        }
    }

    private void tryReviveCase(MessageCreateEvent event, int result1, int result2) throws SQLException, ClassNotFoundException {
        int nbTryRevive = new Getting().getNbReviveTryPeoplePlayByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId()));

        switch (nbTryRevive) {
            case 1:
                new SentMessageRoulette(event, "Unlucky", "nice Try but you need more luck, this is your last try", "The revive dice results was " + result1 + " and " + result2, Color.ORANGE, event.getMessage().getAuthor().getName());
                new Modifying().downTryRevivePeoplePlayById(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                new Modifying().noHopePeoplePlay(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                break;
            case 2:

            case 3:

                new SentMessageRoulette(event, "Unlucky", "nice Try but you need more luck, you have only " + nbTryRevive + " try left", "The revive dice results was " + result1 + " and " + result2, Color.ORANGE, event.getMessage().getAuthor().getName());
                new Modifying().downTryRevivePeoplePlayById(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                break;
        }
    }
}