package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testPlayerCSVFileStringIntInt() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1);
		assertNotNull(instance);
	}

	@Test
	void testPlayerCSVFileStringIntIntDoubleDoubleDoubleIntDoubleDoubleDouble() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		assertNotNull(instance);	
		}

	@Test
	void testGetNewFile() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1);
		assertEquals(testcsv,instance.getNewFile());
	}

	@Test
	void testGetPlayerInitials() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1);
		assertEquals("ts",instance.getPlayerInitials());	}

	@Test
	void testGetDay() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1);
		assertEquals(1,instance.getDay());	}

	@Test
	void testGetWeather() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		assertEquals(1,instance.getWeather());		}

	@Test
	void testGetDifficultyAsString() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1);
		assertEquals("Medium",instance.getDifficultyAsString());		}

	@Test
	void testGetReputation() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		assertEquals(1,instance.getReputation());	}

	@Test
	void testGetBalance() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		assertEquals(1,instance.getBalance());	}

	@Test
	void testChangeBalance() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		instance.changeBalance(2, "refund");
		assertEquals(3,instance.getBalance());	}

	@Test
	void testSetBalance() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1);
		instance.setBalance(50);
		assertEquals(50,instance.getBalance());	}

	@Test
	void testSetDay() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		instance.setDay(3);
		assertEquals(3,instance.getDay());	}

	@Test
	void testSetWeather() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		instance.setWeather(4);
		assertEquals(4,instance.getWeather());	}

	@Test
	void testSetPlayerInitials() {
		CSVFile testcsv = new CSVFile("test.csv");
		Player instance = new Player(testcsv,"ts",1,1,1,1,1,1,1,1,1);
		instance.setPlayerInitials("sl");
		assertEquals("sl",instance.getPlayerInitials());	}

}
