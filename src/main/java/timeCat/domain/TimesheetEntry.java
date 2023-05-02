package timeCat.domain;

import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class TimesheetEntry {
    private Calendar date;
    private double hoursSpent;
    private Employee employee;

    //@author  Benjamin Fríðberg - s224347
    public TimesheetEntry(Calendar date, Employee employee, double hoursSpent){
        this.date = date;
        this.employee = employee;
        this.hoursSpent = hoursSpent;
    }

}
