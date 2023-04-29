package cucumber;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Employee;
import timeCat.Domain.Project;
import timeCat.Exceptions.DuplicateException;
import timeCat.Exceptions.InvalidNameException;
import timeCat.Exceptions.NotAllowedException;
import timeCat.Exceptions.NotFoundException;

//@author  Benjamin Fríðberg - s224347
public class ProjectHelper {
    private TimeCatApp timeCatApp;
    private Project project;

    public ProjectHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    public String createCostumerProject(String projectName) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.GetLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        String projectID = timeCatApp.createCostumerProject(projectName);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return projectID;
    }

    public String createInternalProject(String projectName) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.GetLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        String projectID = timeCatApp.createInternalProject(projectName);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return projectID;
    }

    public void removeProject(String projectID, String PMinitials) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.GetLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login(PMinitials);
        }
        timeCatApp.removeProject(projectID);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }

    public void addTestProject() throws Exception{
        String projectID = createCostumerProject(getProject().getName());
        project = new Project(project.getName(),projectID);
    }

    public Project getProject() throws InvalidNameException {
        if(project == null){
            project = getTestProject();
        }
        return project;
    }

    private Project getTestProject() throws InvalidNameException {
        Project testProject = new Project("Test Project","239999");
        return testProject;
    }

    public void setProject(Project project){
        this.project = project;
    }


    public void assignPM(String projectID, String initials) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.GetLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.assignPM(projectID,initials);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
}
