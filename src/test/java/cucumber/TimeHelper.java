package cucumber;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class TimeHelper {
    private final TimeCatApp timeCatApp;

    //@author  Benjamin Fríðberg - s224347
    public TimeHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    //@author  Benjamin Fríðberg - s224347
    public void registerTime(String projectID, String activityID, Calendar date, double hoursSpent) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("USR");
        }
        timeCatApp.registerTime(projectID,activityID,date,hoursSpent);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
}
