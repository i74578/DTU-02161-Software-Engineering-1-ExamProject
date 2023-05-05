//author: Christian Colberg - s224343
//author: Lukas Halberg - s216229
package cucumber;

import io.cucumber.java.en.Given;
import org.junit.platform.suite.api.ExcludePackages;
import timeCat.domain.*;
import timeCat.application.TimeCatApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import static org.junit.Assert.*;

public class TimeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private ArrayList<TimesheetEntry> registreredHours;
    private ArrayList<ReportEntry> timeReport;
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
        registreredHours.add(new TimesheetEntry(Calendar.getInstance(),employeeHelper.getEmployee(),2.2));
        registreredHours.add(new TimesheetEntry(Calendar.getInstance(),employeeHelper.getEmployee(),4));
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
        TimesheetEntry timesheetEntry = new TimesheetEntry(Calendar.getInstance(),employeeHelper.getEmployee(),-2);
        registreredHours.add(timesheetEntry);
        try{
            timeCatApp.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),timesheetEntry.getDate(),timesheetEntry.getHours());
        } catch(InvalidNameException | NotFoundException |NotAllowedException | IllegalArgumentException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee registers {int} hours spent on activity in one day")
    public void theEmployeeRegistersHoursSpentOnActivityInOneDay(int hoursSpent) {
        TimesheetEntry timesheetEntry = new TimesheetEntry(Calendar.getInstance(),employeeHelper.getEmployee(),hoursSpent);
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

    @And("employee has registered hours on activity {int} days in the future")
    public void employeeHasRegisteredHoursOnActivityDaysInTheFuture(int dateOffset) throws Exception {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE,dateOffset);
        registreredHours.add(new TimesheetEntry(date,employeeHelper.getEmployee(),2));
        registreredHours.add(new TimesheetEntry(date,employeeHelper.getEmployee(),4));
        for(TimesheetEntry entry : registreredHours){
            timeHelper.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),entry.getDate(),entry.getHours());
        }
    }

    @When("employee views total hours spent")
    public void employeeViewsTotalHoursSpent() {
        try {
            timeReport = timeCatApp.getTodayTimeReport();
        } catch (NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the registered time is shown in todays time report")
    public void theRegisteredTimeIsShownInTodaysTimeReport() {
        assertEquals(timeReport, registreredHours);
    }

    @Then("no registered time is shown")
    public void noRegisteredTimeIsShown() {
        assertEquals(0,timeReport.size());
    }

    @And("employee has no registered hours")
    public void employeeHasNoRegisteredHours() throws Exception {
        assertEquals(timeCatApp.getTodayTimeReport().size(),0);
    }

    @Then("I get {double} hours spent")
    public void iGetHoursSpent(double hoursSpent) {
        double totalHoursSpent = 0;
        for(TimesheetEntry entry : timeReport){
            totalHoursSpent += entry.getHours();
        }
        assertEquals(totalHoursSpent,hoursSpent,0.01);
    }

    @And("all entries in the report are from the project and activity")
    public void allEntriesInTheReportAreFromTheProjectAndActivity() throws Exception {
        String projectName = projectHelper.getProject().getName();
        String activityName = activityHelper.getActivity().getName();
        for(ReportEntry entry : timeReport){
            assertEquals(entry.getProjectName(),projectName);
            assertEquals(entry.getActivityName(),activityName);
        }
    }
}
