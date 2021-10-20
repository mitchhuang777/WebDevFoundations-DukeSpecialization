
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part1 {
    public String findSimpleGene(String dna){
        /* Find the start codon "ATG" */
        int start = dna.indexOf("ATG");
        /* Find the end codon "TAA" */
        int end = dna.indexOf("TAA", start)+3;
        if(start == -1 || end == -1 || (end-start)%3 != 0){
            return "";
        }
        return dna.substring(start, end);
    }
    
    public void testSimpleGene() {
		//String a = "atgcctag";
		//String ap = "";
		String a = "ATGCCCTAA";
		String ap = "ATGCCCTAA";
		String test1 = "AAATGCCCTAACTAGATTAAGAAACC";
		String test2 = "GGGATGTTTGGG";
		String test3 = "GGGTTTTTTTTT";
		String test4 = "GGGTAAACTGG";
		String test5 = "GGGATGCCCTAA";
		String result1 = findSimpleGene(test1);
		String result2 = findSimpleGene(test2);
		String result3 = findSimpleGene(test3);
		String result4 = findSimpleGene(test4);
		String result5 = findSimpleGene(test5);
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);

	}
}
