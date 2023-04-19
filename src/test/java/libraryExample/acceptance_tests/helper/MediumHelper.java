package dtu.library.acceptance_tests.helper;

import java.util.ArrayList;
import java.util.List;

import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.dto.BookInfo;
import dtu.library.dto.MediumInfo;

public class MediumHelper {
	LibraryApp libraryApp;

	public MediumHelper(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}

	public void addBooksToLibrary(List<MediumInfo> exampleBooks) throws OperationNotAllowedException {
		boolean adminLoggedIn = libraryApp.adminLoggedIn();
		if (!adminLoggedIn) {
			libraryApp.adminLogin("adminadmin");
		}
		for (MediumInfo b : exampleBooks) {
			libraryApp.addMedium(b);
		}
		if (!adminLoggedIn) {
			libraryApp.adminLogout();
		}
	}

	public List<MediumInfo> getExampleBooks(int n) {
		List<MediumInfo> books = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			books.add(new BookInfo("title" + i, "author" + i, "signature" + i));
		}
		return books;
	}
	
	public List<MediumInfo> addExampleBooks(int n) throws OperationNotAllowedException {
		List<MediumInfo> books = getExampleBooks(n);
		addBooksToLibrary(books);
		return books;
	}
}
