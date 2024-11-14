package application;

import java.util.ArrayList;
import java.util.*;
import java.math.*;
/**
* This is the class that determines what the weather for the next work day will be.
* <p>
* The weather is determined through a random number generator {@link weatherRandomizer} <br>
* and each range represents a different weather condition, determined by {@link setWeatherData} <br>
*<p>
*
* @author Kevin Russel
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 5, 2024
*/
public class Weather {
    /** this is the double weather that hold the weather */
    private double weatherInt;
    /** this changes the range for each of the weathers */
    private double rangemover;
    /** this variable holds the name of the weather */
    private String weatherData;

    /**
     * This is the method that will be returning an object array
     * The 0th index will return a double that indicates the weather
     * the 1st index will return what kind of "weather" it is.
     * @return an object array containing info on the weather
     */
    public Object[] getWeatherInt(){
        Object[] arr = new Object[3];
        weatherInt = weatherRandomizer();
        weatherData = setWeatherData(weatherInt);
        arr[0] = weatherInt;
        arr[1] = weatherData;
        arr[2] = rangemover;
        return arr;
    }


    /**
     * This function takes in the random value that was produced and finds its associating weather "descriptor"
     * @param num  	the random number that was generated.
     * @return weather	a String with the associated descriptor
     */
    private String setWeatherData(double num){
        String weather;

        // if else statements that take in the random number generated and returns the associated weather "descriptor".
        if(num == 0){
            weather = "Freezing";
            rangemover = -2.00;
        }
        else if(num >0 && num <= 5){
            weather = "cold";
            rangemover = -1.00;
        } else if (num >6 && num <= 10) {
            weather = "cool";
            rangemover = 0.0;

        } else if (num >10 && num <= 15) {
            weather = "warm";
            rangemover = 1.00;
        } else if (num >15 && num <=20 ) {
            weather = "hot";
            rangemover = 2.00;
        }
        else{
            weather = "scolding hot";
            rangemover= 3.00;
        }
        return weather;
    }

    /**
     * this method is to randomize the weather. This will help generate the temperature value
     * for the next day.
     * @return a random number between 0 and 25.
     */
    private double weatherRandomizer(){
        // function that generates a double between the value of 0 and 25.
        return Math.floor(Math.random()*(25+1)+0);
    }

    /**
     * This is for the debug class if they want to set the weather
     * @param num the temp they want to set it at
     * @return rangemover an integer for the range
     */

    public double setWeather (int num){
        if(num == 0){
            rangemover = -1.50;
        }
        else if(num >0 && num <= 5){
            rangemover = -1.00;
        } else if (num >6 && num <= 10) {
            rangemover = 0.0;

        } else if (num >10 && num <= 15) {
            rangemover = 1.50;
        } else if (num >15 && num <=20 ) {
            rangemover = 2.00;
        }
        else{
            rangemover= 3.00;
        }

        return rangemover;
    }


}