package timeCat.domain;

import java.util.ArrayList;
import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class Project {
    String name;
    String projectID;
    Employee pm;
    Calendar startTimestamp;
    Calendar finishTimestamp;
    ArrayList<Activity> activities;

    public Project(String name, String projectID){
        this.name = name;
        this.projectID = projectID;
        this.activities = new ArrayList<Activity>();
    }

    public String getID(){
        return projectID;
    }

    public String getName(){
        return name;
    }

    public Employee getPM(){
        return pm;
    }

    public ArrayList<Activity> getActivities(){
        return activities;
    }
}
