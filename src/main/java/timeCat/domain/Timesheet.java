package timeCat.domain;

import java.util.ArrayList;
import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class Timesheet {
    private final ArrayList<TimesheetEntry> entries;

    //@author  Benjamin Fríðberg - s224347
    public Timesheet(){
        entries = new ArrayList<>();
    }

    //author: Lukas Halberg - s216229
    public void add(Calendar date, Employee employee, double hoursSpent) throws IllegalArgumentException {
        if(hoursSpent < 0){
            throw new IllegalArgumentException("Can't register negative hours");
        }
        if(hoursSpent > 24){
            throw new IllegalArgumentException("Can't register more than 24 hours a day");
        }
        entries.add(new TimesheetEntry(date, employee, hoursSpent ));
    }

    public int getEntryCount() {
        return entries.size();
    }

    public ArrayList<TimesheetEntry> getEntries(){return entries;}

}
