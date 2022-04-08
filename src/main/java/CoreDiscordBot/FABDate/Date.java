package CoreDiscordBot.FABDate;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Date {

    private java.util.Date now;
    private java.util.Date Start;

    private String CurrentDate;
    private String OfficialDate;

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getOfficialDate() {
        return OfficialDate;
    }

    public void setOfficialDate(String officialDate) {
        OfficialDate = officialDate;
    }

    public Date() {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 10);
        c1.set(Calendar.DAY_OF_MONTH, 01);
        c1.set(Calendar.YEAR, 2021);
        c1.set(Calendar.HOUR_OF_DAY, 00);
        c1.set(Calendar.MINUTE, 00);
        c1.set(Calendar.SECOND, 00);
        this.Start = c1.getTime();
        this.now = new java.util.Date();

        System.out.println(Start + "    " + now);
        calculateHour();
    }

    public void calculateHour(){
        long differenceInMillis = this.now.getTime() - this.Start.getTime();
        ArrayList<Long> formattedText = formatElapsedTime(differenceInMillis/1000);
        FormatingFABDate(formattedText);
    }

    public static java.util.Date parseDate (String strDate) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        java.util.Date date1 = null;
        try {
            date1 = dateFormat.parse (strDate);
        }
        catch (ParseException e) {
            e.printStackTrace ();
        }
        return date1;
    }

    public static ArrayList<Long> formatElapsedTime (long seconds) {

        ArrayList<Long> Format = new ArrayList<>();

        long hours = TimeUnit.SECONDS.toHours(seconds);
        seconds -= TimeUnit.HOURS.toSeconds (hours);

        long minutes = TimeUnit.SECONDS.toMinutes (seconds);
        seconds -= TimeUnit.MINUTES.toSeconds (minutes);

        Format.add(hours);
        Format.add(minutes);
        Format.add(seconds);

        return Format;
    }

    public void FormatingFABDate(ArrayList<Long> data){
        int nbDay = (int) (data.get(0)/21);
        int nbWeek = (int) (nbDay/new Weeks().getWeeks().size());
        int day = (int) (nbDay%new Weeks().getWeeks().size());
        int Hours = (int) (data.get(0)%21);
        String dayName = new Weeks().getWeeks().get(day).getNameDayCurrent();
        setCurrentDate(new Weeks().getWeeks().get(day).getNameDayCurrent()+" day "
                        + Hours + " hours " + data.get(1) + " Minutes " + data.get(2)
                + " Seconds");
        setOfficialDate(new Weeks().getWeeks().get(day).getNameDayOfficial()+" day "
                + Hours + " hours " + data.get(1) + " Minutes " + data.get(2)
                + " Seconds");
    }

}
