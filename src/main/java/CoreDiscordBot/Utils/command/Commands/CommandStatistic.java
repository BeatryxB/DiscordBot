package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Play.User.User;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.text.DecimalFormat;

import static CoreDiscordBot.Play.User.UserList.userList;

public class CommandStatistic implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {

        //new SentMessageRoulette(event,"this function will be developed in the future");

        if (Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("960554936209403934") ||Long.toString(event.getChannel().getId()).equals("931652066458345503")) {


            String Title = "Your stats :";

            try{
                User u = userList.getUSerById(String.valueOf(event.getMessage().getAuthor().getId()));

                int score = u.getScore();
                int nbShot = u.getNbShot();
                int nbSuicide = u.getNbSuicide();

                DecimalFormat df = new DecimalFormat("#.##");
                double accuracy = 0;

                if (nbShot != 0) {
                    accuracy = (float) score / nbShot * 100;
                }

                String ListCommand = u.getPseudo() + "'s Stats\n" +
                        "Your score is here : " + score + "\n" +
                        "You've committed suicide " + nbSuicide + " times in this month\n" +
                        "You've shot " + nbShot + " times in this month\n" +
                        "Your Accuracy rate is : " + df.format(accuracy) + "%\n";

                String Credit = "Stats created By FAB Roulette For FAB, Sponsored by BeatryxB";

                new SentMessageRoulette(event, Title, ListCommand, Credit, Color.cyan, "AbyssalForestBot For Your Stats");
            }catch (NullPointerException e) {
                new SentMessageRoulette(event,"You've never played in fab roulette, please play to have stats");
            }


        }
    }
}
