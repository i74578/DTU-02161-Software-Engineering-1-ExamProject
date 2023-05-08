package cucumber;
import org.junit.*;
import timeCat.application.TimeCatApp;
import timeCat.domain.Activity;
import timeCat.domain.Employee;
import timeCat.exceptions.*;
import java.util.function.BooleanSupplier;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg
public class Whiteboxtest2 {

    @Test (expected = NotFoundException.class)
    public void Test1() throws NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String testvar = "EMSBS";
        timeCatApp.getEmployee(testvar);
    }
    @Test
    public  void Test2() throws NotAllowedException, NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String testvar2 = "ESBS";
        timeCatApp.login("ADM");
        try {
            timeCatApp.registerEmployee(testvar2);
        } catch (Exception e) {}
        assertTrue(timeCatApp.hasEmployee(testvar2));
    }
    @Test (expected = NotFoundException.class)
    public void Test3() throws NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String testvar3 = "";
        timeCatApp.getEmployee(testvar3);
    }


}