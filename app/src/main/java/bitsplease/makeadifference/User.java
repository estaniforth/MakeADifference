package bitsplease.makeadifference;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.Map;

/**
 * Created by Emma on 1/28/18.
 */

@IgnoreExtraProperties
public class User {

    public String email;
    public String name;
    public Map<String, Goal> goals;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String name, Map<String, Goal> goals) {
        this.goals = goals;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Map<String, Goal> getGoals() {
        return goals;
    }

}