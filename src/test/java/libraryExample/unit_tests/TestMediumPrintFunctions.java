package dtu.library.unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dtu.library.domain.Book;
import dtu.library.domain.Cd;

public class TestMediumPrintFunctions {

	@Test
	public void testToString() {
		Book b = new Book("test","some author","signature");
		assertEquals("test by some author",b.toString());
		Cd c = new Cd("test cd","another author", "cd signature");
		assertEquals("test cd by another author",c.toString());
	}
	
	@Test
	public void testGetTypeName() {
		Book b = new Book("test","some author","signature");
		assertEquals("Book",b.getTypeName());
		Cd c = new Cd("test cd","another author", "cd signature");
		assertEquals("CD",c.getTypeName());
		
	}

}
