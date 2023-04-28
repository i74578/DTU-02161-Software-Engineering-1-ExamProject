package cucumber;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Employee;

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

    private Employee registerTestEmployee() throws Exception{
        if(timeCatApp.IsEmployeeLoggedIn()){
            timeCatApp.logout();
        }
        timeCatApp.login("ADM");
        timeCatApp.registerEmployee(getEmployee().getInitials());
        timeCatApp.logout();
        return getEmployee();
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
