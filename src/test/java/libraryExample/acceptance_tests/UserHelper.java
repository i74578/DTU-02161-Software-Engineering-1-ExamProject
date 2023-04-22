package dtu.library.acceptance_tests;

import dtu.library.app.LibraryApp;
import dtu.library.domain.Address;
import dtu.library.domain.User;

public class UserHelper {
	private User user;
	private LibraryApp libraryApp;
	
	public UserHelper(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}
	
	public User getUser() {
		if (user == null) {
			user = exampleUser();
		}
		return user;
	}
	
	public void setUser(String cpr, String name, String email, String street, int postcode, String city) {
		Address a = new Address(street,postcode,city);
		user = new User(cpr,name,email);
		user.setAddress(a);
	}
	
	public User registerExampleUser() throws Exception {
		User usr = getUser();
		boolean loggedIn = libraryApp.adminLoggedIn();
		if (!loggedIn) {
			libraryApp.adminLogin("adminadmin");
		}
		libraryApp.registerUser(usr);
		if (!loggedIn) {
			libraryApp.adminLogout();
		}
		return usr;
	}
	
	private User exampleUser() {
		User user = new User("231171-3879","Freddie E. Messina","FreddieEMessina@armyspy.com");
		Address address = new Address("Øksendrupvej 68",1321,"København K");
		user.setAddress(address);
		return user;
	}
}
