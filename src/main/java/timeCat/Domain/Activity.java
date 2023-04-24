package timeCat.Domain;

import timeCat.Application.InvalidActivityNameException;
import timeCat.Application.InvalidProjectNameException;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class Activity {
    String name;
    int hourEstimate;
    int startWeek;
    int weekDuration;
    int activityID;
    ArrayList<Employee> assignedEmployees;
    Timesheet timesheet;


    public Activity(String activityName,int activityID) throws InvalidActivityNameException {
        this.name = activityName;
        assignedEmployees = new ArrayList<Employee>();
        timesheet = new Timesheet();
        if (name.length() == 0){
            throw new InvalidActivityNameException("Invalid activity name");}
    }

    public String getName(){
        return name;
    }

    public int getActivityID(){return activityID;
    }

    public int getStartWeek(){
        return startWeek;
    }

    public ArrayList<Employee> getAssignedEmployees(){
        return assignedEmployees;
    }

    public int getHourEstimate(){
        return hourEstimate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public Timesheet getTimesheet(){
        return timesheet;
    }
}
