package cucumber;

import org.junit.*;
import timeCat.application.TimeCatApp;
import timeCat.domain.Activity;
import timeCat.domain.Employee;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg

public class Whiteboxtest {

   @Test(expected = InvalidNameException.class )
    public void Test1() throws InvalidNameException, NotAllowedException, DuplicateException, NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        timeCatApp.login("ADM");
        String testvar = "EMSBS";
        timeCatApp.registerEmployee(testvar);
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
   @Test (expected = InvalidNameException.class )
    public void Test3() throws InvalidNameException, NotAllowedException, DuplicateException, NotFoundException {
       TimeCatApp timeCatApp = new TimeCatApp();
       timeCatApp.login("ADM");
        String testvar3 = "";
        timeCatApp.registerEmployee(testvar3);

    }


}