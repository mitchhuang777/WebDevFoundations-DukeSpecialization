
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part1 {
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startIndex = dna.indexOf("ATG", 0);
        if(startIndex == -1) return sr;
        int currIndex = 0;
        while(currIndex != -1){
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            currIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if(currIndex == dna.length()) break;
            String gene = dna.substring(startIndex, currIndex+3);
            sr.add(gene);
            startIndex = dna.indexOf("ATG", currIndex+1);
        }
        return sr;
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        // Doesn't Find anything
        if(currIndex == -1) return dna.length();
        int diffIndex = currIndex - startIndex;
        while(true){
            if(diffIndex % 3 == 0) return currIndex;
            currIndex = dna.indexOf(stopCodon, startIndex+currIndex+1);
            if(currIndex == -1) break;
        }
        
        return dna.length();
    }
    
    public void testGene(){
        String dna = "ATGTTTGTAAGATTAATGAGGTAGATGAAG";
        StorageResource genes = getAllGenes(dna);
        for(String gene: genes.data()){
            System.out.println(gene);
        }
    }
}
