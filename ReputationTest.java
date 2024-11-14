package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReputationTest {

	@Test
	void testReputation() {
		Reputation instance = new Reputation();
		assertNotNull(instance);
	}

	@Test
	void testSetReputation() {
		Reputation instance = new Reputation();
		instance.setReputation(5);
		assertEquals(5,instance.getReputation());
	}

	@Test
	void testReputationDouble() {
		Reputation instance = new Reputation(5);
		assertEquals(5,instance.getReputation());
	}

	@Test
	void testGetReputation() {
		Reputation instance = new Reputation(5);
		assertEquals(5,instance.getReputation());
	}

}
