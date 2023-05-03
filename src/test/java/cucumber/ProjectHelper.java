package cucumber;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;
import timeCat.domain.Project;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

//@author  Benjamin Fríðberg - s224347
public class ProjectHelper {
    private TimeCatApp timeCatApp;
    private Project project;

    public ProjectHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    public Project createCostumerProject(String projectName) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        Project project = timeCatApp.createCostumerProject(projectName);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return project;
    }

    public Project createInternalProject(String projectName) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        Project project = timeCatApp.createInternalProject(projectName);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return project;
    }

    public void removeProject(String projectID, String PMinitials) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login(PMinitials);
        }
        timeCatApp.removeProject(projectID);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }

    public void addTestProject() throws Exception{
        project = createCostumerProject(getProject().getName());
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
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.assignPM(projectID,initials);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }

    public void assignTestPM() throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.registerEmployee("MGR");
        timeCatApp.assignPM(project.getID(),"MGR");
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
}
