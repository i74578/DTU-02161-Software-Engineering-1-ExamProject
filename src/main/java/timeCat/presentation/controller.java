package timeCat.presentation;
import timeCat.application.*;
import timeCat.domain.*;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

//@author  Benjamin Fríðberg - s224347
public class controller {
    private final ArrayList<Feature> features;
    private final Scanner scanner;
    private final view view;
    private final TimeCatApp timeCatApp;

    //@author  Benjamin Fríðberg - s224347
    public controller(){
        timeCatApp = new TimeCatApp();
        scanner = new Scanner(System.in);
        view = new view();
        features = new ArrayList<>();
        addFeaturesToArray();
    }

    //@author  Benjamin Fríðberg - s224347
    public void addFeaturesToArray(){
        features.add(new Feature("Register time","This feature is used for registering time for a activity", this::registerTime));
        features.add(new Feature("View today time report","This feature shows a time report for all your registered hours today", this::todayTimeReport));
        features.add(new Feature("Create project","This feature creates a new project in the project repository", this::createProject));
        features.add(new Feature("Remove project","This feature removes a project from the project repository", this::removeProject));
        features.add(new Feature("List projects","This feature lists all the projects in the project repository", this::listProjects));
        features.add(new Feature("Create activity","This feature creates a activity for a project", this::createActivity));
        features.add(new Feature("Remove activity","This feature removes a activity from a project", this::removeActivity));
        features.add(new Feature("List activities","This feature lists all activities in a project", this::listActivities));
        features.add(new Feature("Register employee","This feature registers a employee", this::registerEmployee));
        features.add(new Feature("Unregister employee","This feature unregisters a employee", this::unregisterEmployee));
        features.add(new Feature("List employees","This feature lists all employees in the employee repository", this::listEmployees));
        features.add(new Feature("Assign Project Manager","This feature assigns a project manager for a project", this::assignPM));
        features.add(new Feature("Deassign Project Manager","This feature deassigns a project manager for a project", this::deassignPM));
        features.add(new Feature("Logout","Logout", this::logout));
    }

    //@author  Benjamin Fríðberg - s224347
    public void run(){
        view.showWelcomeScreen();
        while(true){
            while(!timeCatApp.IsEmployeeLoggedIn()){
                login();
            }

            view.showMainMenu(features);
            Integer chosenFeature = 0;
            try{
                chosenFeature = getIntFromUser();

            }
            catch(InputMismatchException e){
                scanner.nextLine();
                view.printError("Invalid input");
                proceedAfterUserInput();
                continue;
            }

            if (chosenFeature > 0 && chosenFeature <= features.size()) {
                view.clearConsole();
                features.get(chosenFeature - 1).getFeatureCallMethod().run();
            }
            else{
                view.printError("Invalid feature selected");
                proceedAfterUserInput();
            }
            view.clearConsole();
        }
    }

    //@author  Benjamin Fríðberg - s224347
    public void proceedAfterUserInput(){
        view.print("Press enter to continue");
        scanner.nextLine();
        view.clearConsole();
    }

