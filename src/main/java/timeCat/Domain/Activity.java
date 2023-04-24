package timeCat.Domain;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class Activity {
    String name;
    int hourEstimate;
    int startWeek;
    int weekDuration;
    ArrayList<Employee> assignedEmployees;
    Timesheet timesheet;

    public Activity(String activityName){
        this.name = activityName;
        assignedEmployees = new ArrayList<Employee>();
        timesheet = new Timesheet();

    }

    public String getName(){
        return name;
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
