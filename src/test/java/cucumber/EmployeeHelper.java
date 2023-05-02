package cucumber;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;

//@author  Benjamin Fríðberg - s224347
public class EmployeeHelper {
    private TimeCatApp timeCatApp;
    private Employee employee;

    public EmployeeHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }

    public Employee getEmployee(){
        if(employee == null){
            employee = getTestEmployee();
        }
        return employee;
    }

    private Employee getTestEmployee() {
        Employee testEmployee = new Employee("BOB");
        return testEmployee;
    }

    public String registerTestEmployee() throws Exception{
        return registerEmployee(getEmployee().getInitials());
    }

    public String registerEmployee(String initials) throws Exception{
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.registerEmployee(initials);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return initials;
    }



    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
