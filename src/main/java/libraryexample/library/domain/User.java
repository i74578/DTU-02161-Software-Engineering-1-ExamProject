/*
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import dtu.library.app.EmailServer;
import dtu.library.app.OverdueException;
import dtu.library.app.TooManyMediaException;

public class User {

	private static final int MAX_NUMBER_OF_BOOKS = 10;

	private String cpr;
	private String name;
	private String email;

	private Address address;

	private List<Medium> borrowedMedia = new ArrayList<>();
	private double fine = 0d;
	private boolean hasFine = false;

	public User(String cpr, String name, String email) {
		this.cpr = cpr;
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Medium> getBorrowedMedia() {
		return Collections.unmodifiableList(borrowedMedia);
	}

	public void returnMedium(Medium medium) throws Exception {
		if (!borrowedMedia.contains(medium)) {
			throw new Exception("book/CD is not borrowed by the user");
		}
		borrowedMedia.remove(medium);
	}

	public String getCpr() {
		return cpr;
	}
	
	public void borrowMedium(Medium medium, Calendar borrowDate) throws Exception {
		canBorrow(borrowDate);
		medium.setDueDateFromBorrowDate(borrowDate);
		borrowedMedia.add(medium);
	}

	public void canBorrow(Calendar borrowDate) throws TooManyMediaException, OverdueException, Exception {
		if (borrowedMedia.size() >= MAX_NUMBER_OF_BOOKS) {
			throw new TooManyMediaException(String.format("Can't borrow more than %d books/CDs",MAX_NUMBER_OF_BOOKS));
		}
		if (hasOverdueMedia(borrowDate)) {
			throw new OverdueException("Can't borrow book/CD if user has overdue books/CDs");
		}
		if (hasFine(borrowDate)) {
			throw new Exception("Can't borrow book/CD if user has outstanding fines");
		}
	}

	public double getFine(Calendar currentDate) {
		if (!hasFine) {
			double fineValue = borrowedMedia.stream()
					.filter(b -> b.isOverdue(currentDate))
					.mapToDouble(b -> b.getFine())
					.sum();
			if (fineValue == 0) {
				fine = 0; hasFine = false;
			} else {
				fine = fineValue; hasFine = true;
			}
		}
		return hasFine ? fine : 0;
	}
	
	boolean hasFine(Calendar currentDate) {
		return hasFine;
	}

	public boolean hasOverdueMedia(Calendar date) {
		return borrowedMedia.stream()
				.anyMatch(b -> b.isOverdue(date));
	}

	public void sendEmailReminder(EmailServer emailServer, Calendar currentDate) {
		long numberOfOverdueMedia = borrowedMedia.stream()
				.filter(b -> b.isOverdue(currentDate))
				.count();
		emailServer.sendEmail(email, 
				"Overdue book(s)/CD(s)", 
				String.format("You have %s overdue book(s)/CD(s)", numberOfOverdueMedia));
	}

	public void payFine(int money) {
		if (fine == money) {
			fine = 0; hasFine = false;
		} else {
			fine = fine - money; hasFine = true;
		}
	}
}
 */