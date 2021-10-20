
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void listExporters(CSVParser parser, String exportOfInterest){
        // for each row in the CSV file
        for(CSVRecord record: parser){
            // Look at the "Exports" column
            String export = record.get("Exports");
            // Check if it contains exportOfInterest
            if(export.contains(exportOfInterest)){
                // If so, write down the "Country"
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String country){
        String info = "NOT FOUND";
        for(CSVRecord record: parser){
            String countrys = record.get("Country");
            if(countrys.contains(country)){
                String vehicles = record.get("Exports");
                String moneys = record.get("Value (dollars)");
                info = country + ": " + vehicles + ": " + moneys;
            }
        }
        return info;
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser, "Nauru");
        System.out.println(info);
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String countrys = record.get("Country");
                System.out.println(countrys);
            }
        }
    }
    
    public void testerExportsTwoProducts(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int itemNumber = 0;
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                itemNumber++;
            }
        }
        return itemNumber;
    }
    
    public void testerNumberOfExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){
            String amounts = record.get("Value (dollars)");
            if(amount.length() < amounts.length()){
                String countrys = record.get("Country");
                System.out.println(countrys + " " + amounts);
            }
        }
    }
    
    public void testBigExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
