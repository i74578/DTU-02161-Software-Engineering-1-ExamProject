package timeCat.Domain;

import timeCat.Exceptions.InvalidNameException;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class Activity implements Tabelify {
    private String name;
    private int hourEstimate;
    private int startWeek;
    private int weekDuration;
    private String activityID;
    private ArrayList<Employee> assignedEmployees;
    private Timesheet timesheet;

    public Activity(String activityName,String activityID) throws InvalidNameException {
        if (activityName.length() == 0){throw new InvalidNameException("Invalid activity name");}
        this.name = activityName;
        this.assignedEmployees = new ArrayList<>();
        this.timesheet = new Timesheet();
        this.activityID = activityID;
    }

    public String getName(){
        return name;
    }

    public String getActivityID(){return activityID;}

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

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {String.valueOf(activityID), name};
    }
}