    //@author  Benjamin Fríðberg - s224347
    public Integer getIntFromUser() throws InputMismatchException{
        Integer userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    //@author  Benjamin Fríðberg - s224347
    private double getDoubleFromUser() throws InputMismatchException {
        double userDouble = scanner.nextDouble();
        scanner.nextLine();
        return userDouble;
    }

    //@author  Benjamin Fríðberg - s224347
    public String getTokenFromUser(){
        String token = scanner.next();
        scanner.nextLine();
        return token;
    }

    //@author  Benjamin Fríðberg - s224347
    private Calendar getDateFromUser() throws java.text.ParseException{
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        cal.setTime(sdf.parse(scanner.nextLine()));
        return cal;
    }

    //@author  Benjamin Fríðberg - s224347
    private void login() {
        view.print("You need to login to continue");
        view.print("Please enter your employee initials: ");
        String initials = scanner.nextLine();
        try {
            timeCatApp.login(initials);
        } catch (NotAllowedException | NotFoundException  e) {
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
        view.clearConsole();
    }

    //@author  Benjamin Fríðberg - s224347
    private void logout() {
        try {
            timeCatApp.logout();
        } catch (NotAllowedException e) {
            view.printError(e.getMessage());
        }
    }


    //@author  Benjamin Fríðberg - s224347
    public void createProject(){
        view.print("Name of new project: ");
        String projectName = scanner.nextLine();
        view.print("Is the project a customer project? [y/n]");
        String isCustomerProject = getTokenFromUser();
        try {
            if (isCustomerProject.equals("y")) {
                timeCatApp.createCostumerProject(projectName);
            }
            else if(isCustomerProject.equals("n")) {
                timeCatApp.createInternalProject(projectName);
            }
            else{
                throw new Exception("Invalid [y/n] response");
            }
            view.print("Project created successfully");
        }
        catch(Exception e){
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(){
        view.print("ProjectID of project to be removed:");
        String projectID = scanner.nextLine();
        try {
            timeCatApp.removeProject(projectID);
            view.print("Project was removed successfully");
        }
        catch(Exception e){
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    public void listProjects() {
        ArrayList<Project> projects;
        projects = timeCatApp.getProjects();
        view.printTableWithHeader(projects,false,"Projects");
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void listActivities(){
        view.print("ProjectID of project where activity is located:");
        String projectID = scanner.nextLine();
        try {
            ArrayList<Activity> activities = timeCatApp.getProjectActivities(projectID);
            view.printTableWithHeader(activities,false,"Activities");
        } catch (NotFoundException e) {
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void removeActivity() {
        view.print("ProjectID of project where activity is located:");
        String projectID = scanner.nextLine();
        view.print("ActivityID of activity to remove:");
        String activityID = scanner.nextLine();
        try {
            timeCatApp.removeActivity(activityID,projectID);
        } catch (NotFoundException | NotAllowedException e) {
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void createActivity() {
        view.print("ProjectID of project where activity should be added:");
        String projectID = scanner.nextLine();
        view.print("Name of activity:");
        String activityName = scanner.nextLine();
        try {
            timeCatApp.createActivity(activityName,projectID);
            view.print("Activity created successfully");
        } catch (DuplicateException | NotFoundException | InvalidNameException | NotAllowedException e) {
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void listEmployees() {
        ArrayList<Employee> employees = timeCatApp.getEmployees();
        view.printTableWithHeader(employees,false,"Employees");
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void unregisterEmployee() {
        view.print("Initials of employee to be unregistered: ");
        String initials = scanner.nextLine();
        try {
            timeCatApp.unregisterEmployee(initials);
            view.print("Employee was unregistered successfully");
        }
        catch(Exception e){
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void registerEmployee() {
        view.print("Initials of new employee: ");
        String initials = scanner.nextLine();
        try {
            timeCatApp.registerEmployee(initials);
            view.print("Employee registered successfully");
        }
        catch(Exception e){
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void assignPM() {
        view.print("ProjectID to assign PM: ");
        String projectID = scanner.nextLine();
        view.print("Initials of new PM: ");
        String initials = scanner.nextLine();
        try {
            timeCatApp.assignPM(projectID,initials);
            view.print("Project manager assigned successfully");
        }
        catch(Exception e){
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void deassignPM() {
        view.print("ProjectID to deassign PM: ");
        String projectID = scanner.nextLine();
        try {
            timeCatApp.deassignPM(projectID);
            view.print("Project manager deassigned successfully");
        }
        catch(Exception e){
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void registerTime() {
        view.print("ProjectID of project where activity is located:");
        String projectID = scanner.nextLine();
        view.print("ActivityID of activity to register time to:");
        String activityID = scanner.nextLine();
        view.print("Date in format dd-mm-yyyy:");
        Calendar date;
        try {
            date = getDateFromUser();
        } catch (ParseException e) {
            scanner.nextLine();
            view.printError("Invalid date");
            proceedAfterUserInput();
            return;
        }
        view.print("Hours spent:");
        double hoursSpent;
        try{
            hoursSpent = getDoubleFromUser();
        } catch(InputMismatchException e){
            scanner.nextLine();
            view.printError("Invalid hour count");
            proceedAfterUserInput();
            return;
        }
        try {
            timeCatApp.registerTime(projectID, activityID, date, hoursSpent);
            view.print("Time registered successfully to activity");
        } catch (NotFoundException | NotAllowedException | IllegalArgumentException e) {
            view.printError(e.getMessage());
        }
        proceedAfterUserInput();
    }

    //@author  Benjamin Fríðberg - s224347
    private void todayTimeReport() {
        ArrayList<ReportEntry> timeReport = null;
        try {
            timeReport = timeCatApp.getTodayTimeReport();
        } catch (NotAllowedException e) {
            view.printError("Not allowed");
        }
        view.printTableWithHeader(timeReport,false,"Time Report");
        proceedAfterUserInput();
    }

}
