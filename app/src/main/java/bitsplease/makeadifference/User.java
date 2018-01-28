package bitsplease.makeadifference;

/**
 * Created by Emma on 1/28/18.
 */

public class User {

    public String username;
    public String email;
    public String uid;
    public String name;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String uid, String name) {
        this.username = username;
        this.email = email;
        this.uid = uid;
        this.name = name;
    }

}