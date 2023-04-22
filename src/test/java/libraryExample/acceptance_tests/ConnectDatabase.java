package dtu.library.acceptance_tests;

import dtu.library.app.LibraryApp;
import dtu.library.persistence.InMemoryRepository;
import io.cucumber.java.Before;

public class ConnectDatabase {
	LibraryApp libraryApp;
	
	public ConnectDatabase(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}
	
	@Before
	public void connectDatabase() {
//		SqliteRepository repo = new SqliteRepository();
		InMemoryRepository repo = new InMemoryRepository();
		libraryApp.setRepositories(repo,repo);
		
	}
}
