package dtu.library.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dtu.library.acceptance_tests.BookHelper;
import dtu.library.acceptance_tests.MockDateHolder;
import dtu.library.acceptance_tests.UserHelper;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.domain.Medium;
import dtu.library.domain.User;

public class TestUpdateAndSqliteRepository {

	LibraryApp libraryApp;
	MockDateHolder dateHolder;
	UserHelper userHelper;
	BookHelper bookHelper;

	User user;
	
	@BeforeEach
	public void setUp() throws OperationNotAllowedException, Exception {
		System.out.println("Executing setUp");
		SqliteRepository repository = new SqliteRepository(false);
		libraryApp = new LibraryApp(repository,repository);
		dateHolder = new MockDateHolder(libraryApp);
		userHelper = new UserHelper(libraryApp);
		bookHelper = new BookHelper(libraryApp);
		libraryApp.clearDatabase();
		setUpDatabase();
	}

	protected void setUpDatabase() throws Exception, OperationNotAllowedException {
		user = userHelper.registerExampleUser();
		bookHelper.addExampleBooks(1);
	}
	

	@Test
	public void testBorrowBooks() throws Exception {
		Medium book = getOneBookFromDatabase();
		assertNull(book.getDueDate());
		assertTrue(getOnlyUserFromDatabase().getBorrowedMedia().isEmpty());
		libraryApp.borrowMedium(book, user);
		assertNotNull(getOneBookFromDatabase().getDueDate());
	}

	@Test
	public void testComputeFine() throws Exception {
		Medium book = getOneBookFromDatabase();
		libraryApp.borrowMedium(book, user);
		assertThat(libraryApp.getFineForUser(getOnlyUserFromDatabase()),is(0d));
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.getFineForUser(getOnlyUserFromDatabase()),is(100d));
		libraryApp.payFine(getOnlyUserFromDatabase(), 50);
		assertThat(libraryApp.getFineForUser(getOnlyUserFromDatabase()),is(50.0));
	}
	
	@Test
	public void testRemoveUser() {
		assertNotNull(getOnlyUserFromDatabase());
		new SqliteRepository().removeUser(user);
		try {
			getOnlyUserFromDatabase();
			fail();
		} catch (NoSuchElementException e) {
			
		}
	}
	
	@Test
	public void testContains() {
		assertTrue(new SqliteRepository().contains(userHelper.getUser()));
		userHelper.setUser("123456", "name", "email", "street", 9887, "city");
		User newUser = userHelper.getUser();
		assertFalse(new SqliteRepository().contains(newUser));
	}
	
	protected User getOnlyUserFromDatabase() {
		return new SqliteRepository().getAllUsersStream().findFirst().get();
	}
	protected Medium getOneBookFromDatabase() {
		return new SqliteRepository().getAllMediaStream().findFirst().get();
	}


}
