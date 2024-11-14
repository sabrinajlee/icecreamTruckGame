package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultsTest {

	@Test
	void testResults() {
		Results instance = new Results();
		assertNotNull(instance);
	}

	@Test
	void testResultsDoubleDoubleDoubleDoubleDoubleDouble() {
		Results instance = new Results(1,1,1,1,1,1);
		assertNotNull(instance);
	}
	
	@Test
	void testAddCash() {
		Results instance = new Results(0,0,0,0,0,0);
		instance.addCash(1);
		assertEquals(1,instance.getDayCash()[1]);
	}

	@Test
	void testAddRep() {
		Results instance = new Results(0,0,0,0,0,0);
		instance.addRep(1);
		assertEquals(1,instance.getRepArray()[1]);	}

	@Test
	void testGetDayCash() {
		Results instance = new Results(0,1,0,0,0,0);
		double[] arr = {1,0,0,0,0,0,0,0,0,0,0};
		assertArrayEquals(arr,instance.getDayCash());
	}

	@Test
	void testGetRepArray() {
		Results instance = new Results(2,0,0,0,0,0);
		double[] arr = {2,0,0,0,0,0,0,0,0,0,0};
		assertArrayEquals(arr,instance.getRepArray());	
	}


}
