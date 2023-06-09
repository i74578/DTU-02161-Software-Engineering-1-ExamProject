package cucumber;


import timeCat.application.TimeCatApp;
import timeCat.domain.Activity;
import timeCat.domain.Employee;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

//@author  Benjamin Fríðberg - s224347
public class ActivityHelper {
    private final TimeCatApp timeCatApp;
    private Activity activity;

    //@author  Benjamin Fríðberg - s224347
    public ActivityHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    //@author  Benjamin Fríðberg - s224347
    public Activity createActivity(String activityName, String projectID) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        Employee PM = timeCatApp.getProjectByID(projectID).getPM();
        if(loggedInEmployee == null) {
            if (PM == null) {
                timeCatApp.login("USR");
                timeCatApp.assignPM(projectID, "USR");
            } else {
                timeCatApp.login(PM.getInitials());
            }
        }
        activity = timeCatApp.createActivity(activityName,projectID);
        if(loggedInEmployee == null){
            if(PM == null){
                timeCatApp.deassignPM(projectID);
            }
            timeCatApp.logout();
        }
        return activity;
    }

    //@author  Benjamin Fríðberg - s224347
    public Activity getActivity() throws InvalidNameException {
        if(activity == null){
            activity = getTestActivity();
        }
        return activity;
    }

    //@author  Benjamin Fríðberg - s224347
    private Activity getTestActivity() throws InvalidNameException {
        return new Activity("Test Activity","A0");
    }

    //@author  Benjamin Fríðberg - s224347
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
