package CoreDiscordBot.FABDate;

public class Days {

    private String NameDayCurrent;
    private String NameDayOfficial;


    public Days(String NameDayCurrent, String NameDayOfficial) {
        this.NameDayCurrent = NameDayCurrent;
        this.NameDayOfficial = NameDayOfficial;

    }

    public String getNameDayCurrent() {
        return NameDayCurrent;
    }

    public String getNameDayOfficial() {
        return NameDayOfficial;
    }
}
