/*
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dtu.library.domain.Medium;
import dtu.library.domain.User;
import dtu.library.dto.MediumInfo;
import dtu.library.dto.UserInfo;

public class LibraryApp extends Observable {

	private boolean loggedIn = false;
	private List<Medium> mediumRepository = new ArrayList<>();
	private List<User> userRepository = new ArrayList<>();
	private DateServer dateServer = new DateServer();
	private EmailServer emailServer;
	

	public boolean adminLoggedIn() {
		return loggedIn;
	}

	public boolean adminLogin(String password) {
		loggedIn = password.equals("adminadmin");
		setChanged();
		notifyObservers();
		return loggedIn;
	}


	public void addMedium(MediumInfo medium) throws OperationNotAllowedException {
		checkAdministratorLoggedIn();
		mediumRepository.add(medium.asMedium());
	}

	public Stream<Medium> getMediaStream() {
		return mediumRepository.stream();
	}

	public List<MediumInfo> search(String searchText) {
		return mediumRepository.stream()
				.filter(b -> b.match(searchText))
				.map(b -> b.asMediumInfo())
				.collect(Collectors.toList());
	}

	public void adminLogout() {
		loggedIn = false;
		setChanged();
		notifyObservers();
	}

	public void registerUser(UserInfo u) throws Exception {
		checkAdministratorLoggedIn();
		User user = findUser(u);
		if (user != null) {
			throw new Exception("User is already registered");
		}
		userRepository.add(u.asUser());
	}

	public Stream<UserInfo> getUsersStream() {
		return userRepository.stream().map(u -> new UserInfo(u));
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public void borrowMedium(MediumInfo m, UserInfo u) throws Exception {
		Medium medium = findMedium(m);
		User user = findUser(u);
		user.borrowMedium(medium, dateServer.getDate());
	}

	public double getFineForUser(UserInfo u) {
		User user = findUser(u);
		double fine = user.getFine(dateServer.getDate());
		return fine;
	}

	public boolean userHasOverdueMedia(UserInfo u) {
		User user = findUser(u);
		boolean hasOverdueMedia = user.hasOverdueMedia(dateServer.getDate());
		return hasOverdueMedia;
	}

	public void sendReminder() throws OperationNotAllowedException {
		checkAdministratorLoggedIn();
		Calendar currentDate = dateServer.getDate();
		userRepository.stream()
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

	public void payFine(UserInfo u, int money) {
		User user = findUser(u);
		user.payFine(money);
	}

	public boolean canBorrow(UserInfo u) {
		User user = findUser(u);
		try {
			user.canBorrow(dateServer.getDate());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void unregister(UserInfo u) throws Exception {
		User user = findUser(u);
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
		userRepository.remove(user);
	}

	public void returnUserMedia(UserInfo u, MediumInfo m) throws Exception {
		User user = findUser(u);
		Medium medium =findMedium(m);
		user.getFine(dateServer.getDate());
		user.returnMedium(medium);
	}
	
	public boolean userHasBorrowedMedium(UserInfo u, MediumInfo m) {
		User user = findUser(u);
		Medium medium = findMedium(m);
		return user.getBorrowedMedia().contains(medium);
	}

	private Medium findMedium(MediumInfo mi) {
		return mediumRepository.stream()
				.filter(m -> m.getSignature().equals(mi.getSignature()))
				.findAny()
				.orElse(null);
	}

	private User findUser(UserInfo ui) {
		return userRepository.stream()
				.filter(u -> u.getCpr().equals(ui.getCpr()))
				.findAny()
				.orElse(null);
	}

	public boolean containsMediumWithSignature(String signature) {
		return getMediaStream().anyMatch(m -> m.getSignature().contentEquals(signature));
	}
}
 */
