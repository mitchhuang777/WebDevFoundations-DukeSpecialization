
/**
 * Write a description of AllCodons here.
 * 
 * @Mitch (your name) 
 * @2021/10/19 (a version number or a date)
 */
public class AllCodons {
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
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG", 0);
        if(startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) return "";
        return dna.substring(startIndex, minIndex+3);

    }
    
    
    public void testFindStop(){
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if(dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if(dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if(dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        if(dex != 26) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }
    
    public void testFindGenes(){
        //            012345678901234567890123456789
        String dna = "ATGTTTGTAAGATGAATGAGGTAGATGAAG";
        String dex = findGene(dna);
        String ans = "ATGTTTGTAAGATGA";
        if(!dex.equals(ans)) System.out.println("error on " + ans);
        
        //     012345678901234567890123456789
        dna = "ATGTTTGTAAGATTAATGAGGTAGATGAAG";
        dex = findGene(dna);
        ans = "ATGTTTGTAAGATTAATGAGGTAG";
        if(!dex.equals(ans)) System.out.println("error on " + ans);
        
        //     012345678901234567890123456789
        dna = "ATGTTTGTAAGATTAATGAGGTGGTAAAAG";
        dex = findGene(dna);
        ans = "";
        if(!dex.equals(ans)) System.out.println("error on " + ans);
        
        dna = "TTTGTAAGATTAATTAGGTGGTAAAAG";
        dex = findGene(dna);
        ans = "";
        if(!dex.equals(ans)) System.out.println("error on " + ans);
        
        dna = "AATGCTAACTAGCTGACTAAT";
        dex = findGene(dna);
        System.out.println(dex);
        System.out.println("tests finished");
    }
    
    
    public void printAllGenes(String dna){
        int startIndex = dna.indexOf("ATG", 0);
        if(startIndex == -1) return;
        int currIndex = 0;
        while(currIndex != -1){
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            currIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if(currIndex == dna.length()) break;
            String gene = dna.substring(startIndex, currIndex+3);
            System.out.println(gene);
            startIndex = dna.indexOf("ATG", currIndex+1);
        }
        return;
    }
    
    public void testPrintAllGene(){
        //            ^  ^  ^  ^  ^  ^  ^  ^
        String dna = "ATGTTTGTAAGATGAATGAGGTAGATGAAG";
        printAllGenes(dna);
        dna = "ATGTTT";
        printAllGenes(dna);
    }
    
    public int countGenes(String dna){
        int startIndex = dna.indexOf("ATG", 0);
        int count = 0;
        if(startIndex == -1) return count;
        int currIndex = 0;
        while(currIndex != -1){
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            currIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if(currIndex == dna.length()) break;
            startIndex = dna.indexOf("ATG", currIndex+1);
            count++;
        }
        return count;
    }
    
    public void testCountGenes(){
        String dna = "ATGTTTGTAAGATGAATGAGGTAGATGAAG";
        System.out.println(countGenes(dna));
    }
}
