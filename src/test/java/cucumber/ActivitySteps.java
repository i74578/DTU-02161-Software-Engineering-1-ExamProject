package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.ActivityNotFoundException;
import timeCat.Application.InvalidProjectNameException;
import timeCat.Application.ProjectNotFoundException;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Activity;
import timeCat.Domain.CostumerProject;
import timeCat.Domain.InternalProject;
import timeCat.Domain.Project;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class ActivitySteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;

    Activity activity;
    
    @When("a employee creates an activity with the name {string} in the project {string}")
    public void aEmployeeCreatesAnActivityWithTheNameInTheProject(String activityName, String projectName) throws ProjectNotFoundException {
        Activity newActivity = new Activity();
        timeCatApp.getProjectByName(projectName).addActivity(newActivity);
    }

    @Then("the activity with the name {string} is in the project {string}")
    public void theActivityWithTheNameIsInTheProject(String activityName, String projectName) throws ProjectNotFoundException, ActivityNotFoundException {
        Activity newActivity = timeCatApp.getProjectByName(projectName).getActivityByName(activityName);
        activity = newActivity;
        assertNotNull(newActivity);
    }

    @And("the activity has no registered hours")
    public void theActivityHasNoRegisteredHours() {

        
    }

    @And("the activity has no assigned employees")
    public void theActivityHasNoAssignedEmployees() {
        assertTrue(activity.getAssignedEmployees().isEmpty());
    }


    @And("the startWeek is not assigned")
    public void theStartWeekIsNotAssigned() {
        assertEquals(activity.getStartWeek(),0);
    }

    @And("the hourEstimate is not assigned")
    public void theHourEstimateIsNotAssigned() {
        assertEquals(activity.getHourEstimate(),0);
    }

    @And("the weekDuration is not assigned")
    public void theWeekDurationIsNotAssigned() {
        assertEquals(activity.getWeekDuration(),0);
    }
}