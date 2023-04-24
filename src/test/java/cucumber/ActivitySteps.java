//#author: Christian Colberg - s224343
package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.*;
import timeCat.Domain.Activity;
import timeCat.Domain.CostumerProject;
import timeCat.Domain.InternalProject;
import timeCat.Domain.Project;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class ActivitySteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;

    Activity activity;

    public ActivitySteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
    }

    //#author: Christian Colberg - s224343
    @When("a employee creates an activity with the name {string} in the project {string}")
    public void aEmployeeCreatesAnActivityWithTheNameInTheProject(String activityName, String projectName) throws ProjectNotFoundException, InvalidActivityNameException {
        try {
            String projectID = timeCatApp.getProjectByName(projectName).getID();
            timeCatApp.createActivity(activityName, projectID);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @Then("the activity with the name {string} is in the project {string}")
    public void theActivityWithTheNameIsInTheProject(String activityName, String projectName) throws ProjectNotFoundException, ActivityNotFoundException {
        Activity newActivity = timeCatApp.getProjectByName(projectName).getActivityByName(activityName);
        activity = newActivity;
        assertNotNull(newActivity);
    }

    //#author: Christian Colberg - s224343
    @And("the activity has no registered hours")
    public void theActivityHasNoRegisteredHours() {
        assertEquals(activity.getTimesheet().getEntryCount(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the activity has no assigned employees")
    public void theActivityHasNoAssignedEmployees() {
        assertTrue(activity.getAssignedEmployees().isEmpty());
    }


    //#author: Christian Colberg - s224343
    @And("the startWeek is not assigned")
    public void theStartWeekIsNotAssigned() {
        assertEquals(activity.getStartWeek(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the hourEstimate is not assigned")
    public void theHourEstimateIsNotAssigned() {
        assertEquals(activity.getHourEstimate(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the weekDuration is not assigned")
    public void theWeekDurationIsNotAssigned() {
        assertEquals(activity.getWeekDuration(), 0);
    }

    //#author: Christian Colberg - s224343
    @When("a employee removes an activity with the name {string} in the project {string}")
    public void aEmployeeRemovesAnActivityWithTheNameInTheProject(String activityName, String projectName) {
        try {

            Project project = timeCatApp.getProjectByName(projectName);
            Activity activity = project.getActivityByName(activityName);
            timeCatApp.removeActivity(activity.getActivityID(), project.getID());
        }
        catch(Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @Then("the activity with the name {string} is not in the project {string}")
    public void the_activity_with_the_name_is_not_in_the_project(String activityName, String projectName) {
        Activity newActivity = null;
        try {
            Project project = timeCatApp.getProjectByName(projectName);
            newActivity = project.getActivityByName(activityName);
        }catch(Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
        activity = newActivity;
        assertNull(newActivity);
    }

    //#author: Christian Colberg - s224343
    @And("an activity with the name {string} isn't in the project {string}")
    public void anActivityWithTheNameIsnTInTheProject(String activityName, String projectName) {
        boolean activityFound = false;
        try{
            Activity newActivity = timeCatApp.getProjectByName(projectName).getActivityByName(activityName);
            if(newActivity != null){
                activityFound = true;
            }
        }
        catch(Exception e){
            activityFound = false;
        }
        assertFalse(activityFound);
    }
    //#author: Christian Colberg - s224343
    @Given("a project with the name {string} is not in the project repository")
    public void aProjectWithTheNameIsNotInTheProjectRepository(String projectName) {
    }

    //#author: Christian Colberg - s224343
    @And("an activity with the name {string} is in the project {string}")
    public void anActivityWithTheNameIsInTheProject(String activityName, String projectName) throws ProjectNotFoundException, ActivityNotFoundException {
        String projectID = timeCatApp.getProjectByName(projectName).getID();
        try {
            timeCatApp.createActivity(activityName, projectID);
        }catch(Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @And("an activity with the name {string} is not in the project {string}")
    public void anActivityWithTheNameIsNotInTheProject(String activityName, String projectName) {
    }
}