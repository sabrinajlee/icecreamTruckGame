package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionTest {

	@Test
	void testTransaction() {
		Transaction instance = new Transaction("cream",10,1);
		assertNotNull(instance);
	}

	@Test
	void testGetIngredient() {
		Transaction instance = new Transaction("cream",10,1);
		assertEquals("cream",instance.getIngredient());
	}

	@Test
	void testGetQuantity() {
		Transaction instance = new Transaction("cream",10,1);
		assertEquals(10,instance.getQuantity());
	}

	@Test
	void testGetPrice() {
		Transaction instance = new Transaction("cream",10,1);
		assertEquals(1,instance.getPrice());
	}

}
