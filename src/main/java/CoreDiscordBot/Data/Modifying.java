package CoreDiscordBot.Data;

import java.sql.SQLException;

public class Modifying extends Connect{
    public Modifying() throws SQLException, ClassNotFoundException {

    }

    public void killLastPeopleInLife(int idUserDie, int idUserKiller) throws SQLException, ClassNotFoundException {

        int lifeCondition = new Getting().getStateOfPeoplePlayById(new Getting().getDiscordIdLastUserPlayInLife());

        if(lifeCondition==1){
            String update1 = "UPDATE user_states SET id_state = 4 WHERE id_user = "+idUserDie+"";
            state.executeUpdate(update1);
        }else if(lifeCondition==2){
            String update1 = "UPDATE user_states SET id_state = 3 WHERE id_user = "+idUserDie+"";
            state.executeUpdate(update1);
        }
        String update2 = "UPDATE scores SET score = score + 1 WHERE id_user = "+idUserKiller+"";
        state.executeUpdate(update2);
        if(idUserDie==idUserKiller){
            String update3 = "UPDATE scores SET suicide = suicide + 1 WHERE id_user = "+idUserKiller+"";
            state.executeUpdate(update3);
        }
        state.close();
        connect.close();
    }

    public void downTryRevivePeoplePlayById(int idUserPlay) throws SQLException {
        String update1 = "UPDATE user_states SET nb_try_revive = nb_try_revive - 1 WHERE id_user = "+idUserPlay+"";
        state.executeUpdate(update1);
        state.close();
        connect.close();
    }

    public void revivePeoplePlay(int idUserPlay) throws SQLException {
        String update1 = "UPDATE user_states SET id_state = 2 WHERE id_user = "+idUserPlay+"";
        state.executeUpdate(update1);
        state.close();
        connect.close();
    }

    public void noHopePeoplePlay(int idUserPlay) throws SQLException {
        String update1 = "UPDATE user_states SET id_state = 3 WHERE id_user = "+idUserPlay+"";
        state.executeUpdate(update1);
        state.close();
        connect.close();
    }

    public void majLastDatePlay (int idUserPlay) throws SQLException {
        String update1 = "UPDATE user_states SET last_date_play = NOW() WHERE id_user = "+idUserPlay+"";
        state.executeUpdate(update1);
    }
}