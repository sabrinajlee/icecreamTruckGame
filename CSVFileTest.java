package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVFileTest {
	private CSVFile file = new CSVFile("Player3.csv");
	// private Player testPlayer();
	List<String[]> records;
	

	@Test
	void testReturnLastDay() {
		String[] lastDayRecord = file.returnLastDay();
		assertEquals(1, Integer.parseInt(lastDayRecord[2]));
	}

}
