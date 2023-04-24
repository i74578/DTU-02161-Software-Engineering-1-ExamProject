package timeCat.Domain;

import timeCat.Application.InvalidActivityNameException;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class Activity {
    private String name;
    private int hourEstimate;
    private int startWeek;
    private int weekDuration;
    private int activityID;
    private ArrayList<Employee> assignedEmployees;
    private Timesheet timesheet;

    public Activity(String activityName,int activityID) throws InvalidActivityNameException {
        if (activityName.length() == 0){throw new InvalidActivityNameException("Invalid activity name");}
        this.name = activityName;
        this.assignedEmployees = new ArrayList<>();
        this.timesheet = new Timesheet();
        this.activityID = activityID;
    }

    public String getName(){
        return name;
    }

    public int getActivityID(){return activityID;}

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
