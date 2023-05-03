//author: Christian Colberg - s224343
package cucumber;

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

    public ActivitySteps(TimeCatApp timeCatApp, ErrorMessage errorMessage,ActivityHelper activityHelper, ProjectHelper projectHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.activityHelper = activityHelper;
        this.projectHelper = projectHelper;
    }

    //#author: Christian Colberg - s224343
    @Given("a project with the name {string} is not in the project repository")
    public void aProjectWithTheNameIsNotInTheProjectRepository(String projectName) {
        assertFalse(timeCatApp.hasProject(projectName));
    }

    //#author: Christian Colberg - s224343
    @And("a activity with the name {string} is in the project {string}")
    public void anActivityWithTheNameIsInTheProject(String activityName, String projectName) throws Exception {
        String projectID = timeCatApp.getProjectByName(projectName).getID();
        activityHelper.createActivity(activityName, projectID);
    }

    //#author: Christian Colberg - s224343
    @When("the employee creates an activity with the name {string} in the project {string}")
    public void aEmployeeCreatesAnActivityWithTheNameInTheProject(String activityName, String projectName)  {
        try {
            String projectID = timeCatApp.getProjectByName(projectName).getID();
            timeCatApp.createActivity(activityName, projectID);
        } catch (NotFoundException | InvalidNameException | DuplicateException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @When("a employee removes an activity with the name {string} in the project {string}")
    public void aEmployeeRemovesAnActivityWithTheNameInTheProject(String activityName, String projectName)  {
        try {
            Project project = timeCatApp.getProjectByName(projectName);
            Activity activity = project.getActivityByName(activityName);
            timeCatApp.removeActivity(activity.getActivityID(), project.getID());
        }
        catch(NotFoundException | NotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @Then("the activity with the name {string} is not in the project {string}")
    public void the_activity_with_the_name_is_not_in_the_project(String activityName, String projectName) throws Exception {
        assertFalse(timeCatApp.getProjectByName(projectName).hasActivity(activityName));
    }

    //#author: Christian Colberg - s224343
    @Then("the activity with the name {string} is in the project {string}")
    public void theActivityWithTheNameIsInTheProject(String activityName, String projectName) throws Exception {
        this.activity = timeCatApp.getProjectByName(projectName).getActivityByName(activityName);
        assertNotNull(this.activity);
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
    @And("the startWeek is not assigned")
    public void theStartWeekIsNotAssigned() throws Exception {
        assertEquals(activity.getStartWeek(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the hourEstimate is not assigned")
    public void theHourEstimateIsNotAssigned() throws Exception {
        assertEquals(activity.getHourEstimate(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the weekDuration is not assigned")
    public void theWeekDurationIsNotAssigned() throws Exception {
        assertEquals(activity.getWeekDuration(), 0);
    }

    @And("the activity is not in the project")
    public void aActivityIsNotInTheProject() throws Exception {
        ArrayList<Activity> activitiesInProject = timeCatApp.getProjectByID(projectHelper.getProject().getID()).getActivities();
        activity = activityHelper.getActivity();
        String activityID = activity.getActivityID();
        assertFalse(activitiesInProject.stream().anyMatch(p -> p.getActivityID().equals(activityID)));
    }

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

    @Then("the activity is in the project")
    public void theActivityIsInTheProject() throws Exception {
        ArrayList<Activity> activitiesInProject = timeCatApp.getProjectByID(projectHelper.getProject().getID()).getActivities();
        String activityID = activity.getActivityID();
        assertTrue(activitiesInProject.stream().anyMatch(p -> p.getActivityID().equals(activityID)));
    }

    @And("an activity is in the project")
    public void anActivityIsInTheProject() throws Exception {
        activity = activityHelper.createActivity("Test Activity",projectHelper.getProject().getID());
    }

    @When("the employee creates an activity with the name {string} in the project")
    public void theEmployeeCreatesAnActivityWithTheNameInTheProject(String activityName) {
        try {
            timeCatApp.createActivity(activityName, projectHelper.getProject().getID());
        } catch (NotFoundException | InvalidNameException | DuplicateException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

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

    @When("the employee removes the activity from the project")
    public void theEmployeeRemovesTheActivityFromTheProject() throws Exception {
        timeCatApp.removeActivity(activity.getActivityID(),projectHelper.getProject().getID());
    }
}