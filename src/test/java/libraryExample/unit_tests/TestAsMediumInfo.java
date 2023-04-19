package dtu.library.unit_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dtu.library.domain.Cd;
import dtu.library.dto.CdInfo;
import dtu.library.dto.MediumInfo;

public class TestAsMediumInfo {

	@Test
	public void testCdAsMediumInfo() {
		Cd cd = new Cd("title","author", "signature");
		MediumInfo cdi = cd.asMediumInfo();
		assertTrue(cdi instanceof CdInfo);
		assertEquals(cd.getAuthor(),cdi.getAuthor());
		assertEquals(cd.getTitle(),cdi.getTitle());
		assertEquals(cd.getSignature(),cdi.getSignature());		
	}

}
