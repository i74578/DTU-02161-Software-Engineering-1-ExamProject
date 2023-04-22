package dtu.library.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * This class represents a medium with title, author, and signature, where
 * signature is a unique key used by the librarian to identify the medium. Very
 * often it is composed of the first letters of the authors plus the year the
 * medium was published.
 * 
 * @author Hubert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
@DiscriminatorValue("M")
public abstract class Medium {
	private String title;
	private String author;

	@Id
	private String signature;

	/*
	 * There s a bug in EclipseLink 2.7.1 such that date fields written cannot be
	 * read anymore. Workaround is to store the date as milliseconds from the Unix
	 * epoch (1.1.1970) in a separate field dueDateMilliseconds and convert to a
	 * GreogorianCalender, when the dueDate is used.
	 * 
	 * This is a nice example of the use of getter and setters to access fields in
	 * Java as getDueDate and setDueDate does all the work of making sure that the
	 * dueDateMilliseconds field is correct.
	 */
	// @Temporal(TemporalType.DATE)
	@Transient
	private Calendar dueDate;

	private long dueDateMilliseconds;

	public Medium(String title, String author, String signature) {
		this.title = title;
		this.author = author;
		this.signature = signature;
	}

	public Medium() {
	} // Needed by Java Persistence Layer

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSignature() {
		return signature;
	}

	/**
	 * 
	 * @param searchText
	 * @return returns true if the searchText appears as substring in either title,
	 *         author, or signature field.
	 */
	public boolean match(String searchText) {
		return signature.contains(searchText) || title.contains(searchText) || author.contains(searchText);
	}

	public void setDueDateFromBorrowDate(Calendar borrowDate) {
		setDueDate(new GregorianCalendar());
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
		if (dueDate == null && dueDateMilliseconds != 0l) {
			dueDate = GregorianCalendar.getInstance();
			dueDate.setTimeInMillis(dueDateMilliseconds);
		}
		return dueDate;
	}

	private void setDueDate(Calendar dueDate) {
		assert dueDate != null;
		this.dueDate = dueDate;
		this.dueDateMilliseconds = dueDate.getTimeInMillis();
	}
	
	public String printDetail() {
		StringBuffer b = new StringBuffer();
		b.append("<html>"+this.getTypeName()+"<br>");
		b.append(String.format("<b>Title:</b>     %s<br>", this.getTitle()));
		b.append(String.format("<b>Author:</b>    %s<br>", this.getAuthor()));
		b.append(String.format("<b>Signature:</b> %s<br></html>", this.getSignature()));
		return b.toString();
	}

	abstract protected String getTypeName() ;
	
	public String toString() {
		return getTitle() + " by "+getAuthor();
	}

}
