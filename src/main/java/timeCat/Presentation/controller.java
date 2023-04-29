package timeCat.Presentation;
import timeCat.Application.*;
import timeCat.Domain.Activity;
import timeCat.Domain.Employee;
import timeCat.Domain.Project;
import timeCat.Exceptions.NotAllowedException;
import timeCat.Exceptions.NotFoundException;
import timeCat.Exceptions.DuplicateException;
import timeCat.Exceptions.InvalidNameException;

import java.util.ArrayList;
import java.util.Scanner;

//@author  Benjamin Fríðberg - s224347
public class controller {
    private ArrayList<commandOption> options;
    private Scanner scanner;
    private view view;
    private final TimeCatApp timeCatApp;

    //@author  Benjamin Fríðberg - s224347
    public controller(){
        timeCatApp = new TimeCatApp();
        scanner = new Scanner(System.in);
        view = new view();
        options = new ArrayList<commandOption>();
        initializeOptions();
    }

    //@author  Benjamin Fríðberg - s224347
    public void run(){
        boolean stop = false;
        view.showWelcomeScreen();
        while(!stop){
            while(!timeCatApp.IsEmployeeLoggedIn()){
                login();
            }
            view.showMainMenu(options);
            int chosenOption = getIntFromUser();
            if (chosenOption > 0 && chosenOption <= options.size()) {
                options.get(chosenOption - 1).optionCallMethod.run();
            }
            else{
                view.printError("Invalid option");
            }
        }
    }

    //@author  Benjamin Fríðberg - s224347
    public int getIntFromUser(){
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
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
        String isCustomerProject = scanner.next();
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
    }

    //@author  Benjamin Fríðberg - s224347
    public void listProjects(){
        ArrayList<Project> projects = timeCatApp.getProjects();
        view.showProjects(projects);
    }

    //@author  Benjamin Fríðberg - s224347
    private void listActivities(){
        view.print("ProjectID of project where activity is located:");
        String projectID = scanner.nextLine();
        try {
            ArrayList<Activity> activities = timeCatApp.getProjectActivities(projectID);
            view.showActivities(activities);
        } catch (NotFoundException e) {
            view.printError(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    private void removeActivity() {
        view.print("ProjectID of project where activity is located:");
        String projectID = scanner.nextLine();
        view.print("ActivityID of activity to remove:");
        String activityID = scanner.nextLine();
        try {
            timeCatApp.removeActivity(activityID,projectID);
        } catch (NotFoundException e) {
            view.printError(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    private void createActivity() {
        view.print("ProjectID of project where activity should be added:");
        String projectID = scanner.nextLine();
        view.print("Name of activity:");
        String activityName = scanner.nextLine();
        try {
            timeCatApp.createActivity(activityName,projectID);
        } catch (DuplicateException | NotFoundException | InvalidNameException e) {
            view.printError(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    private void listEmployees() {
        ArrayList<Employee> employees = timeCatApp.getEmployees();
        view.showEmployees(employees);
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
    }

    //@author  Benjamin Fríðberg - s224347
    public void initializeOptions(){
        options.add(new commandOption("Create project","This options creates a new project in the project repository",() -> createProject()));
        options.add(new commandOption("Remove project","This options removes a project from the project repository",() -> removeProject()));
        options.add(new commandOption("List projects","This options lists all the projects in the project repository",() -> listProjects()));
        options.add(new commandOption("Create activity","This options creates a activity for a project",() -> createActivity()));
        options.add(new commandOption("Remove activity","This options removes a activity from a project",() -> removeActivity()));
        options.add(new commandOption("List activities","This options lists all activities in a project",() -> listActivities()));
        options.add(new commandOption("Register employee","This options registers a employee",() -> registerEmployee()));
        options.add(new commandOption("Unregister employee","This options unregisters a employee",() -> unregisterEmployee()));
        options.add(new commandOption("List employees","This options lists all employees in the employee repository",() -> listEmployees()));
        options.add(new commandOption("Logout","Logout",() -> logout()));
    }

}
