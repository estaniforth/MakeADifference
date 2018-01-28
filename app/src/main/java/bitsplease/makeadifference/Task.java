package bitsplease.makeadifference;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Emma on 1/28/18.
 */

@IgnoreExtraProperties
public class Task {

    public String name;
    public String description;
    public int time;

    public Task() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Task(String name, String description, int time) {
        this.name = name;
        this.description = description;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTime() {
        return time;
    }

}