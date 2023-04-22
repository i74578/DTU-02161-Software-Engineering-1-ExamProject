package dtu.library.domain;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Cd extends Medium {

	private static final int FINE = 200;
	private static final int MAX_NUMBER_OF_DAYS = 7;

	public Cd(String title, String author, String signature) {
		super(title, author, signature);
	}
	
	public Cd() { super(); }  // Needed by Java Persistence Layer

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
		return "CD";
	}


}
