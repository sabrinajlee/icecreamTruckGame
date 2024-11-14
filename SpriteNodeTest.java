package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpriteNodeTest {

	@Test
	void testSpriteNode() {
		SpriteNode instance = new SpriteNode();
		assertNotNull(instance);
	}
	

	@Test
	void testSpriteNodeColorRange() {
		SpriteNode instance = new SpriteNode(" blue ",3);
		assertNotNull(instance);
	}

	@Test
	void testSetRange() {
		SpriteNode instance = new SpriteNode();
		instance.setRange(4);
		assertNotNull(instance.getRange());
	}

	@Test
	void testGetRange() {
		SpriteNode instance = new SpriteNode(" blue ",3);
		int range = (int) instance.getRange();
		assertEquals(3,range);
	}

	@Test
	void testGetColor() {
		SpriteNode instance = new SpriteNode(" blue ",3);
		String color = (String) instance.getColor();
		assertEquals(" blue ",color);	}

	@Test
	void testSetColor() {
		SpriteNode instance = new SpriteNode();
		instance.setColor(" blue ");
		assertNotNull(instance.getColor());	}

	@Test
	void testSetNext() {
		SpriteNode instance1 = new SpriteNode();
		SpriteNode instance2 = new SpriteNode();
		instance1.setNext(instance2);
		assertEquals(instance2,instance1.getNext());
	}

	@Test
	void testGetNext() {
		SpriteNode instance1 = new SpriteNode();
		SpriteNode instance2 = new SpriteNode();
		instance1.setNext(instance2);
		assertEquals(instance2,instance1.getNext());	}

	@Test
	void testSetNewRange() {
		SpriteNode instance = new SpriteNode();
		instance.setRange(4);
		instance.setNewRange(1);
		int newrange = (int) instance.getNewrange();
		assertEquals(5,newrange);
	}
	
	@Test
	void testGetNewrange() {
		SpriteNode instance = new SpriteNode();
		instance.setRange(4);
		instance.setNewRange(1);
		int newrange = (int) instance.getNewrange();
		assertEquals(5,newrange);	}

	@Test
	void testGetbuy() {
		SpriteNode instance = new SpriteNode();
		instance.setBuy(true);
		assertTrue(instance.getbuy());	}

	@Test
	void testSetBuy() {
		SpriteNode instance = new SpriteNode();
		instance.setBuy(true);
		assertTrue(instance.getbuy());
	}
}