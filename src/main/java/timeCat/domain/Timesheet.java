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

    public int getEntryCount() {
        return entries.size();
    }

    //author: Lukas Halberg - s216229
    public void add(Calendar date, Employee employee, double hoursSpent) {
        entries.add(new TimesheetEntry(date, employee, hoursSpent ));

    }
    public ArrayList<TimesheetEntry> getEntries(){return entries;}

}
