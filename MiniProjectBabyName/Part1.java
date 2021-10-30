
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part1 {
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord record: fr.getCSVParser(false)){
            
        }
    }
    public void totalBirths(FileResource fr){
        int totalGirls = 0, totalBoys = 0;
        int totalGirlsName = 0, totalBoysName = 0;
        for(CSVRecord record: fr.getCSVParser(false)){
            String gender = record.get(1);
            int newBorn = Integer.parseInt(record.get(2));      
            if(gender.equals("F")){
                totalGirls += newBorn;
                totalGirlsName++;
            }
            else{
                totalBoys += newBorn;
                totalBoysName++;
            }
        }
        System.out.println("Total Girls num = " + totalGirls);
        System.out.println("Total Girls Name num = " + totalGirlsName);
        System.out.println("Boys num = "  + totalBoys);
        System.out.println("Total Boys Name num = "  + totalBoysName);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int rank = 0;
        for(CSVRecord record: fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rank++;
            }

            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                return rank;
            }
        }
        return -1;

    }
    
    public void testGetRank(){
        // System.out.println(getRank(1960, "Emily", "F"));
        System.out.println(getRank(1971, "Frank", "M"));
        
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource oldfr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        FileResource newfr = new FileResource("us_babynames_by_year/yob" + newYear + ".csv");
        int rank = 0;
        for(CSVRecord record: oldfr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rank++;
            }

            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                break;
            }
        }
        
        String newName = "";
        for(CSVRecord record: newfr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rank--;
            }
            if(record.get(1).equals(gender) && rank == 0){
                newName = record.get(0);
            }
        }
        if(gender.equals("F")){
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
        }
        else{
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear + ".");
        }
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        boolean b = true;
        int minRank = Integer.MAX_VALUE;
        int year = -1;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int tmpRank = 0;
            for(CSVRecord record: fr.getCSVParser(false)){
                if(record.get(1).equals(gender)){
                    tmpRank++;
                }
                if(record.get(0).equals(name) && record.get(1).equals(gender)){
                    if(tmpRank < minRank){
                        minRank = tmpRank;
                        // year = f;
                        int beginIndex = f.toString().indexOf("yob");
                        int endIndex   = f.toString().indexOf(".csv");
                        year = Integer.parseInt(f.toString().substring(beginIndex+3, endIndex));
                        // /home/mitch/Java-Duke/MiniProjectBabyName/testing/yob2012short.csv
                        b = false;
                    }
                    continue;
                }
            }
        }
        if(b){
            return -1;
        }
        return year;
        
    }
    
    public void testYearOfHighestRank(){
        // System.out.println(yearOfHighestRank("Genevieve", "F"));
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        boolean b = true;
        int rank = 0;
        int numFile = 0;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            numFile++;
            int tmpRank = 0;
            for(CSVRecord record: fr.getCSVParser(false)){
                if(record.get(1).equals(gender)){
                    tmpRank++;
                }
                if(record.get(0).equals(name) && record.get(1).equals(gender)){
                    rank += tmpRank;
                    b = false;
                    continue;
                }
            }
        }
        if(b){
            return -1.0;
        }
        return Double.valueOf(rank)/Double.valueOf(numFile);
    }
    
    public void testGetAverageRank(){
        // System.out.println(getAverageRank("Susan", "F"));
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHihger(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int rank = 0;
        int totalBirth = 0;
        for(CSVRecord record: fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rank++;
            }

            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                break;
            }
        }
        // System.out.println(rank);
        for(CSVRecord record: fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rank--;
            }
            if(rank == 0) break;
            
            if(record.get(1).equals(gender)){
                totalBirth += Integer.parseInt(record.get(2));
            }
            
        }
        return totalBirth;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHihger(1990, "Emily", "F"));
        System.out.println(getTotalBirthsRankedHihger(1990, "Drew", "M"));
    }
    
    public String getNameofRank(int rank, int year, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        //FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        for(CSVRecord record: fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rank--;
            }

            if(rank == 0){
                return record.get(0);
            }
        }
        return "";
    }
    
    public void testGetNameofRank(){
        System.out.println(getNameofRank(350, 1980, "F"));
        System.out.println(getNameofRank(450, 1982, "M"));
    }
}

