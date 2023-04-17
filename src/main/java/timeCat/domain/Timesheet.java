package timeCat.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.stream.Collectors;

public class Timesheet {
    ArrayList<TimesheetEntry> entries;

    public Timesheet(){
        entries = new ArrayList<TimesheetEntry>();
    }

    public void addEntry(TimesheetEntry entry){
        entries.add(entry);
    }

    public void editHoursSpent(TimesheetEntry entry, int hoursSpent){
        entry.editHoursSpent(hoursSpent);
    }

    public void editDate(TimesheetEntry entry, Calendar date){
        entry.editDate(date);
    }

    public List<TimesheetEntry> getByDate(Calendar date){
        return entries.stream().filter(c -> isSameDay(c.getDate(),date)).collect(Collectors.toList());
    }

    public List<TimesheetEntry> getByEmployee(Employee employee){
        return entries.stream().filter(c -> c.getEmployee() == employee).collect(Collectors.toList());
    }

    public double getTotalHours(){
        double runningTotal = 0;
        for (TimesheetEntry entry : entries){
            runningTotal += entry.getHoursSpent();
        }
        return runningTotal;
    }

    public boolean isSameDay(Calendar a, Calendar b){
        return a.get(Calendar.YEAR) == b.get(Calendar.YEAR) && a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR);
    }
}
