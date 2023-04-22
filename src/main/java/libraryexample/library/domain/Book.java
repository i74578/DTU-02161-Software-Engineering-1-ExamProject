package dtu.library.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Medium {

	private static final int FINE = 100;
	private static final int MAX_NUMBER_OF_DAYS = 4 * 7;

	public Book(String title, String author, String signature) {
		super(title, author, signature);
	}
	
	public Book() { super(); }  // Needed by Java Persistence Layer

	@Override
	protected int getMaxBorrowDays() {
		return MAX_NUMBER_OF_DAYS;
	}

	@Override
	double getFine() {
		return FINE;
	}

	@Override
	protected String getTypeName() {
		return "Book";
	}

}
