package CoreDiscordBot.Play.FabRoulette;

import CoreDiscordBot.Data.Adding;
import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Data.Modifying;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.sql.SQLException;

public class FabRouletteCore {

    private Long idChanel;
    private MessageCreateEvent event;

    public FabRouletteCore(Long idChanel, MessageCreateEvent event) {
        this.idChanel = idChanel;
        this.event = event;
        setupGame();
    }

    private void setupGame(){
        try {
            if (new Getting().getUserIdByIdDiscord(String.valueOf(this.event.getMessage().getAuthor().getId())) == -1) {
                new Adding().addUser(String.valueOf(this.event.getMessage().getAuthor().getId()), checkPseudo(event.getMessage().getAuthor().getName()));
            }

            play(this.event);
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void play(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
        switch (new Getting().getStateOfPeoplePlayById(String.valueOf(event.getMessage().getAuthor().getId()))) {
            case 1:

            case 2:

            case 5:
                if(this.idChanel!=960554493471260692L && this.idChanel != 961613222178340884L){
                    new SentMessageRoulette(event, "living can shot only <#960554493471260692> Here", "You're still in life", "Your dice is useless when you're living here", Color.MAGENTA, event.getMessage().getAuthor().getName());
                    //new SentMessageRoulette(event, "living can shot only <#961613222178340884> Here", "You're still in life", "Your dice is useless when you're living here", Color.MAGENTA, event.getMessage().getAuthor().getName());
                }
                else{
                    playInLife(event);
                }
                break;

            case 3:
                if(this.idChanel!=931322424518119484L && this.idChanel != 1037132744620257290L){
                    new SentMessageRoulette(event, "Dead can shot only <#931322424518119484> Here", "You're dead today", "Your dice is useless when you're die here", Color.MAGENTA, event.getMessage().getAuthor().getName());
                    //new SentMessageRoulette(event, "Dead can shot only <#1037132744620257290> Here", "You're dead today", "Your dice is useless when you're die here", Color.MAGENTA, event.getMessage().getAuthor().getName());
                }
                else{
                    playInVallarbre(event);
            }
                break;

            case 4:

            case 6:
                if(this.idChanel!=960554493471260692L && this.idChanel != 961613222178340884L){
                    new SentMessageRoulette(event, "living can shot only <#960554493471260692> Here", "You're still in life", "Your dice is useless when you're living here", Color.MAGENTA, event.getMessage().getAuthor().getName());
                    //new SentMessageRoulette(event, "living can shot only <#961613222178340884> Here", "You're still in life", "Your dice is useless when you're living here", Color.MAGENTA, event.getMessage().getAuthor().getName());
                }
                else{
                    tryReviveConduct(event);
                }
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

    private void playInVallarbre(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
        int result = (int) (Math.random() * 6) + 1;

        if (new Getting().checkIfPeopleShot()) {
            new Adding().addShotvalhallarbre(String.valueOf(event.getMessage().getAuthor().getId()), result);
            new SentMessageRoulette(event, "there is nobody in the Target list, so you are luck, you are the first target");
        } else {
            if (result == 1) {
                if (new Getting().getIdLastUserPlayInValhallarbre() == new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId()))) {
                    new SentMessageRoulette(event, "<@" + event.getMessage().getAuthor().getId() + ">");
                    new SentMessageRoulette(event, "Suicide", "you has commit suicide <@" + event.getMessage().getAuthor().getId() + ">", "The dice result was " + result, Color.pink, event.getMessage().getAuthor().getName());
                } else {
                    String discordId = new Getting().getDiscordIdLastUserPlayInValhallarbre();
                    new SentMessageRoulette(event, "<@" + discordId + ">");
                    new SentMessageRoulette(event, "win", "you've killed <@" + discordId + ">, but now you are the target", "The dice result was " + result, Color.pink, event.getMessage().getAuthor().getName());
                }
                new Modifying().killLastPeopleInValhallarbre(new Getting().getIdLastUserPlayInValhallarbre(), new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));

            } else {
                new SentMessageRoulette(event, "miss", "your shot was so predictable, now you are the target", "The dice result was " + result, Color.BLUE, event.getMessage().getAuthor().getName());
            }
            new Adding().addShotvalhallarbre(String.valueOf(event.getMessage().getAuthor().getId()), result);

        }

    }
    private void tryReviveConduct(MessageCreateEvent event) throws SQLException, ClassNotFoundException {
        int result1 = (int) (Math.random() * 6) + 1;
        int result2 = (int) (Math.random() * 6) + 1;
        if (result1 == result2) {
            new SentMessageRoulette(event, "You can revive", "You've became the phoenix today, congratulation ! But now you are the target", "The revive dice results was " + result1 + " and " + result2, Color.GREEN, event.getMessage().getAuthor().getName());
            new Modifying().revivePeoplePlay(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())), String.valueOf(event.getMessage().getAuthor().getId()));
        }
        else{
            tryReviveCase(event, result1, result2);
        }
    }

    private void tryReviveCase(MessageCreateEvent event, int result1, int result2) throws SQLException, ClassNotFoundException {
        int nbTryRevive = new Getting().getNbReviveTryPeoplePlayByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId()));

        switch (nbTryRevive) {
            case 1:
                new Modifying().downTryRevivePeoplePlayById(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                new Modifying().noHopePeoplePlay(new Getting().getUserIdByIdDiscord(String.valueOf(event.getMessage().getAuthor().getId())));
                break;
            case 2:

            case 3:

            case 4:
                new SentMessageRoulette(event, "Unlucky", "nice Try but you need more luck, you have only " + nbTryRevive + " try left", "The revive dice results was " + result1 + " and " + result2, Color.ORANGE, event.getMessage().getAuthor().getName());
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
