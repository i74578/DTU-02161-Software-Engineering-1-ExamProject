package timeCat.Domain;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class Activity {
    String name;
    int hourEstimate;
    int startWeek;
    int weekDuration;
    ArrayList<Employee> assignedEmployees;
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
}
