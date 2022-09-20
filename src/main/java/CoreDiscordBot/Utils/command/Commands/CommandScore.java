package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.Play.User.MAJScore;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Play.User.UserList;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.*;

public class CommandScore implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {

       //Long.toString(event.getChannel().getId()).equals("961613222178340884") ||
        if (Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("961613222178340884") ||Long.toString(event.getChannel().getId()).equals("960554936209403934") ||Long.toString(event.getChannel().getId()).equals("931652066458345503")){
            new MAJScore(event);
            UserList list = new UserList();
            list.setUserList(UserList.userList.getUserList());

            list.getSortedUserByScore();

            String ScoreList = "Player : Score \n";

            EmbedBuilder builder = null;

            ArrayList<String> ranking = new ArrayList<>();

            ranking.add(" :first_place:");
            ranking.add(" :second_place:");
            ranking.add(" :third_place:");


            int lastScore = list.getUserList().get(0).getScore();

            int index = 0;

            for (int i = 0; i < list.getUserList().size(); i++) {
                String satut="";
                if(!list.getUserList().get(i).hasPlayToday()){
                    satut = ":innocent:";
                }
                else {
                    if (list.getUserList().get(i).isWasRevive()) {
                        if (list.getUserList().get(i).isInLife()) {
                            satut = ":angel:";
                        } else {
                            satut = ":skull_crossbones:";
                        }
                    }
                    else{
                        satut = ":innocent:";
                    }
                }
                ScoreList += "<@" + list.getUserList().get(i).getIdUser() + "> " + list.getUserList().get(i).getScore()+ " " + satut;
                if (lastScore != list.getUserList().get(i).getScore()) {
                    lastScore = list.getUserList().get(i).getScore();
                    index++;
                }
                if (index < 3) {
                    ScoreList += ranking.get(index);
                }
                ScoreList += "\n";
            }

            new SentMessageRoulette(event,"Score Table",ScoreList,"",Color.WHITE,"SCORE LIST");
        }
    }
}