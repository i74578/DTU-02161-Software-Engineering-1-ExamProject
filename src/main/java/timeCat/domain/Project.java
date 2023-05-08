package timeCat.domain;

import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotFoundException;
import timeCat.exceptions.DuplicateException;

import java.util.ArrayList;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class Project {
    private final String name;
    private final String projectID;
    private Employee pm;
    private final ArrayList<Activity> activities;

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
    public Activity getActivityByID(String activityID) throws NotFoundException {
        Optional<Activity> FoundActivity = activities.stream().filter(p -> p.getActivityID().equals(activityID)).findFirst();
        if (FoundActivity.isPresent()){
            return FoundActivity.get();
        }
        throw new NotFoundException("The activity is not found");
    }

    //@author Lukas Halberg - s216229
    public boolean hasActivity(String activityName){
        return activities.stream().anyMatch(p -> p.getName().equals(activityName));
    }

    //@author  Benjamin Fríðberg - s224347
    //DBC - Christian Colberg - s224343
    public void addActivity(Activity activityToAdd) throws DuplicateException {

    // Design by Contract
        // Preconditions
        // assert activityToAdd != null: "Assertion error, invalid activity";
        // assert !activities.contains(activityToAdd): "Duplicate activity";

        if(!hasActivity(activityToAdd.getName())){
            activities.add(activityToAdd);
            return;
        }
        throw new DuplicateException("Duplicate activity");

    // Postconditions
        //assert activities.contains(activityToAdd):"The activity was not added successfully";
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeActivity(String activityID) throws NotFoundException {
        Activity projectToRemove = getActivityByID(activityID);
        activities.remove(projectToRemove);
    }

    //@author  Benjamin Fríðberg - s224347
    public void assignPM(Employee employee) {
        pm = employee;
    }

    //@author  Benjamin Fríðberg - s224347
    public void deassignPM() {
        pm = null;
    }
}