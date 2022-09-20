package CoreDiscordBot.Play.User;

public class DiceGame {

    private User user;
    private User userBefore;
    private int diceResult;

    public DiceGame(User user, User userBefore, int diceResult) {
        this.user = user;
        this.userBefore = userBefore;
        this.diceResult = diceResult;
    }

    public String play(){
        if(user.isInLife()){
            if(this.diceResult == 1){
                userBefore.beKilled();
                return "you've killed "+userBefore.getPseudo()+", but now you are the target";
            }
            else{
                return "you're shot was so predictable, now you are the target";
            }
        }
        else{
            return "You're dead, you cannot play for today";
        }
    }
}
