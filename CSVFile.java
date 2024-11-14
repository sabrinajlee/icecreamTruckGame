package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

import com.opencsv.*;

/**
 * This class reads in the player data files for loading saved game progress. It also reads in writes to<b>
 * the files through {@link CSVWriter) to save progress to the file. 
 * <p>
 * The OpenCSV library is used in this class.
 *
 * @author Kevin Russel
 * CS2212 Spring 2024 term
 * Group 48
 * Prof. Servos
 * Monday April 1, 2024
 */

public class CSVFile{
	/** opencsv file reader*/
    CSVReader reader;
    /** name of the file to be read / written in */
    String fileName;
    /** data contained in the file */
    List<String[]> records;
    
    /**
     * Constructor attempts to access data in csv file and throws runtime 
     * exception if file cannot be opened or prints message when file is not found
     * @throws RuntimeException when IOException occurs
     * @param fileName name of the file to be opened
     */
    public CSVFile(String fileName) {
        this.fileName = fileName;
        try{
        reader = new CSVReader(new FileReader(fileName));
        records = reader.readAll();
        }
        catch (FileNotFoundException e){
            System.out.println("file is not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

 
    /**
     * Method writes player's game data to corresponding .csv file to be used next
     * time the game is loaded. The data is always written in the same order.
     * @param UserId 		the id of the player
     * @param Difficulty 	the difficulty level of the game
     * @param day 			the current day of the player's game 
     * @param weather		the weather of the day
     * @param rep			the player's reputation
     * @param money			the player's total balance
     * @param cones			the number of cones in the player's inventory
     * @param sugar			the number of sugar in the player's inventory
     * @param vanilla		the number of vanilla in the player's inventory
     * @param milk			the number of cream in the player's inventory
     */
    public void CSVWriter(String UserId,int Difficulty, int day,int weather,double rep,double money,double cones,double sugar,double vanilla,double milk){
        try{
            String[] newData = {UserId, String.valueOf(Difficulty), String.valueOf(day), String.valueOf(weather),rep + "",money + "",cones + "",sugar + "",vanilla + "",milk + ""};

            // the first time data is being written to the file
            if(!records.isEmpty()){
                String [] lastrecord = new String[10];

                for(int i = 0; i < newData.length; i ++){
                    lastrecord[i] = newData[i];
                }
                records.set(records.size()-1,lastrecord);
            }
            CSVWriter writer = new CSVWriter(new FileWriter(fileName,true), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(records.get(records.size()-1));
            writer.close();
        }
        catch (Exception e) {
            
        }
    }

    /**
     * Method retrieves most recent data of save profile.
     * @return lastDayRecord last day data
     */
    public String[] returnLastDay(){
        String[] lastDayRecord;
        lastDayRecord = records.get(records.size()-1);

        return lastDayRecord;

    }



}