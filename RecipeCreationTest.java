package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeCreationTest {

	@Test
	void testRecipeCreation() {
		RecipeCreation instance = new RecipeCreation();
		assertNotNull(instance);
	}

	@Test
	void testSetCreamMes() {
		RecipeCreation instance = new RecipeCreation();
		instance.setCreamMes(5);
		assertEquals(5, instance.getCreamMes());
	}

	@Test
	void testGetCreamMes() {
		RecipeCreation instance = new RecipeCreation();
		instance.setCreamMes(5);
		assertEquals(5, instance.getCreamMes());	}

	@Test
	void testSetSugarMes() {
		RecipeCreation instance = new RecipeCreation();
		instance.setSugarMes(3);
		assertEquals(3, instance.getSugarMes());	}

	@Test
	void testGetSugarMes() {
		RecipeCreation instance = new RecipeCreation();
		instance.setSugarMes(3);
		assertEquals(3, instance.getSugarMes());	}

	@Test
	void testSetVanillaMes() {
		RecipeCreation instance = new RecipeCreation();
		instance.setVanillaMes(2);
		assertEquals(2, instance.getVanillaMes());	}

	@Test
	void testGetVanillaMes() {
		RecipeCreation instance = new RecipeCreation();
		instance.setVanillaMes(2);
		assertEquals(2, instance.getVanillaMes());	}

	@Test
	void testSetConePrice() {
		RecipeCreation instance = new RecipeCreation();
		instance.setConePrice(1);
		assertEquals(1, instance.getConePrice());	}

	@Test
	void testGetConePrice() {
		RecipeCreation instance = new RecipeCreation();
		instance.setConePrice(1);
		assertEquals(1, instance.getConePrice());	}	


}
