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
                    new Adding().addUser(String.valueOf(event.getMessage().getAuthor().getId()), checkPseudo(event.getMessage().getAuthor().getName()));
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
                new SentMessageRoulette(event, "Dead can't shoot.", "You died this day, you cannot play", "You must not die to use the die", Color.MAGENTA, event.getMessage().getAuthor().getName());
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
                new SentMessageRoulette(event, "There is no target yet so this is you now, congratulations");
            } else {
                if (result == 1) {
                    if (new Getting().getIdLastUserPlayInLife() == new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId()))) {
                        new SentMessageRoulette(event, "<@" + event.getMessage().getAuthor().getId() + ">");
                        new SentMessageRoulette(event, "Suicide", "you have committed suicide <@" + event.getMessage().getAuthor().getId() + ">", "You rolled " + result, Color.red, event.getMessage().getAuthor().getName());
                    } else {
                        String discordId = new Getting().getDiscordIdLastUserPlayInLife();
                        new SentMessageRoulette(event, "<@" + discordId + ">");
                        new SentMessageRoulette(event, "Success", "You  killed <@" + discordId + ">, but you now are the target", "You rolled " + result, Color.red, event.getMessage().getAuthor().getName());
                    }
                    new Modifying().killLastPeopleInLife(new Getting().getIdLastUserPlayInLife(), new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));

                } else {
                    new SentMessageRoulette(event, "Missed", "Your shot was too predictable, now you are the target", "You rolled " + result, Color.white, event.getMessage().getAuthor().getName());
                }
                new Adding().addShot(String.valueOf(event.getMessage().getAuthor().getId()), result);

            }

    }
    private void tryReviveConduct(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
        int result1 = (int) (Math.random() * 6) + 1;
        int result2 = (int) (Math.random() * 6) + 1;
        if (result1 == result2) {
            new SentMessageRoulette(event, "You can revive", "You are a phoenix today, congratulations !", "You rolled " + result1 + " and " + result2, Color.GREEN, event.getMessage().getAuthor().getName());
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
                new SentMessageRoulette(event, "Unlucky", "Nice try ! Yet you failed, this was your last try", "You rolled " + result1 + " and " + result2, Color.ORANGE, event.getMessage().getAuthor().getName());
                new Modifying().downTryRevivePeoplePlayById(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                new Modifying().noHopePeoplePlay(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                break;
            case 2:

            case 3:

                new SentMessageRoulette(event, "Unlucky", "Nice try ! Though you need more luck. " + (nbTryRevive - 1) + " chances left", "You rolled " + result1 + " and " + result2, Color.ORANGE, event.getMessage().getAuthor().getName());
                new Modifying().downTryRevivePeoplePlayById(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                break;
        }
    }

    private String checkPseudo(String pseudo){
        if(pseudo.contains("'")){
            pseudo = pseudo.replace("'","''");
            System.out.println(pseudo);
        }

        return pseudo;
    }
}
