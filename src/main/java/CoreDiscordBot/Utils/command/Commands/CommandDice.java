package CoreDiscordBot.Utils.command.Commands;

import CoreDiscordBot.FileLog.FileLogNewLogs;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import CoreDiscordBot.Play.User.User;
import CoreDiscordBot.Utils.command.Command;
import CoreDiscordBot.Utils.command.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

import static CoreDiscordBot.Play.User.UserList.userList;

public class CommandDice implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent event, Command command, String[] arg) {

        //new SaveGame().connect();

        int result = (int) (Math.random()*6)+1;
        //Long.toString( event.getChannel().getId()).equals("960554493471260692")||
        if(Long.toString( event.getChannel().getId()).equals("960554493471260692")||Long.toString( event.getChannel().getId()).equals("931652066458345503")||Long.toString( event.getChannel().getId()).equals("961613222178340884")){
            User u;

        if(userList.containsId(String.valueOf(event.getMessage().getAuthor().getId()))){
            u = userList.getUSerById(String.valueOf(event.getMessage().getAuthor().getId()));
        }
        else{
            u = new User(String.valueOf(event.getMessage().getAuthor().getId()),event.getMessage().getAuthor().getDisplayName());
            userList.getUserList().add(u);
                if(userList.getUserTarget().isEmpty()&&userList.getNbshot()==0){
                        userList.addTarget(u);
                        new SentMessageRoulette(event,"there is nobody in the Target list, so you are luck, you are the first target");
                        userList.setNbshot();
                        return;
                }else{
                    playGameInLife(event, result, u);
                    return;
                }
        }
            if(u.isInlife()){
                playGameInLife(event, result, u);
            }
            else{
                if(userList.getUserList().get(userList.getUserList().indexOf(u)).isWasRevive()){
                    if(userList.getUserList().get(userList.getUserList().indexOf(u)).hasdieToday()){
                        new SentMessageRoulette(event, "Dead can't Shot", "You're dead today, you cannot play","Your dice is useless when you're die",Color.MAGENTA,u.getPseudo());
                    }
                    else{
                        playGameInLife(event, result, u);
                    }
                }else{
                    int result2 = (int) (Math.random()*6)+1;
                    if(userList.getUserList().get(userList.getUserList().indexOf(u)).getTryRevive()>0){
                        if(result==result2){
                            new SentMessageRoulette(event, "You can revive", "You've became the phoenix today, congratulation !","The revive dice results was "+result + " and " + result2,Color.GREEN,u.getPseudo());
                            userList.getUserList().get(userList.getUserList().indexOf(u)).revive();
                        }
                        else{
                            int i = userList.getUserList().get(userList.getUserList().indexOf(u)).getTryRevive();
                            int j = i-1;
                            if(j!=0){
                                new SentMessageRoulette(event, "Unlucky", "nice Try but you need more luck, you have only "+j+" try left","The revive dice results was "+result + " and " + result2,Color.ORANGE,u.getPseudo());
                            }
                            else{
                                new SentMessageRoulette(event, "Unlucky", "nice Try but you need more luck, this is your last try","The revive dice results was "+result + " and " + result2,Color.ORANGE,u.getPseudo());

                            }
                            i-=1;
                            userList.getUserList().get(userList.getUserList().indexOf(u)).setTryRevive(i);
                            if(i==0){
                                userList.getUserList().get(userList.getUserList().indexOf(u)).revive();
                                userList.getUserList().get(userList.getUserList().indexOf(u)).beKilled();
                            }
                        }
                    }
                }
            }
        }
        FileLogNewLogs.FileLog.addLogTrace(userList.getUserTarget().toString());
    }

    private void playGameInLife(MessageCreateEvent event, int result, User u) {
        if(result == 1){
            if(userList.getUserTarget().isEmpty()){
                userList.addTarget(u);
                new SentMessageRoulette(event,"<@"+ userList.getLastTarget().getIdUser()+">");
                new SentMessageRoulette(event, "Suicide", "you has commit suicide <@"+userList.getLastTarget().getIdUser()+">","The dice result was "+result, Color.red,u.getPseudo());
                userList.getUserList().get(userList.getUserList().indexOf(userList.getLastTarget())).beKilled();
                userList.getUserList().get(userList.getUserList().indexOf(u)).hasKilled();
                userList.removeTarget(userList.getLastTarget());
            }
            else{
            new SentMessageRoulette(event,"<@"+ userList.getLastTarget().getIdUser()+">");
            new SentMessageRoulette(event, "win", "you've killed <@"+userList.getLastTarget().getIdUser()+">, but now you are the target","The dice result was "+result, Color.red,u.getPseudo());
            userList.getUserList().get(userList.getUserList().indexOf(userList.getLastTarget())).beKilled();
            userList.getUserList().get(userList.getUserList().indexOf(u)).hasKilled();
            userList.removeTarget(userList.getLastTarget());
        }}
        else{
            if(userList.getUserTarget().contains(u)){
                userList.removeTarget(u);
                userList.addTarget(u);
            }
            else{
                userList.addTarget(u);
            }
            new SentMessageRoulette(event, "miss", "your shot was so predictable, now you are the target","The dice result was "+result,Color.white,u.getPseudo());

        }
    }
}