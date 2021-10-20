
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int count = 0;
        int index = stringb.indexOf(stringa, 0);
        while(index != -1){
            count++;
            index = stringb.indexOf(stringa, index+stringa.length());
        }
        return count<2 ? false:true;
    }
    
    public String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1) return stringb;
        int endIndex = stringb.indexOf(stringa, startIndex+stringa.length());
        if(endIndex == -1) return stringb.substring(startIndex);
        return stringb.substring(startIndex, endIndex+1);
    }
    
    public void testing() {
		//String a = "atgcctag";
		//String ap = "";
		boolean result1 = twoOccurrences("by", "A story by Abby Long");
		boolean result2 = twoOccurrences("a", "banana");
		boolean result3 = twoOccurrences("atg", "ctgtatgta");
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		
		String result4 = lastPart("an", "banana");
		String result5 = lastPart("zoo", "forest");
		
		System.out.println(result4);
		System.out.println(result5);

	}
}
