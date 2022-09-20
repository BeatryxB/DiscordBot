package CoreDiscordBot.Play.User;

import CoreDiscordBot.FileLog.FileLogNewLogs;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static CoreDiscordBot.Play.User.UserList.userList;

public class MAJScore {

    public MAJScore(MessageCreateEvent event) {

        long idMessage = 1003657161416642581L;
        Message m = null;
        try {
            m = event.getChannel().getMessageById(idMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            FileLogNewLogs.FileLog.addLogTrace(e.getMessage());
        }
        Object[] emb = Arrays.stream(m.getEmbeds().toArray()).toArray();
            for(int j = 0; j<emb.length;j++){
                Embed embed = (Embed) emb[j];
                String a = embed.getAuthor().get().getName();
                if(Objects.equals(a, "SCORE LIST")){
                    String[] coucou = embed.getDescription().get().split(" ");
                    int l=0;
                    for(int k = 0;k<coucou.length;k++){
                        if(!coucou[k].equals("Player")&&!coucou[k].equals(":")&!coucou[k].equals("Score")){
                            if(coucou[k].contains("@")){
                                String id = coucou[k].substring(coucou[k].indexOf('<') + 2, coucou[k].indexOf('>'));
                                System.out.println(id);
                                if(userList.containsId(id)){
                                    if(!userList.getUSerById(id).isScoreUpdate()){
                                        if(coucou[k+1].contains(":")){
                                            String[] scorea = coucou[k+1].split(":");
                                            int score = Integer.parseInt(scorea[0]);
                                            userList.getUSerById(id).setScore(userList.getUSerById(id).getScore()+score);
                                            userList.getUSerById(id).setScoreUpdate(true);
                                        }
                                        else{
                                            String[] scorea = coucou[k+1].split("<");
                                            int score = Integer.parseInt(scorea[0].trim());
                                            userList.getUSerById(id).setScore(userList.getUSerById(id).getScore()+score);
                                            userList.getUSerById(id).setScoreUpdate(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }