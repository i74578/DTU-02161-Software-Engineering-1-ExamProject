package timeCat.Domain;

import timeCat.Application.ActivityNotFoundException;
import timeCat.Application.InvalidProjectNameException;
import timeCat.Application.ProjectNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class Project implements Tabelify {
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

    //@author  Benjamin Fríðberg - s224347
    public String[] getMainProperties(){
        return new String[] {projectID, name};
    }
    public void addActivity(Activity activityToAdd){
        activities.add(activityToAdd);
    }
    public Activity getActivityByName (String activityName) throws ActivityNotFoundException {
        Optional<Activity> FoundActivity = activities.stream().filter(p -> p.getName().equals(activityName)).findFirst();
        if (!FoundActivity.isEmpty()){
            return FoundActivity.get();
        }
        throw new ActivityNotFoundException("The activity is not found");
    }
    public boolean activityExists(String activityName){
        Optional<Activity> FoundActivity = activities.stream().filter(p -> p.getName().equals(activityName)).findFirst();
        return !FoundActivity.isEmpty();
    }
}
