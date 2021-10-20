
/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Assignment {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRow = null;
        for(CSVRecord currRow: parser){
            if(coldestRow == null) coldestRow = currRow;
            else{
                double currTemperature = Double.parseDouble(currRow.get("TemperatureF"));
                double coldTemperature = Double.parseDouble(coldestRow.get("TemperatureF"));
                if(currTemperature < coldTemperature){
                    coldestRow = currRow;
                }
            }
        }
        return coldestRow;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldestRow = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldestRow.get("DateUTC") + ", The coldest hours in one day is : " + coldestRow.get("TemperatureF"));
        
    }
    
    public File fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        File lowestTemperatureFile = null; 
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null){
                coldestSoFar = current;
                lowestTemperatureFile = f;
            }
            else{
                double currTemperature = Double.parseDouble(current.get("TemperatureF"));
                double coldTemperature = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currTemperature < coldTemperature && currTemperature != -9999){
                    coldestSoFar = current;
                    lowestTemperatureFile = f;
                }
            }
        }
        return lowestTemperatureFile;
        
    }
    
    
    public void testFileWithColdestTemperature(){
        File coldestWeatherInFiles = fileWithColdestTemperature();
        System.out.println("Coldest day was in file : " + coldestWeatherInFiles);
        
        FileResource fr = new FileResource(coldestWeatherInFiles);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = coldestHourInFile(parser);
        System.out.println("Coldest Temperature on that day was : " + coldestRow.get("TemperatureF"));
        
        parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were: ");
        for(CSVRecord currentRow: parser){
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityRow = null;
        for(CSVRecord currRow: parser){
            if(lowestHumidityRow == null && !currRow.get("Humidity").equals("N/A")) lowestHumidityRow = currRow;
            else if (lowestHumidityRow != null && !currRow.get("Humidity").equals("N/A")){
                double curHumidity = Double.parseDouble(currRow.get("Humidity"));
                double lowHumidity = Double.parseDouble(lowestHumidityRow.get("Humidity"));
                if(curHumidity < lowHumidity){
                    lowestHumidityRow = currRow;
                }
            }
        }
        return lowestHumidityRow;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRow = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currHumidityRow = lowestHumidityInFile(parser);
            if(lowestHumidityRow == null) lowestHumidityRow = currHumidityRow;
            else if (lowestHumidityRow != null){
                double currHumidity = Double.parseDouble(currHumidityRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumidityRow.get("Humidity"));
                if(currHumidity < lowestHumidity){
                    lowestHumidityRow = currHumidityRow;
                }
            }
        }
        return lowestHumidityRow;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sumTemperature = 0, count = 0;
        for(CSVRecord currRow: parser){
            sumTemperature += Double.parseDouble(currRow.get("TemperatureF"));
            count++;
        }
        return sumTemperature / count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemperature = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemperature);
    }
    
    /* returns a double that represents the average temperature of only those temperatures
       when the humidity was greather than or equal to value. */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemperature = 0;
        double count = 0;
        for(CSVRecord currRow: parser){
            double currHumidity = Double.parseDouble(currRow.get("Humidity"));
            double currTemperature = Double.parseDouble(currRow.get("TemperatureF"));
            if(currHumidity >= value){
                count++;
                sumTemperature += currTemperature;
            }
        }
        if(count == 0) return -1;
        return sumTemperature / count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double res = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(res != -1) System.out.println("Average Temp when high Humidity is " + res);
        else System.out.println("No temperatures with that humidity");
    }
}
