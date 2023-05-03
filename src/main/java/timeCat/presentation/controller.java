package timeCat.presentation;
import timeCat.application.*;
import timeCat.domain.Activity;
import timeCat.domain.Employee;
import timeCat.domain.Project;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;

import java.util.ArrayList;
import java.util.Scanner;

//@author  Benjamin Fríðberg - s224347
public class controller {
    private final ArrayList<commandOption> options;
    private final Scanner scanner;
    private final view view;
    private final TimeCatApp timeCatApp;

    //@author  Benjamin Fríðberg - s224347
    public controller(){
        timeCatApp = new TimeCatApp();
        scanner = new Scanner(System.in);
        view = new view();
        options = new ArrayList<>();
        initializeOptions();
    }

    //@author  Benjamin Fríðberg - s224347
    public void run(){
        view.showWelcomeScreen();
        while(true){
            while(!timeCatApp.IsEmployeeLoggedIn()){
                login();
            }
            view.clearConsole();
            view.showMainMenu(options);
            int chosenOption = getIntFromUser();
            if (chosenOption > 0 && chosenOption <= options.size()) {
                options.get(chosenOption - 1).getOptionCallMethod().run();
            }
            else{
                view.printError("Invalid option");
            }
        }
    }

    //@author  Benjamin Fríðberg - s224347
    public void proceedAfterUserInput(){
        view.print("Press enter to continue");
        scanner.nextLine();
        scanner.nextLine();
        view.clearConsole();
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
    public void listProjects(){
        ArrayList<Project> projects = timeCatApp.getProjects();
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
    public void initializeOptions(){
        options.add(new commandOption("Create project","This options creates a new project in the project repository", this::createProject));
        options.add(new commandOption("Remove project","This options removes a project from the project repository", this::removeProject));
        options.add(new commandOption("List projects","This options lists all the projects in the project repository", this::listProjects));
        options.add(new commandOption("Create activity","This options creates a activity for a project", this::createActivity));
        options.add(new commandOption("Remove activity","This options removes a activity from a project", this::removeActivity));
        options.add(new commandOption("List activities","This options lists all activities in a project", this::listActivities));
        options.add(new commandOption("Register employee","This options registers a employee", this::registerEmployee));
        options.add(new commandOption("Unregister employee","This options unregisters a employee", this::unregisterEmployee));
        options.add(new commandOption("List employees","This options lists all employees in the employee repository", this::listEmployees));
        options.add(new commandOption("Assign Project Manager","This options assigns a project manager for a project", this::assignPM));
        options.add(new commandOption("Deassign Project Manager","This options deassigns a project manager for a project", this::deassignPM));
        options.add(new commandOption("Logout","Logout", this::logout));
    }
}
