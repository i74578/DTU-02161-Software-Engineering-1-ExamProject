package timeCat.domain;

import timeCat.app.InvalidProjectNameException;

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

    //@author  Benjamin Fríðberg - s224347
    public Project(String name, String projectID) throws InvalidProjectNameException {
        if (name.length() == 0){
            throw new InvalidProjectNameException("Invalid project name");
        }
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
