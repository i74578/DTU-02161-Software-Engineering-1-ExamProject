package timeCat.Application;

import timeCat.Domain.CostumerProject;
import timeCat.Domain.Employee;
import timeCat.Domain.InternalProject;
import timeCat.Domain.Project;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class TimeCatApp {
    private static ArrayList<Project> projectsRepo = new ArrayList<>();
    private static ArrayList<Employee> employeeRepo = new ArrayList<>();

    public ArrayList<Project> getProjects(){
        return projectsRepo;
    }

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByID(String projectID) throws ProjectNotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getID().equals(projectID)).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new ProjectNotFoundException("The project is not found");
    }

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByName(String projectName) throws ProjectNotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getName().equals(projectName)).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new ProjectNotFoundException("The project is not found");
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID) throws ProjectNotFoundException {
        Project projectToRemove = getProjectByID(projectID);
        projectsRepo.remove(projectToRemove);
    }

    //@author  Benjamin Fríðberg - s224347
    public void createCostumerProject(String projectName) throws InvalidProjectNameException {
        Project costumerProject = new CostumerProject(projectName,getNextProjectID());
        projectsRepo.add(costumerProject);
    }

    //@author  Benjamin Fríðberg - s224347
    public void createInternalProject(String projectName) throws InvalidProjectNameException {
        Project internalProject = new InternalProject(projectName,getNextProjectID());
        projectsRepo.add(internalProject);
    }

    //@author  Benjamin Fríðberg - s224347
    public static String getNextProjectID(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return currentYear+String.format("%04d",projectsRepo.size()+1);
    }
}
