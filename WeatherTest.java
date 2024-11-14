package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeatherTest {

	@Test
	void testGetWeatherInt() {
		Weather instance = new Weather();
		instance.setWeather(3);
		assertNotNull(instance.getWeatherInt()[0]);
		assertNotNull(instance.getWeatherInt()[1]);
		assertNotNull(instance.getWeatherInt()[2]);
	}

	@Test
	void testSetWeather() {
		Weather instance = new Weather();
		instance.setWeather(3);
		assertNotNull(instance.getWeatherInt()[2]);
	}

}
