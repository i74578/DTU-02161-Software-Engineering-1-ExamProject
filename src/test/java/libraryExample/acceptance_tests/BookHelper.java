package dtu.library.acceptance_tests;

import java.util.ArrayList;
import java.util.List;

import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.domain.Book;
import dtu.library.domain.Medium;

public class BookHelper {
	LibraryApp libraryApp;

	public BookHelper(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}

	public void addBooksToLibrary(List<Medium> exampleBooks) throws OperationNotAllowedException {
		boolean adminLoggedIn = libraryApp.adminLoggedIn();
		if (!adminLoggedIn) {
			libraryApp.adminLogin("adminadmin");
		}
		for (Medium b : exampleBooks) {
			libraryApp.addMedium(b);
		}
		if (!adminLoggedIn) {
			libraryApp.adminLogout();
		}
	}

	public List<Medium> getExampleBooks(int n) {
		List<Medium> books = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			books.add(new Book("title" + i, "author" + i, "signature" + i));
		}
		return books;
	}
	
	public List<Medium> addExampleBooks(int n) throws OperationNotAllowedException {
		List<Medium> books = getExampleBooks(n);
		addBooksToLibrary(books);
		return books;
	}
}
