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

    //@author  Benjamin Fríðberg - s224347
    public void editDate(Calendar date){
        this.date = date;
    }

    //@author  Benjamin Fríðberg - s224347
    public void editHoursSpent(double hoursSpent){
        this.hoursSpent = hoursSpent;
    }

    //@author  Benjamin Fríðberg - s224347
    public Employee getEmployee(){
        return employee;
    }

    //@author  Benjamin Fríðberg - s224347
    public Calendar getDate(){
        return date;
    }

    //@author  Benjamin Fríðberg - s224347
    public double getHoursSpent(){
        return hoursSpent;
    }
}
