package timeCat.app;

import timeCat.domain.CostumerProject;
import timeCat.domain.Employee;
import timeCat.domain.InternalProject;
import timeCat.domain.Project;
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

    //@author  Benjamin Fríðberg - s224347
    public Project getProject(String projectID) throws ProjectNotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getID().equals(projectID)).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new ProjectNotFoundException("The project \"" + projectID + "\" is not found");
    }

    public void removeProject(String projectID) throws ProjectNotFoundException {
        Project projectToRemove = getProject(projectID);
        projectsRepo.remove(projectToRemove);
    }

    //@author  Benjamin Fríðberg - s224347
    public void createCostumerProject(String projectName){
        Project costumerProject = new CostumerProject(projectName,getNextProjectID());
        projectsRepo.add(costumerProject);
    }

    //@author  Benjamin Fríðberg - s224347
    public void createInternalProject(String projectName){
        Project internalProject = new InternalProject(projectName,getNextProjectID());
        projectsRepo.add(internalProject);
    }

    //@author  Benjamin Fríðberg - s224347
    public static String getNextProjectID(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return currentYear+String.format("%06d",projectsRepo.size()+1);
    }



}
