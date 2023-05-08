package cucumber;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;

//@author  Benjamin Fríðberg - s224347
public class EmployeeHelper {
    private TimeCatApp timeCatApp;
    private Employee employee;

    //@author  Benjamin Fríðberg - s224347
    public EmployeeHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    //@author  Benjamin Fríðberg - s224347
    public Employee getEmployee(){
        if(employee == null){
            employee = getTestEmployee();
        }
        return employee;
    }

    //@author  Benjamin Fríðberg - s224347
    private Employee getTestEmployee() {
        Employee testEmployee = new Employee("BOB");
        return testEmployee;
    }

    //@author  Benjamin Fríðberg - s224347
    public String registerTestEmployee() throws Exception{
        employee = registerEmployee(getEmployee().getInitials());
        return employee.getInitials();
    }

    //@author  Benjamin Fríðberg - s224347
    public Employee registerEmployee(String initials) throws Exception{
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("USR");
        }
        Employee registeredEmployee = timeCatApp.registerEmployee(initials);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return registeredEmployee;
    }

    //author: Lukas Halberg - s216229
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
