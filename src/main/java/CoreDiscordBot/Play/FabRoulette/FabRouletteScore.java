package CoreDiscordBot.Play.FabRoulette;

import CoreDiscordBot.Data.Getting;
import CoreDiscordBot.Play.User.SentMessageRoulette;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class FabRouletteScore {

    private Long idChanel;

    private MessageCreateEvent event;

    public FabRouletteScore(Long idChanel, MessageCreateEvent event) {
        this.idChanel = idChanel;
        this.event = event;
        setUpGame();
    }

    private void setUpGame(){
        try {

            String ScoreList = "Player : Score \n";

            ArrayList<ArrayList<Object>> listScore = null;
            if(idChanel == 1039292747770761267L || idChanel == 1037132744620257290L){
                listScore = new Getting().getScoreListInValhallabre();

            }else if(idChanel == 961613222178340884L || idChanel == 960554936209403934L){
                listScore = new Getting().getScoreListInLife();
            }

            int i = 3;
            int j;
            for(int k=0;k<listScore.size();k++)
            {
                j = (k-1 == -1) ? 0 : k-1;

                if(Integer.parseInt((String)listScore.get(k).get(1))<Integer.parseInt((String)listScore.get(j).get(1))){
                    i --;
                }

                ScoreList += "<@"+listScore.get(k).get(0)+">   " + listScore.get(k).get(1) + " " + getMedal(i) + " "+getStateEmoji(Integer.parseInt((String) listScore.get(k).get(2))) + " \n";
            }

            new SentMessageRoulette(event,"Score Table",ScoreList,"", Color.WHITE,"SCORE LIST");


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @author BeatryxB
     * @version 3.0
     * Give the medal for the first, second and third players
     * @param medal Give the ranking of people
     * @return The medal emoji
     */
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

    /**
     * @author BeatryxB
     * @version 3.0
     * Give the life's state
     * @param state Give the life's state from the database
     * @return The emoji of the player's state
     */
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
