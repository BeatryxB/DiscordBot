package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Data.Getting;
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

       //Long.toString(event.getChannel().getId()).equals("961613222178340884") ||
        if (Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("960554936209403934") ||Long.toString(event.getChannel().getId()).equals("931652066458345503")){
            try {
                String ScoreList = "Player : Score \n";

                ArrayList<ArrayList<Object>> listScore = new Getting().getScoreList();
                int i = 3;
                int j = 0;
                for(int k=0;k<listScore.size();k++)
                {
                   j = (k-1 == -1) ? 0 : k-1;

                    if(Integer.parseInt((String)listScore.get(k).get(1))<Integer.parseInt((String)listScore.get(j).get(1))){
                        i --;
                    }

                            ScoreList += "<@"+listScore.get(k).get(0)+">   " + listScore.get(k).get(1) + " " + getMedal(i) + " "+getStateEmoji(Integer.parseInt((String) listScore.get(k).get(2))) + " \n";
                }

                new SentMessageRoulette(event,"Score Table",ScoreList,"",Color.WHITE,"SCORE LIST");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private String getMedal(int medal){
        String medalEmoji = "";
        switch (medal){
            case 3 :
                medalEmoji = ":first_place:";
                break;

            case 2:
                medalEmoji = ":second_place:";
                break;

            case 1:
                medalEmoji = ":third_place:";
                break;
        }
        return medalEmoji;
    }
    private String getStateEmoji(int state){
        String Emoji = "";
        switch (state) {
            case 1 :
                Emoji = ":innocent:";
                break;

            case 2:
                Emoji = ":angel:";
                break;

            case 3:
                Emoji = ":skull_crossbones:";
                break;

            case 4:
                Emoji = ":zombie:";
                break;
        }
        return Emoji;
    }

}