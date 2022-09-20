package CoreDiscordBot.Play.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class User implements Comparable<User>{

    private String idUser, pseudo, dateDie, lastDatePlay;
    private int score, tryRevive, suicide, nbShot;
    private boolean inLife, wasRevive, scoreUpdate;

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
        this.inLife = true;
        this.wasRevive = false;
        this.tryRevive = 3;
        this.scoreUpdate = false;
        this.dateDie = null;
        this.lastDatePlay = null;
        this.suicide = 0;
        this.nbShot = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isInLife() {
        return inLife;
    }

    public boolean isWasRevive() {
        return wasRevive;
    }

    public void hasKilled(){
        this.score += 1;
    }

    public void beKilled(){
        this.inLife = false;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = new Date(System.currentTimeMillis());
        if(!Objects.equals(this.dateDie, formatter.format(date))){
            this.wasRevive = false;
        }
        this.dateDie = formatter.format(date);
    }

    public void Suicided(){
        this.suicide++;
    }

    public int getNbSuicide() {
        return suicide;
    }

    public void addShot(){
        this.nbShot++;
    }

    public int getNbShot() {
        return nbShot;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDateDie(String dateDie) {
        this.dateDie = dateDie;
    }

    public void setLastDatePlay(String lastDatePlay) {
        this.lastDatePlay = lastDatePlay;
    }

    public void setSuicide(int suicide) {
        this.suicide = suicide;
    }

    public void setNbShot(int nbShot) {
        this.nbShot = nbShot;
    }

    public void setInLife(boolean inLife) {
        this.inLife = inLife;
    }

    public void setWasRevive(boolean wasRevive) {
        this.wasRevive = wasRevive;
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
            this.inLife = true;
        }
    }

    public boolean hasDieToday(){
        if(this.dateDie != null){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH");
            Date date = new Date(System.currentTimeMillis());
            String dateToday = formatter.format(date);
            if(!Objects.equals(this.lastDatePlay, dateToday)){
                this.tryRevive = 3;
                this.lastDatePlay = dateToday;
            }
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

    public boolean hasPlayToday(){
        if(this.lastDatePlay!=null){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH");
            Date date = new Date(System.currentTimeMillis());
            String dateToday = formatter.format(date);
            return Objects.equals(this.lastDatePlay, dateToday);
        }
        else{
            return false;
        }
    }

    public void playToday(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = new Date(System.currentTimeMillis());
        String dateToday = formatter.format(date);
        lastDatePlay = dateToday;
    }



    public void forceRevive(){
        this.inLife = true;
        this.wasRevive = false;
        this.tryRevive = 3;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", score=" + score +
                ", inlife=" + inLife +
                ", revive=" + wasRevive +
                ", lastDatePlay=" + lastDatePlay +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return (Integer.compare(this.getScore(), o.getScore()));
    }
}
