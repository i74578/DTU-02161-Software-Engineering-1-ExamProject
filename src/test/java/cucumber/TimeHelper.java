package cucumber;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;
import timeCat.domain.Project;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class TimeHelper {
    private TimeCatApp timeCatApp;

    //@author  Benjamin Fríðberg - s224347
    public TimeHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    //@author  Benjamin Fríðberg - s224347
    public void registerTime(String projectID, String activityID, Calendar date, double hoursSpent) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.registerTime(projectID,activityID,date,hoursSpent);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
}
