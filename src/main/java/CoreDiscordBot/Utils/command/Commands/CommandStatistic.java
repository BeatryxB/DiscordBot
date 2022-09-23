package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CommandStatistic implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {

        //new SentMessageRoulette(event,"this function will be developed in the future");

        if (Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("960554936209403934") ||Long.toString(event.getChannel().getId()).equals("931652066458345503")) {


            String Title = "Your stats : "+event.getMessage().getAuthor().getName();

            try {
                ArrayList<Object> Statistic = new Getting().getStatsPeople(String.valueOf(event.getMessage().getAuthor().getId()));
                try{

                    int score = Integer.parseInt((String)Statistic.get(0));
                    int nbSuicide = Integer.parseInt((String)Statistic.get(1));
                    int nbShot = Integer.parseInt(String.valueOf(Statistic.get(2)));

                    DecimalFormat df = new DecimalFormat("#.##");
                    double accuracy = 0;

                    if (nbShot != 0) {
                        accuracy = (float) score / nbShot * 100;
                    }

                    String ListCommand =
                            "Your score is here : " + score + "\n" +
                            "You've committed suicide " + nbSuicide + " times in this month\n" +
                            "You've shot " + nbShot + " times in this month\n" +
                            "Your Accuracy rate is : " + df.format(accuracy) + "%\n";

                    String Credit = "Stats created By FAB Roulette For FAB, Sponsored by BeatryxB";

                    new SentMessageRoulette(event, Title, ListCommand, Credit, Color.cyan, "AbyssalForestBot For Your Stats");
                }catch (NullPointerException e) {
                    new SentMessageRoulette(event,"You've never played in fab roulette, please play to have stats");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
