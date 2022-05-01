package CoreDiscordBot.Play.User;

import java.util.ArrayList;

public class ListShot {

    public static ListShot ShotList = new ListShot();
    private ArrayList<shot> listShot;

    public ListShot() {
        this.listShot = new ArrayList<>();
    }

    public void addShot(int result, User u){
        this.listShot.add(new shot(result, u));
    }


    private class shot{
        private int diceResult;
        private User userPlay;

        public shot(int diceResult, User userPlay) {
            this.diceResult = diceResult;
            this.userPlay = userPlay;
        }
    }
}
