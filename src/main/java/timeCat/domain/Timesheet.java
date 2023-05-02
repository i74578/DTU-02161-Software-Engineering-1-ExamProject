package timeCat.domain;

import java.util.ArrayList;

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
}
