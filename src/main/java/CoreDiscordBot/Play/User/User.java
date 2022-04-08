package CoreDiscordBot.Play.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class User implements Comparable<User>{

    private String idUser, pseudo, dateDie;
    private int score, tryRevive;
    private boolean inlife,wasRevive, scoreUpdate;

    public boolean isScoreUpdate() {
        return scoreUpdate;
    }

    public void setScoreUpdate(boolean scoreUpdate) {
        this.scoreUpdate = scoreUpdate;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getPseudo() {
        return pseudo;
    }

    public User(String idUser, String pseudo) {
        this.idUser = idUser;
        this.pseudo = pseudo;
        this.score = 0;
        this.inlife = true;
        this.wasRevive = false;
        this.tryRevive = 3;
        this.scoreUpdate = false;
        this.dateDie = null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isInlife() {
        return inlife;
    }

    public boolean isWasRevive() {
        return wasRevive;
    }

    public void hasKilled(){
        this.score += 1;
    }

    public void beKilled(){
        this.inlife = false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        this.dateDie = formatter.format(date);
    }

    public int getTryRevive() {
        return tryRevive;
    }

    public void setTryRevive(int tryRevive) {
        this.tryRevive = tryRevive;
    }

    public void revive(){
        if(!this.wasRevive){
            this.wasRevive = true;
            this.inlife = true;
        }
    }

    public boolean hasdieToday(){
        if(this.dateDie != null){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String dateToday = formatter.format(date);
            if(Objects.equals(this.dateDie, dateToday)){
                return true;
            }else{
                forceRevive();
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void forceRevive(){
        this.inlife = true;
        this.wasRevive = false;
        this.tryRevive = 3;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", score=" + score +
                ", inlife=" + inlife +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return (Integer.compare(this.getScore(), o.getScore()));
    }
}
