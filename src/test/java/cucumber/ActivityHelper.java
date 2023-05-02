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
    private TimeCatApp timeCatApp;
    private Activity activity;

    public ActivityHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    public Activity createActivity(String activityName, String projectID) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        Employee PM = timeCatApp.getProjectByID(projectID).getPM();
        if(loggedInEmployee == null) {
            if (PM == null) {
                timeCatApp.login("ADM");
                timeCatApp.assignPM(projectID, "ADM");
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

    public Activity getActivity() throws InvalidNameException {
        if(activity == null){
            activity = getTestActivity();
        }
        return activity;
    }

    private Activity getTestActivity() throws InvalidNameException {
        Activity testActivity = new Activity("Test Activity","A0");
        return testActivity;
    }

    public String createTestActivity() throws Exception{
        return "";
    }

    public void setActivity(Activity activity) throws InvalidNameException {
        this.activity = activity;
    }
}
