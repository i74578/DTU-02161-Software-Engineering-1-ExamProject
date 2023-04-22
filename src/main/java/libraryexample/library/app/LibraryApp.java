package dtu.library.app;

import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dtu.library.domain.Address;
import dtu.library.domain.Book;
import dtu.library.domain.Cd;
import dtu.library.domain.Medium;
import dtu.library.domain.User;
import dtu.library.persistence.InMemoryRepository;
import dtu.library.persistence.SqliteRepository;

/**
 * This class represents the business logic associated with the library
 * application.
 * 
 * @author Hubert
 *
 */
public class LibraryApp extends Observable {

	private boolean loggedIn = false;
	private MediumRepository mediumRepository;
	private UserRepository userRepository;
	private DateServer dateServer = new DateServer();
	private EmailServer emailServer;
	
	public LibraryApp(MediumRepository mediumRepository, UserRepository userRepository) {
		setRepositories(mediumRepository, userRepository);
	}
		
	public LibraryApp() {
	}

	public void clearDatabase() {
		userRepository.clearUserDatabase();
		mediumRepository.clearMediumDatabase();
	}

	/**
	 * @return true if the administrator is logged in, false otherwise.
	 */
	public boolean adminLoggedIn() {
		return loggedIn;
	}

	/**
	 * Logs in the administrator provided that the correct password is supplied.
	 * 
	 * @param password
	 * @return true if the administrator could be logged in, false otherwise.
	 */
	public boolean adminLogin(String password) {
		loggedIn = password.equals("adminadmin");
		setChanged();
		notifyObservers();
		return loggedIn;
	}

	/**
	 * Adds a medium to the library, so that it can be searched and borrowed. Only the
	 * administrator can do this. Thus, the administrator has to be logged in.
	 * 
	 * @param medium,
	 *            the medium to be added
	 * @throws OperationNotAllowedException
	 *             if the administrator is not logged in
	 */
	public void addMedium(Medium medium) throws OperationNotAllowedException {
		checkAdministratorLoggedIn();
		mediumRepository.addMedium(medium);
	}

	public Stream<Medium> getMediaStream() {
		return mediumRepository.getAllMediaStream();
	}

	/**
	 * Search for a set of mediums by text. The text can be any substring of either
	 * the title, the author, and the signature.
	 * 
	 * @param searchText
	 * @return a list of mediums containing the searchText in either title, author,
	 *         and signature
	 */
	public List<Medium> search(String searchText) {
		return mediumRepository.getAllMediaStream()
				.filter(b -> b.match(searchText))
				.collect(Collectors.toList());
	}

	/**
	 * Logs out the administrator.
	 */
	public void adminLogout() {
		loggedIn = false;
		setChanged();
		notifyObservers();
	}

	public void registerUser(User user) throws Exception {
		checkAdministratorLoggedIn();
		if (userRepository.contains(user)) {
			throw new Exception("User is already registered");
		}
		userRepository.addUser(user);
	}

	public Stream<User> getUsersStream() {
		return userRepository.getAllUsersStream();
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public void borrowMedium(Medium medium, User user) throws Exception {
		user.borrowMedium(medium, dateServer.getDate());
		mediumRepository.updateMedium(medium);
		userRepository.updateUser(user);
	}

	public double getFineForUser(User user) {
		double fine = user.getFine(dateServer.getDate());
		userRepository.updateUser(user);
		return fine;
	}

	public boolean userHasOverdueMedia(User user) {
		boolean hasOverdueMedia = user.hasOverdueMedia(dateServer.getDate());
		return hasOverdueMedia;
	}

	public void sendReminder() throws OperationNotAllowedException {
		checkAdministratorLoggedIn();
		Calendar currentDate = dateServer.getDate();
		userRepository.getAllUsersStream()
			.filter(u -> u.hasOverdueMedia(currentDate))
			.forEach(u -> {u.sendEmailReminder(emailServer,currentDate);});
	}

	private void checkAdministratorLoggedIn() throws OperationNotAllowedException {
		if (!adminLoggedIn()) {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}

	public void payFine(User user, int money) {
		user.payFine(money);
		userRepository.updateUser(user);
	}

	public boolean canBorrow(User user) {
		try {
			user.canBorrow(dateServer.getDate());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void unregister(User user) throws Exception {
		checkAdministratorLoggedIn();
		if (!userRepository.contains(user)) {
			throw new Exception("User not registered");
		}
		if (!user.getBorrowedMedia().isEmpty()) {
			throw new Exception("Can't unregister user: user has still borrowed books/CDs");
		}
		if (user.getFine(dateServer.getDate()) > 0) {
			throw new Exception("Can't unregister user: user has still fines to pay");
		}
		userRepository.removeUser(user);
	}

	public void returnUserMedia(User user, Medium medium) throws Exception {
		user.getFine(dateServer.getDate());
		user.returnMedium(medium);
		userRepository.updateUser(user);
	}
	
	public void exampleData() throws Exception {
		adminLogin("adminadmin");
		User user = new User("050149-2833","Tom P. Davis","TomPDavis@rhyta.com");
		Address address = new Address("Oakmound Drive",60062,"Northbrook");
		user.setAddress(address);
		registerUser(user);
		Book book = new Book("Extreme Programming Explained", "Kent Beck","Beck99");
		addMedium(book);
		Cd cd = new Cd("A Fish Named Wanda","John Cleese","Cleese88");
		addMedium(cd);
		
		borrowMedium(book, user);
		borrowMedium(cd, user);
		
		adminLogout();
	}

	public void setRepositories(MediumRepository mediumRepo, UserRepository userRepo) {
		this.mediumRepository = mediumRepo;
		this.userRepository = userRepo;	
//		try {
//			exampleData();
//		} catch (Exception e) {
//			throw new Error(e);
//		}
	}
}
