//author: Christian Colberg - s224343
package cucumber;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.application.*;
import timeCat.domain.*;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ActivitySteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private ActivityHelper activityHelper;
    private ProjectHelper projectHelper;
    private Activity activity;
    private ArrayList<Activity> activityList;

    public ActivitySteps(TimeCatApp timeCatApp, ErrorMessage errorMessage,ActivityHelper activityHelper, ProjectHelper projectHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.activityHelper = activityHelper;
        this.projectHelper = projectHelper;
        this.activityList = new ArrayList<>();
    }

    //#author: Christian Colberg - s224343
    @When("the employee creates an activity with a blank name in the project")
    public void theEmployeeCreatesAnActivityWithABlankNameInTheProject()  {
        try {
            String projectID = projectHelper.getProject().getID();
            timeCatApp.createActivity("", projectID);
        } catch (NotFoundException | InvalidNameException | DuplicateException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee creates an activity id {string} in the project with id {string}")
    public void theEmployeeCreatesAnActivityIdInTheProjectWithId(String arg0, String arg1) {
    }


    //#author: Christian Colberg - s224343
    @When("a employee removes an activity from project {string}")
    public void aEmployeeRemovesAnActivityFromProject(String projectName) {
        try {
            Project project = projectHelper.getProjectByName(projectName);
            timeCatApp.removeActivity("", project.getID());
        }
        catch(NotFoundException | NotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @And("the activity has no registered hours")
    public void theActivityHasNoRegisteredHours() throws Exception {
        assertEquals(activity.getTimesheet().getEntryCount(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the activity has no assigned employees")
    public void theActivityHasNoAssignedEmployees() throws Exception {
        assertTrue(activity.getAssignedEmployees().isEmpty());
    }

    //#author: Christian Colberg - s224343
    @And("the activity is not in the project")
    public void aActivityIsNotInTheProject() throws Exception {
        ArrayList<Activity> activitiesInProject = timeCatApp.getProjectActivities(projectHelper.getProject().getID());
        activity = activityHelper.getActivity();
        String activityID = activity.getActivityID();
        assertFalse(activitiesInProject.stream().anyMatch(p -> p.getActivityID().equals(activityID)));
    }
    //#author: Christian Colberg - s224343
    @When("the employee creates the activity in the project")
    public void theEmployeeCreatesTheActivityInTheProject() throws Exception {
        try {
            activity = timeCatApp.createActivity(activity.getName(), projectHelper.getProject().getID());
            activityHelper.setActivity(activity);
        } catch(DuplicateException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
        activity = activityHelper.getActivity();

    }
    //#author: Christian Colberg - s224343
    @Then("the activity is in the project")
    public void theActivityIsInTheProject() throws Exception {
        ArrayList<Activity> activitiesInProject = timeCatApp.getProjectActivities(projectHelper.getProject().getID());
        String activityID = activity.getActivityID();
        assertTrue(activitiesInProject.stream().anyMatch(p -> p.getActivityID().equals(activityID)));
    }
    //#author: Christian Colberg - s224343
    @And("an activity is in the project")
    public void anActivityIsInTheProject() throws Exception {
        activity = activityHelper.createActivity("Test Activity",projectHelper.getProject().getID());
    }
    //#author: Christian Colberg - s224343
    @When("the employee creates an activity with the name {string} in the project")
    public void theEmployeeCreatesAnActivityWithTheNameInTheProject(String activityName) {
        try {
            timeCatApp.createActivity(activityName, projectHelper.getProject().getID());
        } catch (NotFoundException | InvalidNameException | DuplicateException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //#author: Christian Colberg - s224343
    @When("a employee removes the activity from the project")
    public void aEmployeeRemovesTheActivityFromTheProject() {
        try {
            Project project = projectHelper.getProject();
            activity = activityHelper.getActivity();
            timeCatApp.removeActivity(activity.getActivityID(), project.getID());
        } catch(NotAllowedException | NotFoundException | InvalidNameException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //#author: Christian Colberg - s224343
    @When("the employee removes the activity from the project")
    public void theEmployeeRemovesTheActivityFromTheProject() throws Exception {
        timeCatApp.removeActivity(activity.getActivityID(),projectHelper.getProject().getID());
    }

    @When("the employee requests a activity list for the project")
    public void theEmployeeRequestsAActivityListForTheProject() {
        try {
            activityList = timeCatApp.getProjectActivities(projectHelper.getProject().getID());
        } catch (InvalidNameException | NotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("there are {int} activities on the activity list")
    public void thereAreActivitiesOnTheActivityList(int acitivityCount) {
        assertEquals(activityList.size(),acitivityCount);
    }

    @And("the activity is in the list")
    public void theActivityIsInTheList() throws InvalidNameException{
        activity = activityHelper.getActivity();
        assertTrue(activityList.stream().anyMatch(p -> p.equals(activity)));
    }
}