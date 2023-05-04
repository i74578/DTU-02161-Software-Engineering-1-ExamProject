//author: Christian Colberg - s224343
//author: Lukas Halberg - s216229
package cucumber;

import io.cucumber.java.en.Given;
import timeCat.domain.*;
import timeCat.exceptions.DuplicateException;
import timeCat.application.TimeCatApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Calendar;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TimeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private ArrayList<TimesheetEntry> registreredHours;
    private ArrayList<TimesheetEntry> hoursSpentDisplayed;
    private EmployeeHelper employeeHelper;
    private ProjectHelper projectHelper;
    private ActivityHelper activityHelper;
    private TimeHelper timeHelper;
    //author: Lukas Halberg - s216229
    public TimeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage,EmployeeHelper employeeHelper,ProjectHelper projectHelper,ActivityHelper activityHelper, TimeHelper timeHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.registreredHours = new ArrayList<>();
        this.employeeHelper = employeeHelper;
        this.projectHelper = projectHelper;
        this.activityHelper = activityHelper;
        this.timeHelper = timeHelper;
    }

    //author: Lukas Halberg - s216229
    @When("the employee registers hours spent on activity")
    public void theEmployeeRegistersHoursSpentOnActivity() {
        registreredHours.add(new TimesheetEntry(null,null,Calendar.getInstance(),employeeHelper.getEmployee(),2.2));
        registreredHours.add(new TimesheetEntry(null,null,Calendar.getInstance(),employeeHelper.getEmployee(),4));
        try{
            for(TimesheetEntry entry : registreredHours){
                timeCatApp.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),entry.getDate(),entry.getHours());
            }
        } catch(InvalidNameException | NotFoundException |NotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee registers negative hours spent on activity")
    public void theEmployeeRegistersNegativeHoursSpentOnActivity() {
        TimesheetEntry timesheetEntry = new TimesheetEntry(null,null,Calendar.getInstance(),employeeHelper.getEmployee(),-2);
        registreredHours.add(timesheetEntry);
        try{
            timeCatApp.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),timesheetEntry.getDate(),timesheetEntry.getHours());
        } catch(InvalidNameException | NotFoundException |NotAllowedException | IllegalArgumentException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee registers {int} hours spent on activity in one day")
    public void theEmployeeRegistersHoursSpentOnActivityInOneDay(int hoursSpent) {
        TimesheetEntry timesheetEntry = new TimesheetEntry(null,null,Calendar.getInstance(),employeeHelper.getEmployee(),hoursSpent);
        registreredHours.add(timesheetEntry);
        try{
            timeCatApp.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),timesheetEntry.getDate(),timesheetEntry.getHours());
        } catch(InvalidNameException | NotFoundException |NotAllowedException | IllegalArgumentException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //author: Lukas Halberg - s216229
    @Then("the hours are registered")
    public void theHoursAreRegistered() throws InvalidNameException, NotFoundException {
        Project project = timeCatApp.getProjectByID(projectHelper.getProject().getID());
        Activity activity = project.getActivityByID(activityHelper.getActivity().getActivityID());
        Timesheet timesheet = activity.getTimesheet();
        ArrayList<TimesheetEntry> timesheetEntries = timesheet.getEntries();
        assertEquals(timesheetEntries, registreredHours);

    }

    @Given("employee has registered hours on activity")
    public void employeeHasRegisteredHoursOnActivity() throws Exception {
        registreredHours.add(new TimesheetEntry(null,null,Calendar.getInstance(),employeeHelper.getEmployee(),2));
        registreredHours.add(new TimesheetEntry(null,null,Calendar.getInstance(),employeeHelper.getEmployee(),4));
        for(TimesheetEntry entry : registreredHours){
            timeHelper.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),entry.getDate(),entry.getHours());
        }
    }



    @When("employee views total hours spent")
    public void employeeViewsTotalHoursSpent() {
        try {
            hoursSpentDisplayed = timeCatApp.getTimeReport();
        } catch (NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("hours spent is displayed")
    public void hoursSpentIsDisplayed() {
        assertEquals(hoursSpentDisplayed, registreredHours);
    }

    @And("employee has no registered hours")
    public void employeeHasNoRegisteredHours() throws Exception {
        assertTrue(timeCatApp.getTimeReport().size() == 0);
    }

    @Then("I get {double} hours spent")
    public void iGetHoursSpent(double hoursSpent) {
        double totalHoursSpent = 0;
        for(TimesheetEntry entry : hoursSpentDisplayed){
            totalHoursSpent += entry.getHours();
        }
        assertEquals(totalHoursSpent,hoursSpent,0.01);
    }
}
