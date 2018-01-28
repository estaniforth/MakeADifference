package bitsplease.makeadifference;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.HashMap;

/**
 * Created by Emma on 1/28/18.
 */

@IgnoreExtraProperties
public class User {

    public String email;
    public String name;
    public HashMap<String, Goal> goals;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        goals = new HashMap<String, Goal>();
    }

    public User(String email, String name, HashMap<String, Goal> goals) {
        this.email = email;
        this.name = name;
        this.goals = goals;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Goal> getGoals() {
        return goals;
    }

    public void addGoal(Goal newGoal) {
        goals.put(newGoal.getName(), newGoal);
    }

}