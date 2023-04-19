package dtu.library.acceptance_tests.helper;

import dtu.library.app.LibraryApp;
import dtu.library.domain.Address;
import dtu.library.dto.UserInfo;

public class UserHelper {
	private UserInfo user;
	private LibraryApp libraryApp;
	
	public UserHelper(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}
	
	public UserInfo getUser() {
		if (user == null) {
			user = exampleUser();
		}
		return user;
	}
	
	public void setUser(String cpr, String name, String email, String street, int postcode, String city) {
		Address a = new Address(street,postcode,city);
		user = new UserInfo(cpr,name,email);
		user.setAddress(a);
	}
	
	public UserInfo registerExampleUser() throws Exception {
		UserInfo usr = getUser();
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
	
	private UserInfo exampleUser() {
		UserInfo user = new UserInfo("231171-3879","Freddie E. Messina","FreddieEMessina@armyspy.com");
		Address address = new Address("Øksendrupvej 68",1321,"København K");
		user.setAddress(address);
		return user;
	}
}
