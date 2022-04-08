package CoreDiscordBot.FABDate;

import java.util.ArrayList;

public class Weeks {

    private ArrayList<Days> Weeks;

    public ArrayList<Days> getWeeks() {
        return Weeks;
    }

    public Weeks() {
        Weeks = new ArrayList<>(LoadWeek());
    }

    private ArrayList<Days> LoadWeek(){
        ArrayList<Days> weekFormat = new ArrayList<>();
        weekFormat.add(new Days("Lunjo","Lundijoai"));
        weekFormat.add(new Days("Boskijo","Boskijoai"));
        weekFormat.add(new Days("Kukijo","Kukijoai"));
        weekFormat.add(new Days("Owojo","Owojoai"));
        weekFormat.add(new Days("Arbrejo","Arbijoai"));
        weekFormat.add(new Days("Uwujo","Uwujoai"));
        weekFormat.add(new Days("Lumijo","Lumijoai"));
        weekFormat.add(new Days("Aborajo","Aborajoai"));
        return weekFormat;
    }

    public Days getDays(int i) {
        return this.getWeeks().get(i);
    }
}
