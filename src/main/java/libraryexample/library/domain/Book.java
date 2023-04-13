/*
import dtu.library.dto.BookInfo;
import dtu.library.dto.MediumInfo;

public class Book extends Medium {

	private static final int FINE = 100;
	private static final int MAX_NUMBER_OF_DAYS = 4 * 7;

	public Book(String title, String author, String signature) {
		super(title, author, signature);
	}
	
	@Override
	protected int getMaxBorrowDays() {
		return MAX_NUMBER_OF_DAYS;
	}

	@Override
	double getFine() {
		return FINE;
	}

	@Override
	public String getTypeName() {
		return "Book";
	}

	@Override
	public MediumInfo asMediumInfo() {
		return new BookInfo(this.getTitle(),this.getAuthor(),this.getSignature());
	}

}
*/

