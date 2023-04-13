/*
import dtu.library.dto.CdInfo;
import dtu.library.dto.MediumInfo;

public class Cd extends Medium {

	private static final int FINE = 200;
	private static final int MAX_NUMBER_OF_DAYS = 7;

	public Cd(String title, String author, String signature) {
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
		return "CD";
	}
	
	@Override
	public MediumInfo asMediumInfo() {
		return new CdInfo(this.getTitle(),this.getAuthor(),this.getSignature());
	}
}
 */
