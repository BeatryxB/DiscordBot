package CoreDiscordBot.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adding extends Connect{

    Statement st;
    public Adding() throws SQLException, ClassNotFoundException {
        super();
    }



    public void addUser(String idDiscord, String Pseudo) throws SQLException, ClassNotFoundException {
        String Insert = "INSERT INTO user_general(id_discord, pseudo) VALUES ('"+idDiscord+"','"+Pseudo+"')";
        PreparedStatement PrepareToInsert = connect.prepareStatement(Insert,Statement.RETURN_GENERATED_KEYS);
        PrepareToInsert.execute();
        addUserParameters(idDiscord);
        connect.close();
    }


    private void addUserParameters(String idDiscord) throws SQLException, ClassNotFoundException {
        String id = String.valueOf(new Getting().getUserIdByIdDiscord(idDiscord));
        String Insert = "INSERT INTO scores(id_user, score, suicide) VALUES ("+ id +",'0','0');";
        String Insert2 = "INSERT INTO user_states(id_user,id_state,last_date_play,nb_try_revive) VALUES ("+ id +",1,NOW(),'3');";
        PreparedStatement PrepareToInsert = connect.prepareStatement(Insert,Statement.RETURN_GENERATED_KEYS);
        PrepareToInsert.execute();
        PrepareToInsert.close();
        PreparedStatement PrepareToInsert2 = connect.prepareStatement(Insert2,Statement.RETURN_GENERATED_KEYS);
        PrepareToInsert2.execute();
        PrepareToInsert.close();
    }

    public void addShot(String idDiscord,int result) throws SQLException, ClassNotFoundException {
        String id = String.valueOf(new Getting().getUserIdByIdDiscord(idDiscord));
        String Insert = "INSERT INTO shots(id_user, result, date) VALUES ("+ id +","+result+",NOW());";
        PreparedStatement PrepareToInsert = connect.prepareStatement(Insert,Statement.RETURN_GENERATED_KEYS);
        new Modifying().majLastDatePlay(new Getting().getUserIdByIdDiscord(idDiscord));
        PrepareToInsert.execute();
        PrepareToInsert.close();
        connect.close();
    }
}
