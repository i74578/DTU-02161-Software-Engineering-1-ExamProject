package timeCat.Domain;

import timeCat.Exceptions.InvalidNameException;
import timeCat.Exceptions.NotFoundException;
import timeCat.Exceptions.DuplicateException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class Project implements Tabelify {
    private String name;
    private String projectID;
    private Employee pm;
    private Calendar startTimestamp;
    private Calendar finishTimestamp;
    private ArrayList<Activity> activities;

    //@author  Benjamin Fríðberg - s224347
    public Project(String name, String projectID) throws InvalidNameException {
        if (name.length() == 0){
            throw new InvalidNameException("Invalid project name");
        }
        this.name = name;
        this.projectID = projectID;
        this.activities = new ArrayList<>();
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

    //@author  Lukas Halberg - s216229
    private Activity getActivityByID(String activityID) throws NotFoundException {
        Optional<Activity> FoundActivity = activities.stream().filter(p -> p.getActivityID().equals(activityID)).findFirst();
        if (!FoundActivity.isEmpty()){
            return FoundActivity.get();
        }
        throw new NotFoundException("The activity is not found");
    }

    //@author  Lukas Halberg - s216229
    public Activity getActivityByName(String activityName) throws NotFoundException {
        Optional<Activity> FoundActivity = activities.stream().filter(p -> p.getName().equals(activityName)).findFirst();
        if (!FoundActivity.isEmpty()){
            return FoundActivity.get();
        }
        throw new NotFoundException("The activity is not found");
    }

    //@author Lukas Halberg - s216229
    public boolean hasActivity(String activityName){
        return activities.stream().anyMatch(p -> p.getName().equals(activityName));
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {projectID, name};
    }

    //@author  Benjamin Fríðberg - s224347
    public void addActivity(Activity activityToAdd) throws DuplicateException {
        if(!hasActivity(activityToAdd.getName())){
            activities.add(activityToAdd);
            return;
        }
        throw new DuplicateException("Duplicate activity");
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeActivity(String activityID) throws NotFoundException {
        Activity projectToRemove = getActivityByID(activityID);
        activities.remove(projectToRemove);
    }
}