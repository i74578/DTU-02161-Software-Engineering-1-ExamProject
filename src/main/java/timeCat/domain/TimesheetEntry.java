package timeCat.domain;

import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class TimesheetEntry{
    private Calendar date;
    private double hoursSpent;
    private final Employee employee;

    //@author  Benjamin Fríðberg - s224347
    public TimesheetEntry(Calendar date, Employee employee, double hoursSpent){
        this.date = date;
        this.employee = employee;
        this.hoursSpent = hoursSpent;
    }
    //author: Lukas Halberg - s216229
    public Calendar getDate() {
        return date;
    }
    //author: Lukas Halberg - s216229
    public double getHours() {
        return hoursSpent;
    }
    //author: Lukas Halberg - s216229
    public Employee getEmployee() {
        return employee;
    }

    //author: Lukas Halberg - s216229
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof TimesheetEntry)){
            return false;
        }
        TimesheetEntry timesheetEntry = (TimesheetEntry) obj;
        return  timesheetEntry.getDate().equals(date) &&
                timesheetEntry.getHours() == hoursSpent &&
                timesheetEntry.getEmployee() == employee;
    }
}
