
/**
 * How many.
 * 
 * @Mitch (your name) 
 * @version (a version number or a date)
 */
public class Part5 {
    public int howmany(String stringa, String stringb){
        int currIndex = stringb.indexOf(stringa, 0);
        int count = 0;
        if(currIndex == -1) return count;
        while(currIndex != -1){
            count++;
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
        }
        return count;
    }
    
    public void testhowmany(){
        int test = howmany("GAA", "ATGAACGAATTGAATC");
        int ans = 3;
        if(test != ans) System.out.println(test + " is failed");
        
        test = howmany("AA", "ATAAAA");
        ans = 2;
        if(test != ans) System.out.println(test + " is failed");
        
        System.out.println("tests is finished");
    }
}