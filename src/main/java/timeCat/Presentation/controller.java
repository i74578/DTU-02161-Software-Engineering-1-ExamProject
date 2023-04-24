package timeCat.Presentation;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Project;

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
    public void initializeOptions(){
        options.add(new commandOption("Create project","This options creates a new project in the project repository", this::createProject));
        options.add(new commandOption("Remove project","This options removes a project from the project repository",() -> removeProject()));
        options.add(new commandOption("List projects","This options lists all the projects in the project repository",() -> listProjects()));
    }
}
