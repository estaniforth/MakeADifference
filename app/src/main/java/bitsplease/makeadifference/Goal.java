package bitsplease.makeadifference;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Emma on 1/28/18.
 */

@IgnoreExtraProperties
public class Goal {

    public int tasks;
//    public String uid;
//    public String name;
//    public Map<String, Goal> goals;

    public Goal() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Goal(int tasks) {
        this.tasks = tasks;
//        this.uid = uid;
//        this.name = name;
//        this.goals = goals;
    }

    public int getTasks() {
        return tasks;
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String[] getGoals() {
//        return goals;
//    }

}