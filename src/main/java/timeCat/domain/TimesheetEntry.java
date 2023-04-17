package timeCat.domain;

import java.util.Calendar;

public class TimesheetEntry {
    private Calendar date;
    private double hoursSpent;
    private Employee employee;

    public TimesheetEntry(Calendar date, Employee employee, double hoursSpent){
        this.date = date;
        this.employee = employee;
        this.hoursSpent = hoursSpent;
    }

    public void editDate(Calendar date){
        this.date = date;
    }

    public void editHoursSpent(double hoursSpent){
        this.hoursSpent = hoursSpent;
    }

    public Employee getEmployee(){
        return employee;
    }

    public Calendar getDate(){
        return date;
    }
    public double getHoursSpent(){
        return hoursSpent;
    }
}
