package bitsplease.makeadifference;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;

/**
 * Created by Emma on 1/28/18.
 */

@IgnoreExtraProperties
public class Goal {

    public String name;
    public int numTasks;
    public ArrayList<Task> tasks;

    public Goal() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        name = "Goal";
        numTasks = 0;
        tasks = new ArrayList<>();
    }

    public Goal(String name, int timeStart, int timeEnd) {
        this.name = name;
        this.tasks = calcTasks(timeStart, timeEnd);
        this.numTasks = tasks.size();
    }

    public String getName() {
        return name;
    }

    public int getNumTasks() {
        return numTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> calcTasks(int timeStart, int timeEnd) {
        ArrayList<Task> tasks = new ArrayList<>();
        int i = 1;
        while (timeStart < timeEnd && (15 * i) < (timeStart - timeEnd)) {
            tasks.add(new Task(name, "description", 15 * i + timeStart));
            timeStart += 15 * i;
            if (15 * (i + 1) <= 120) {
                i++;
            }
        }
        numTasks = tasks.size();
        return tasks;
    }

}