package dtu.library.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.TooManyMediaException;
import dtu.library.domain.Book;
import dtu.library.domain.Cd;
import dtu.library.domain.Medium;
import dtu.library.domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSteps {

	private LibraryApp libraryApp;

	private Medium medium;
	private ErrorMessageHolder errorMessage;
	private List<Medium> books;
	private Map<String, Medium> bookBySignature = new HashMap<>();
	
	private UserHelper helper;
	private MockDateHolder dateHolder;
	private BookHelper bookHelper;

	/*
	 * Note that the constructor is apparently never called, but there are no null
	 * pointer exceptions regarding that libraryApp is not set. When creating the
	 * BookSteps object, the Cucumber libraries are using that constructor with an
	 * object of class LibraryApp as the default.
	 * 
	 * This also holds for all other step classes that have a similar constructor.
	 * In this case, the <b>same</b> object of class LibraryApp is used as an
	 * argument. This provides an easy way of sharing the same object, in this case
	 * the object of class LibraryApp, among all step classes.
	 * 
	 * This principle is called <em>dependency injection</em>. More information can
	 * be found in the "Cucumber for Java" book available online from the DTU Library.
	 */
	public BookSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessage, UserHelper helper, MockDateHolder dateHolder, BookHelper bookHelper) {
		this.libraryApp = libraryApp;
		this.errorMessage = errorMessage;
		this.helper = helper;
		this.dateHolder = dateHolder;
		this.bookHelper = bookHelper;
	}

	@Given("^I have a book with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\"$")
	public void iHaveABookWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
		medium = new Book(title,author,signature);
	}
	
	@Given("^I have a CD with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\"$")
	public void iHaveACDWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
	    medium = new Cd(title,author,signature);
	}

	@Given("^a book with signature \"([^\"]*)\" is in the library$")
	public void aBookWithSignatureIsInTheLibrary(String signature) throws Exception {
		if (signature.equals("Beck99")) {
			medium = new Book("Extreme Programming", "Kent Beck", signature);
		} else {
			medium = new Book("The Cucumber Book for Java", "Seb Rose", signature);
		}
		bookBySignature.put(signature,medium);
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
	}

	@Given("^these books are contained in the library$")
	public void theseBooksAreContainedInTheLibrary(List<List<String>> books) throws Exception {
		for (List<String> bookInfo : books) {
			libraryApp.addMedium(new Book(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2)));
		}
	}
	
	@When("^I add the book$")
	public void iAddTheBook() throws Exception {
		try {
			libraryApp.addMedium(medium);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("^the book with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\" is added to the library$")
	public void theBookWithTitleAuthorAndSignatureIsAddedToTheLibrary(String title, String author, String signature)
			throws Exception {
		assertThat(medium.getTitle(),is(equalTo(title)));
		assertThat(medium.getAuthor(),is(equalTo(author)));
		assertThat(medium.getSignature(),is(equalTo(signature)));
		assertTrue(libraryApp.getMediaStream().anyMatch(m -> m.getSignature().equals(medium.getSignature())));
	}

	@When("^I add the Cd$")
	public void iAddTheCd() throws Exception {
		try {
			libraryApp.addMedium(medium);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("^the Cd with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\" is added to the library$")
	public void theCdWithTitleAuthorAndSignatureIsAddedToTheLibrary(String title, String author, String signature) throws Exception {
		assertThat(medium.getTitle(),is(equalTo(title)));
		assertThat(medium.getAuthor(),is(equalTo(author)));
		assertThat(medium.getSignature(),is(equalTo(signature)));
		assertTrue(libraryApp.getMediaStream().anyMatch(m -> m.getSignature().equals(medium.getSignature())));
	}
	@Then("^I get the error message \"([^\"]*)\"$")
	public void iGetTheErrorMessage(String errorMessage) throws Exception {
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
	}

	@Given("^the library has a book with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\"$")
	public void theLibraryHasABookWithTitleAuthorAndSignature(String title, String author, String signature)
			throws Exception {
		Medium book = new Book(title, author, signature);
		assertThat(book.getTitle(),is(equalTo(title)));
		assertThat(book.getAuthor(),is(equalTo(author)));
		assertThat(book.getSignature(),is(equalTo(signature)));
		libraryApp.addMedium(book);
	}

	@When("^I search for the text \"([^\"]*)\"$")
	public void iSearchForTheText(String searchText) throws Exception {
		books = libraryApp.search(searchText);
	}

	@Then("^I find the book with signature \"([^\"]*)\"$")
	public void iFindTheBookWithSignature(String signature) throws Exception {
		assertEquals(1, books.size());
		assertEquals(signature, books.get(0).getSignature());
	}

	@Then("^I don't find any book$")
	public void iDonTFindAnyBook() throws Exception {
		assertTrue(books.isEmpty());
	}

	@Then("^I find a book with signatures \"([^\"]*)\" and \"([^\"]*)\"$")
	public void iFindABookWithSignaturesAnd(String signature1, String signature2) throws Exception {
		assertEquals(2, books.size());
		Medium book1 = books.get(0);
		Medium book2 = books.get(1);
		assertTrue((book1.getSignature().equals(signature1) && book2.getSignature().equals(signature2))
				|| (book1.getSignature().equals(signature2) && book2.getSignature().equals(signature1)));
	}
	
	@Given("^book \"([^\"]*)\" by \"([^\"]*)\" with signature \"([^\"]*)\" is in the library$")
	public void bookByWithSignatureIsInTheLibrary(String title, String author, String signature) throws Exception {
		medium = new Book(title,author,signature);
	}
	
	@Given("^the user has borrowed (\\d+) books$")
	public void theUserHasBorrowedBooks(int arg1) throws Exception {
		List<Medium> exampleBooks = bookHelper.getExampleBooks(10);
		bookHelper.addBooksToLibrary(exampleBooks);
		for (Medium b : exampleBooks) {
			libraryApp.borrowMedium(b,helper.getUser());
		}
	}


	@When("^the user borrows the book$")
	public void theUserBorrowsTheBook() throws Exception {
		try {
			libraryApp.borrowMedium(medium,helper.getUser());
		} catch (TooManyMediaException e ) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("^the user borrows the book with signature \"([^\"]*)\"$")
	public void theUserBorrowsTheBookWithSignature(String signature) throws Exception {
		try {
			libraryApp.borrowMedium(bookBySignature.get(signature),helper.getUser());
		} catch (Exception e ) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("^the book is borrowed by the user$")
	public void theBookIsBorrowedByTheUser() throws Exception {
		assertThat(helper.getUser().getBorrowedMedia(),hasItem(medium));
	}
	
	@Then("^the book is not borrowed by the user$")
	public void theBookIsNotBorrowedByTheUser() throws Exception {
		assertThat(helper.getUser().getBorrowedMedia(),not(hasItem(medium)));
	}
	
	@Then("^the book with signature \"([^\"]*)\" is not borrowed by the user$")
	public void theBookWithSignatureIsNotBorrowedByTheUser(String signature) throws Exception {
		Medium book = bookBySignature.get(signature);
		assertThat(helper.getUser().getBorrowedMedia(),not(hasItem(book)));
	}
	
	@Given("^(?:that |)the user has borrowed a book$")
	public void thatTheUserHasBorrowedABook() throws Exception {
		medium = new Book("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
		libraryApp.borrowMedium(medium,helper.getUser());
	}
	
	@Given("^the user has to pay a fine$")
	public void theUserHasToPayAFine() throws Exception {
		medium = new Book("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
		libraryApp.borrowMedium(medium,helper.getUser());
		dateHolder.advanceDateByDays(29);
		libraryApp.returnUserMedia(helper.getUser(),medium);
		assertTrue(libraryApp.getFineForUser(helper.getUser()) > 0);
	}

	@Given("^the user has borrowed a CD$")
	public void theUserHasBorrowedACD() throws Exception {
		medium = new Cd("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
		libraryApp.borrowMedium(medium,helper.getUser());
	}

	@Given("^the fine for one overdue CD is (\\d+) DKK$")
	public void theFineForOneOverdueCDIsDKK(int arg1) throws Exception {
	}

	
	@Given("^that the user has not borrowed the book$")
	public void thatTheUserHasNotBorrowedTheBook() throws Exception {
		medium = new Book("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
	}

	@When("^the user returns the book$")
	public void theUserReturnsTheBook() throws Exception {
		try {
			libraryApp.returnUserMedia(helper.getUser(),medium);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("^the user returns the book with signature \"([^\"]*)\"$")
	public void theUserReturnsTheBookWithSignature(String signature) throws Exception {
		try {
			libraryApp.returnUserMedia(helper.getUser(),bookBySignature.get(signature));
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("^there is a user with one overdue book$")
	public void thereIsAUserWithOneOverdueBook() throws Exception {
		libraryApp.registerUser(helper.getUser());
		thatTheUserHasBorrowedABook();
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueMedia(helper.getUser()),is(true));		
	}
	
	@Given("^a user has an overdue book$")
	public void aUserHasAnOverdueBook() throws Exception {
		User user = helper.getUser();
		libraryApp.adminLogin("adminadmin");
		libraryApp.registerUser(user);
		libraryApp.adminLogout();
		List<Medium> books = bookHelper.getExampleBooks(1);
		bookHelper.addBooksToLibrary(books);
		medium = books.get(0);
		libraryApp.borrowMedium(medium, user);
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueMedia(user),is(true));
	}

	@Given("^the user has another overdue book$")
	public void theUserHasAnotherOverdueBook() throws Exception {
		List<Medium> books = new ArrayList<>();
		books.add(new Book("title","author","signature"));
		bookHelper.addBooksToLibrary(books);
		medium = books.get(0);
		libraryApp.borrowMedium(medium, helper.getUser());
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueMedia(helper.getUser()),is(true));
	}

	@When("^the user pays (\\d+) DKK$")
	public void theUserPaysDKK(int money) throws Exception {
	    libraryApp.payFine(helper.getUser(),money);
	}

	@Then("^the user can borrow books again$")
	public void theUserCanBorrowBooksAgain() throws Exception {
		assertThat(libraryApp.canBorrow(helper.getUser()),is(true));
	}
	
	@Then("^the user cannot borrow books$")
	public void theUserCannotBorrowBooks() throws Exception {
		assertThat(libraryApp.canBorrow(helper.getUser()),is(false));
	}


}
