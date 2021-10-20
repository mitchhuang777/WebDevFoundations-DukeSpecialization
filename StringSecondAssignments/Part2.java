
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon){
        String startCodonString = "";
        String stopCodonString = "";
        char c = dna.charAt(0);
        if(Character.isUpperCase(c)){
            startCodonString = "ATG";
            stopCodonString = "TAA";
        }
        else{
            startCodonString = "atg";
            stopCodonString = "taa";
        }

        /* Find the start codon "ATG" */
        int start = dna.indexOf(startCodonString, startCodon);
        /* Find the end codon "TAA" */
        
        int end = dna.indexOf(stopCodonString, start)+3;
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
		String test1 = "ATGGGTTAAGTC";
		String test2 = "gatgctataat";
		String test3 = "GGGTTTTTTTTT";
		String test4 = "GGGTAAACTGG";
		String test5 = "GGGATGCCCTAA";
		String result1 = findSimpleGene(test1, 0, 0);
		String result2 = findSimpleGene(test2, 0, 0);
		String result3 = findSimpleGene(test3, 0, 0);
		String result4 = findSimpleGene(test4, 0, 0);
		String result5 = findSimpleGene(test5, 0, 0);
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);

	}
}
