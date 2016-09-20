package bo.edu.ucbcba.videoclub.controller;

/**
 * Created by privado on 20/06/2016.
 */
public class Session {

    public String username;
    public int usertype;

    static Session currentSession;

    public static Session getSession(){
        if (currentSession == null) {
            currentSession = new Session();
        }
        return currentSession;
    }

    private Session(){
        username = " ";
        usertype = 0;
    }

    public void Logout(){
        this.username = " ";
        this.usertype=0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUtype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
