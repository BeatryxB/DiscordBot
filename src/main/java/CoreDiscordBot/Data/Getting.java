package CoreDiscordBot.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Getting extends Connect {


    public Getting() throws SQLException, ClassNotFoundException {
    }

    public int getUserIdByIdDiscord(String idDiscord) throws SQLException {
        int id = -1;
        String select = "SELECT id_user FROM user_general WHERE id_discord ='" + idDiscord + "'";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public int getIdLastUserPlayInLife() throws SQLException {
        int id = -1;
        String select = "SELECT user_general.id_user FROM shots  " +
                "INNER JOIN user_general ON shots.id_user = user_general.id_user " +
                "INNER JOIN user_states ON user_general.id_user = user_states.id_user " +
                "INNER JOIN state ON user_states.id_state = state.id_state " +
                "WHERE state.id_state=1 OR state.id_state=2 ORDER BY shots.id_shot DESC LIMIT 1";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public String getPseudoLastUserPlayInLife() throws SQLException {
        String pseudo = null;
        String select = "SELECT user_general.pseudo FROM shots  " +
                "INNER JOIN user_general ON shots.id_user = user_general.id_user " +
                "INNER JOIN user_states ON user_general.id_user = user_states.id_user " +
                "INNER JOIN state ON user_states.id_state = state.id_state " +
                "WHERE state.id_state=1 OR state.id_state=2 ORDER BY shots.id_shot DESC LIMIT 1";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            pseudo = rs.getString(1);
        }
        return pseudo;
    }

    public String getDiscordIdLastUserPlayInLife() throws SQLException {
        String id = null;
        String select = "SELECT user_general.id_discord FROM shots  " +
                "INNER JOIN user_general ON shots.id_user = user_general.id_user " +
                "INNER JOIN user_states ON user_general.id_user = user_states.id_user " +
                "INNER JOIN state ON user_states.id_state = state.id_state " +
                "WHERE state.id_state=1 OR state.id_state=2 ORDER BY shots.id_shot DESC LIMIT 1";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            id = rs.getString(1);
        }
        return id;
    }

    public boolean checkIfPeopleShot() throws SQLException {
        int shot = -1;
        String select = "SELECT id_shot FROM shots";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            shot = rs.getInt(1);
        }
        return shot == -1;
    }

    public int getStateOfPeoplePlayById(String idDiscord) throws SQLException {
        int condition = -1;
        String select = "SELECT id_state FROM user_states " +
                "INNER JOIN user_general ON user_states.id_user = user_general.id_user " +
                "WHERE user_general.id_discord = "+ idDiscord +";";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            condition = rs.getInt(1);
        }
        return condition;
    }

    public int getNbReviveTryPeoplePlayByIdDiscord(String idDiscord) throws SQLException {
        int nbTryRevive = -1;
        String select = "SELECT nb_try_revive FROM user_states " +
                "INNER JOIN user_general ON user_states.id_user = user_general.id_user " +
                "WHERE user_general.id_discord = "+ idDiscord +" ;";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            nbTryRevive = rs.getInt(1);
        }
        return nbTryRevive;
    }

    public ArrayList<ArrayList<Object>> getScoreList() throws SQLException {
        ArrayList<ArrayList<Object>> listScore= new ArrayList<>();
        String select = "SELECT user_general.id_discord, scores.score, user_states.id_state FROM user_general " +
                "INNER JOIN scores ON user_general.id_user = scores.id_user " +
                "INNER JOIN user_states ON user_general.id_user = user_states.id_user " +
                "ORDER BY score DESC;";
        ResultSet rs = state.executeQuery(select);
        while (rs.next()) {
            ArrayList<Object> user= new ArrayList<>();
            user.add(rs.getString(1));
            user.add(rs.getString(2));
            user.add(rs.getString(3));
            listScore.add(user);
        }
        return listScore;
        }


        public ArrayList<Object> getStatsPeople(String idDiscord) throws SQLException {
        ArrayList<Object> Stats = new ArrayList<>();
        int id = 0;
        String select = "SELECT scores.score, scores.suicide, user_general.id_user FROM scores " +
                    "INNER JOIN user_general ON scores.id_user = user_general.id_user  " +
                    "WHERE user_general.id_discord = " + idDiscord +";";
            ResultSet rs = state.executeQuery(select);
            while (rs.next()) {
                Stats.add(rs.getString(1));
                Stats.add(rs.getString(2));
                id =rs.getInt(3);
            }
            String select2 = "SELECT COUNT(*) FROM shots " +
                    "WHERE id_user = " + id +" AND MONTH(NOW());";
            ResultSet rs2 = state.executeQuery(select2);
            while (rs2.next()) {
                Stats.add(rs2.getInt(1));
            }
            state.close();
            connect.close();
        return Stats;
        }

}
