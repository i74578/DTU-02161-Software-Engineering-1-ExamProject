package timeCat.domain;

import java.util.Calendar;

public class ReportEntry extends TimesheetEntry {
    private final Project project;
    private final Activity activity;

    public ReportEntry(Project project, Activity activity, Calendar date, Employee employee, double hoursSpent) {
        super(date, employee, hoursSpent);
        this.project = project;
        this.activity = activity;
    }

    public String getProjectName(){
        return project.getName();
    }

    public String getActivityName(){
        return activity.getName();
    }
}
