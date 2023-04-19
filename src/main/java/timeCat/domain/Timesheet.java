package timeCat.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.stream.Collectors;

//@author  Benjamin Fríðberg - s224347
public class Timesheet {
    ArrayList<TimesheetEntry> entries;

    //@author  Benjamin Fríðberg - s224347
    public Timesheet(){
        entries = new ArrayList<TimesheetEntry>();
    }

    //@author  Benjamin Fríðberg - s224347
    public void addEntry(TimesheetEntry entry){
        entries.add(entry);
    }

    //@author  Benjamin Fríðberg - s224347
    public void editHoursSpent(TimesheetEntry entry, int hoursSpent){
        entry.editHoursSpent(hoursSpent);
    }

    //@author  Benjamin Fríðberg - s224347
    public void editDate(TimesheetEntry entry, Calendar date){
        entry.editDate(date);
    }

    //@author  Benjamin Fríðberg - s224347
    public List<TimesheetEntry> getByDate(Calendar date){
        return entries.stream().filter(c -> isSameDay(c.getDate(),date)).collect(Collectors.toList());
    }

    //@author  Benjamin Fríðberg - s224347
    public List<TimesheetEntry> getByEmployee(Employee employee){
        return entries.stream().filter(c -> c.getEmployee() == employee).collect(Collectors.toList());
    }

    //@author  Benjamin Fríðberg - s224347
    public double getTotalHours(){
        double runningTotal = 0;
        for (TimesheetEntry entry : entries){
            runningTotal += entry.getHoursSpent();
        }
        return runningTotal;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean isSameDay(Calendar a, Calendar b){
        return a.get(Calendar.YEAR) == b.get(Calendar.YEAR) && a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR);
    }
}
