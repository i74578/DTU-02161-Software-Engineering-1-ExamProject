package dtu.library.acceptance_tests.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dtu.library.acceptance_tests.helper.ErrorMessageHolder;
import dtu.library.acceptance_tests.helper.MediumHelper;
import dtu.library.acceptance_tests.helper.MockDateHolder;
import dtu.library.acceptance_tests.helper.UserHelper;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.TooManyMediaException;
import dtu.library.dto.BookInfo;
import dtu.library.dto.CdInfo;
import dtu.library.dto.MediumInfo;
import dtu.library.dto.UserInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MediumSteps {

	private LibraryApp libraryApp;

	private MediumInfo medium;
	private ErrorMessageHolder errorMessage;
	private List<MediumInfo> books;
	private Map<String, MediumInfo> bookBySignature = new HashMap<>();
	
	private UserHelper helper;
	private MockDateHolder dateHolder;
	private MediumHelper bookHelper;

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
	public MediumSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessage, UserHelper helper, MockDateHolder dateHolder, MediumHelper bookHelper) {
		this.libraryApp = libraryApp;
		this.errorMessage = errorMessage;
		this.helper = helper;
		this.dateHolder = dateHolder;
		this.bookHelper = bookHelper;
	}

	@Given("there is a book with title {string}, author {string}, and signature {string}")
	public void thereIsABookWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
		medium = new BookInfo(title,author,signature);
	}
	
	@Given("the book is not in the library")
	public void theBookIsNotInTheLibrary() {
	    assertFalse(libraryApp.containsMediumWithSignature(medium.getSignature()));
	}
	
	@Given("there is a Cd with title {string}, author {string}, and signature {string}")
	public void thereIsACdWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
	    medium = new CdInfo(title,author,signature);
	}

	@Given("a book with signature {string} is in the library")
	public void aBookWithSignatureIsInTheLibrary(String signature) throws Exception {
		if (signature.equals("Beck99")) {
			medium = new BookInfo("Extreme Programming", "Kent Beck", signature);
		} else {
			medium = new BookInfo("The Cucumber BookInfo for Java", "Seb Rose", signature);
		}
		bookBySignature.put(signature,medium);
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
	}

	@Given("these books are contained in the library")
	public void theseBooksAreContainedInTheLibrary(List<List<String>> books) throws Exception {
		for (List<String> bookInfo : books) {
			libraryApp.addMedium(new BookInfo(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2)));
		}
	}
	
	@When("the book is added to the library")
	public void theBookIsAddedToTheLibrary() throws Exception {
		try {
			libraryApp.addMedium(medium);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the book with title {string}, author {string}, and signature {string} is contained in the library")
	public void theBookWithTitleAuthorAndSignatureIsContainedInTheLibrary(String title, String author, String signature)
			throws Exception {
		assertTrue(libraryApp.containsMediumWithSignature(signature));
	}

	@When("the Cd is added to the library")
	public void theCdIsAddedToTheLibrary() throws Exception {
		try {
			libraryApp.addMedium(medium);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the Cd with title {string}, author {string}, and signature {string} is contained in the library")
	public void theCdWithTitleAuthorAndSignatureIsContainedInTheLibrary(String title, String author, String signature) throws Exception {
		assertThat(medium.getTitle(),is(equalTo(title)));
		assertThat(medium.getAuthor(),is(equalTo(author)));
		assertThat(medium.getSignature(),is(equalTo(signature)));
		assertTrue(libraryApp.getMediaStream().anyMatch(m -> m.getSignature().equals(medium.getSignature())));
	}
	
	@Then("the error message {string} is given")
	public void theErrorMessageIsGiven(String errorMessage) throws Exception {
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
	}

	@Given("the library has a book with title {string}, author {string}, and signature {string}")
	public void theLibraryHasABookWithTitleAuthorAndSignature(String title, String author, String signature)
			throws Exception {
		MediumInfo book = new BookInfo(title, author, signature);
		assertThat(book.getTitle(),is(equalTo(title)));
		assertThat(book.getAuthor(),is(equalTo(author)));
		assertThat(book.getSignature(),is(equalTo(signature)));
		libraryApp.addMedium(book);
	}

	@When("the user searches for the text {string}")
	public void theUserSearchesForTheText(String searchText) throws Exception {
		books = libraryApp.search(searchText);
	}

	@Then("the book with signature {string} is found")
	public void theBookWithSignatureIsFound(String signature) throws Exception {
		assertEquals(1, books.size());
		assertEquals(signature, books.get(0).getSignature());
	}

	@Then("no book is found")
	public void noBookIsFound() throws Exception {
		assertTrue(books.isEmpty());
	}

	@Then("the books with signatures {string} and {string} are found")
	public void theBooksWithSignaturesAndAreFound(String signature1, String signature2) throws Exception {
		assertEquals(2, books.size());
		MediumInfo book1 = books.get(0);
		MediumInfo book2 = books.get(1);
		assertTrue((book1.getSignature().equals(signature1) && book2.getSignature().equals(signature2))
				|| (book1.getSignature().equals(signature2) && book2.getSignature().equals(signature1)));
	}
	
	@Given("book {string} by {string} with signature {string} is in the library")
	public void bookByWithSignatureIsInTheLibrary(String title, String author, String signature) throws Exception {
		medium = new BookInfo(title,author,signature);
	}
	
	@Given("the user has borrowed {int} books")
	public void theUserHasBorrowedBooks(int arg1) throws Exception {
		List<MediumInfo> exampleBooks = bookHelper.getExampleBooks(10);
		bookHelper.addBooksToLibrary(exampleBooks);
		for (MediumInfo b : exampleBooks) {
			libraryApp.borrowMedium(b,helper.getUser());
		}
	}


	@When("the user borrows the book")
	public void theUserBorrowsTheBook() throws Exception {
		try {
			libraryApp.borrowMedium(medium,helper.getUser());
		} catch (TooManyMediaException e ) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the user borrows the book with signature {string}")
	public void theUserBorrowsTheBookWithSignature(String signature) throws Exception {
		try {
			libraryApp.borrowMedium(bookBySignature.get(signature),helper.getUser());
		} catch (Exception e ) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the book is borrowed by the user")
	public void theBookIsBorrowedByTheUser() throws Exception {
		assertThat(libraryApp.userHasBorrowedMedium(helper.getUser(),medium),is(true));
	}
	
	@Then("the book is not borrowed by the user")
	public void theBookIsNotBorrowedByTheUser() throws Exception {
		assertThat(libraryApp.userHasBorrowedMedium(helper.getUser(),medium),is(false));
	}
	
	@Then("the book with signature {string} is not borrowed by the user")
	public void theBookWithSignatureIsNotBorrowedByTheUser(String signature) throws Exception {
		MediumInfo book = bookBySignature.get(signature);
		assertThat(libraryApp.userHasBorrowedMedium(helper.getUser(),book),is(false));
	}
	
	@Given("the user has borrowed a book")
	public void theUserHasBorrowedABook() throws Exception {
		medium = new BookInfo("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
		libraryApp.borrowMedium(medium,helper.getUser());
	}

	@Given("that the user has borrowed a book")
	public void thatTheUserHasBorrowedABook() throws Exception {
		theUserHasBorrowedABook();
	}

	@Given("the user has to pay a fine")
	public void theUserHasToPayAFine() throws Exception {
		medium = new BookInfo("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
		libraryApp.borrowMedium(medium,helper.getUser());
		dateHolder.advanceDateByDays(29);
		libraryApp.returnUserMedia(helper.getUser(),medium);
		assertTrue(libraryApp.getFineForUser(helper.getUser()) > 0);
	}

	@Given("the user has borrowed a CD")
	public void theUserHasBorrowedACD() throws Exception {
		medium = new CdInfo("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
		libraryApp.borrowMedium(medium,helper.getUser());
	}

	@Given("the fine for one overdue CD is {int} DKK")
	public void theFineForOneOverdueCDIsDKK(int arg1) throws Exception {
	}

	
	@Given("that the user has not borrowed the book")
	public void thatTheUserHasNotBorrowedTheBook() throws Exception {
		medium = new BookInfo("title","author","signature");
		bookHelper.addBooksToLibrary(Arrays.asList(medium));
	}

	@When("the user returns the book")
	public void theUserReturnsTheBook() throws Exception {
		try {
			libraryApp.returnUserMedia(helper.getUser(),medium);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the user returns the book with signature {string}")
	public void theUserReturnsTheBookWithSignature(String signature) throws Exception {
		try {
			libraryApp.returnUserMedia(helper.getUser(),bookBySignature.get(signature));
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there is a user with one overdue book")
	public void thereIsAUserWithOneOverdueBook() throws Exception {
		libraryApp.registerUser(helper.getUser());
		thatTheUserHasBorrowedABook();
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueMedia(helper.getUser()),is(true));		
	}
	
	@Given("a user has an overdue book")
	public void aUserHasAnOverdueBook() throws Exception {
		UserInfo user = helper.getUser();
		libraryApp.adminLogin("adminadmin");
		libraryApp.registerUser(user);
		libraryApp.adminLogout();
		List<MediumInfo> books = bookHelper.getExampleBooks(1);
		bookHelper.addBooksToLibrary(books);
		medium = books.get(0);
		libraryApp.borrowMedium(medium, user);
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueMedia(user),is(true));
	}

	@Given("the user has another overdue book")
	public void theUserHasAnotherOverdueBook() throws Exception {
		List<MediumInfo> books = new ArrayList<>();
		books.add(new BookInfo("title","author","signature"));
		bookHelper.addBooksToLibrary(books);
		medium = books.get(0);
		libraryApp.borrowMedium(medium, helper.getUser());
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueMedia(helper.getUser()),is(true));
	}

	@When("the user pays {int} DKK")
	public void theUserPaysDKK(int money) throws Exception {
	    libraryApp.payFine(helper.getUser(),money);
	}

	@Then("the user can borrow books again")
	public void theUserCanBorrowBooksAgain() throws Exception {
		assertThat(libraryApp.canBorrow(helper.getUser()),is(true));
	}
	
	@Then("the user cannot borrow books")
	public void theUserCannotBorrowBooks() throws Exception {
		assertThat(libraryApp.canBorrow(helper.getUser()),is(false));
	}


}
