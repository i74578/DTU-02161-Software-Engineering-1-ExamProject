package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {

	@When("Employee submits valid username and password")
	public void employeeSubmitsValidUsernameAndPassword() {

	}

	@Then("Employee should be logged in")
	public void employeeShouldBeLoggedIn() {

	}

	@Given("Employee is Logged In")
	public void employeeIsLoggedIn() {

	}

	@Given("Employee is not logged in")
	public void employeeIsNotLoggedIn() {

	}

	@When("the a employee creates a costumer project with the name {string}")
	public void theAEmployeeCreatesACostumerProjectWithTheName(String arg0) {
	}

	@Then("a costumer project with the name {string} is in the project repository")
	public void aCostumerProjectWithTheNameIsInTheProjectRepository(String arg0) {

	}

	@When("the a employee creates a internal project with the name {string}")
	public void theAEmployeeCreatesAInternalProjectWithTheName(String arg0) {

	}

	@Then("a internal project with the name {string} is in the project repository")
	public void aInternalProjectWithTheNameIsInTheProjectRepository(String arg0) {

	}

	@Then("a internal project with the name {string} is not in the project repository")
	public void aInternalProjectWithTheNameIsNotInTheProjectRepository(String arg0) {

	}

	@Then("a costumer project with the name {string} is not in the project repository")
	public void aCostumerProjectWithTheNameIsNotInTheProjectRepository(String arg0) {
	}
}
