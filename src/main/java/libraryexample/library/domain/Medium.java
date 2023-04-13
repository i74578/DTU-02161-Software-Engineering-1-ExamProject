

/**
 * This class represents a medium with title, author, and signature, where
 * signature is a unique key used by the librarian to identify the medium. Very
 * often it is composed of the first letters of the authors plus the year the
 * medium was published.
 * 
 * @author Hubert
 *
 */
/*
public abstract class Medium {
	private String title;
	private String author;

	private String signature;

	private Calendar dueDate;

	public Medium(String title, String author, String signature) {
		this.title = title;
		this.author = author;
		this.signature = signature;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSignature() {
		return signature;
	}

	public boolean match(String searchText) {
		return signature.contains(searchText) || title.contains(searchText) || author.contains(searchText);
	}

	public void setDueDateFromBorrowDate(Calendar borrowDate) {
		this.dueDate = new GregorianCalendar();
		getDueDate().setTime(borrowDate.getTime());
		getDueDate().add(Calendar.DAY_OF_YEAR, getMaxBorrowDays());
	}

	abstract protected int getMaxBorrowDays();

	abstract double getFine();

	boolean isOverdue(Calendar currentDate) {
		// Precondition: isOverdue is only called for mediums that
		// are borrowed and thus have their dueDate set
		// The assert statement will not have full condition coverage, as the
		// precondition should never fail. If the precondition fails, a client has made
		// a mistake, i.e., the program has a bug.
		assert getDueDate() != null;
		return currentDate.after(getDueDate());
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public abstract String getTypeName() ;
	
	public String toString() {
		return getTitle() + " by "+getAuthor();
	}

	public abstract MediumInfo asMediumInfo();

}
*/
