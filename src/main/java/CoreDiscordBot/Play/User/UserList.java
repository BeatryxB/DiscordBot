package CoreDiscordBot.Play.User;

import java.util.*;

public class UserList {

    public static UserList userList = new UserList();

    private ArrayList<User> UserList;
    private ArrayList<User> UserTarget;
    private int nbshot;

    public UserList() {
        UserList = new ArrayList<>();
        UserTarget = new ArrayList<>();
        this.nbshot = 0;
    }

    public int getNbshot() {
        return nbshot;
    }

    public void setNbshot() {
        this.nbshot++;
    }

    public ArrayList<User> getUserTarget() {
        return UserTarget;
    }

    public ArrayList<User> getUserList() {
        return UserList;
    }

    public void setUserList(ArrayList<User> userList) {
        UserList = userList;
    }

    public boolean containsId(final String id){
        final ArrayList<User> list = UserList;
        return list.stream().anyMatch(o -> o.getIdUser().equals(id));
    }

    public User getUSerById(String id){

        Optional<User> optionalVariable = getUserList().stream().filter(element -> element.getIdUser().equals(id)).findFirst();
        User user = optionalVariable.orElseThrow(() -> new NullPointerException()); //or you use just the Optional#get method instead.
        return user;
    }

    public void addTarget(User u) {
            this.UserTarget.add(u);
    }

    public void removeTarget(User u){
        this.UserTarget.remove(u);
    }

    public User getLastTarget() {
        return this.UserTarget.get(this.UserTarget.size()-1);
    }


    @Override
    public String toString() {
        return "UserList{" +
                "UserList=" + UserList +
                "UserTarget=" + UserTarget +
                '}';
    }

    public ArrayList<User> getSortedUserByScore() {
        ArrayList<User> list = this.getUserList();
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public void razkill(){
        for(int i = 0; i<userList.getUserList().size();i++){
            userList.getUserList().get(i).forceRevive();
        }
    }

}
