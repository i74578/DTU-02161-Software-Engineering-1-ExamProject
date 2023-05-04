package timeCat.domain;

import timeCat.exceptions.InvalidNameException;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class Activity{
    private final String name;
    private final String activityID;
    private final Timesheet timesheet;
    private final ArrayList<Employee> assignedEmployees;
    private int startWeek;
    private int hourEstimate;
    private int weekDuration;

    //@author  Benjamin Fríðberg - s224347
    public Activity(String activityName,String activityID) throws InvalidNameException {
        if (activityName.length() == 0){throw new InvalidNameException("Invalid activity name");}
        this.name = activityName;
        this.activityID = activityID;
        this.timesheet = new Timesheet();
        this.assignedEmployees = new ArrayList<>();

    }

    public String getName(){
        return name;
    }

    public String getActivityID(){return activityID;}

    public Timesheet getTimesheet(){
        return timesheet;
    }

    public ArrayList<Employee> getAssignedEmployees(){
        return assignedEmployees;
    }
    public int getStartWeek(){
        return startWeek;
    }
    public int getHourEstimate(){
        return hourEstimate;
    }
    public int getWeekDuration(){
        return weekDuration;
    }
}
