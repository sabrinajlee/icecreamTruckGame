package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientListTest {
	private CSVFile testCSV = new CSVFile("Player3.csv"); 
	private Player testPlayer = new Player(testCSV, "user1", 1, 1);
	private IngredientList testClass = new IngredientList(0,0,0,0,testPlayer);
	
	final private int cones = 0, cream = 0, sugar = 0, vanilla = 0; 
	final private double balance = 100;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testPlayer.setBalance(balance);
	}

	@AfterEach
	void tearDown() throws Exception {
		testClass = new IngredientList(cones, cream, sugar, vanilla, testPlayer);	
	}

	@Test
	void testChangeConesQty() {
		try {
			setUp();
			testClass.changeConeQty(10, 10, "purchase");
			int result = testClass.getCones();
			assertEquals(10, result);
			tearDown();
		}
		catch (Exception e){
			
		}
	}
	
	@Test
	void testChangeCreamQty() {
		try {
			setUp();
			testClass.changeCreamQty(15, 10, "purchase");
			double result = testClass.getCream();
			assertEquals(15, result);
			tearDown();
		}
		catch (Exception e){
			
		}
	}
	
	@Test
	void testChangeSugarQty() {
		try {
			setUp();
			testClass.changeSugarQty(20, 10, "purchase");
			double result = testClass.getSugar();
			assertEquals(20, result);
			tearDown();
		}
		catch (Exception e){
			
		}
	}
	
	@Test
	void testChangeVanillaQty() {
		try {
			setUp();
			testClass.changeVanillaQty(25, 10, "purchase");
			double result = testClass.getVanilla();
			assertEquals(25, result);
			tearDown();
		}
		catch (Exception e){
			
		}
	}
	
	@Test
	void testUndoPurchase() {
		testClass.changeConeQty(15, 0, "purchase");
		String type = testClass.undoPurchase();
		double qty = testClass.getCones();
		
		if (type != "Cone" || qty != 0) fail(type + ", " + qty);
		
		testClass.changeCreamQty(15, 0, "purchase");
		type = testClass.undoPurchase();
		qty = testClass.getCream();
		
		if (type != "Cream" || qty != 0) fail(type + ", " + qty);
		
		testClass.changeSugarQty(15, 0, "purchase");
		type = testClass.undoPurchase();
		qty = testClass.getSugar();
		
		if (type != "Sugar" || qty != 0) fail(type + ", " + qty);
		
		testClass.changeVanillaQty(15, 0, "purchase");
		type = testClass.undoPurchase();
		qty = testClass.getSugar();
		
		if (type != "Vanilla" || qty != 0) fail(type + ", " + qty);
		
		assertTrue(true);
		
	}

}
