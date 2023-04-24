package timeCat.Domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

//@author  Benjamin Fríðberg - s224347
public class Timesheet {
    private ArrayList<TimesheetEntry> entries;

    //@author  Benjamin Fríðberg - s224347
    public Timesheet(){
        entries = new ArrayList<TimesheetEntry>();
    }

    //@author  Benjamin Fríðberg - s224347
    public int getEntryCount(){
        return entries.size();
    }

    //@author  Benjamin Fríðberg - s224347
    public void addEntry(TimesheetEntry entry){
        entries.add(entry);
    }
}
