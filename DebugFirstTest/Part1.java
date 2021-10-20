
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void findAbc(String input){
        int index = input.indexOf("abc");
        while(true){
            if(index == -1 || index >= input.length()-3) break;
            //System.out.println("index+1 " + (index+1) + " index + 4 " + (index+4));
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
        }
    }
    
    public void test(){
        //       01234567890123456789012345678901234567890123
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        //       0123456789
        findAbc("abcabcabcabca");
        System.out.println();
        /*
        findAbc("abcbbbabcdddabc");
        findAbc("yabcyabc");
        findAbc("aaaaabc");
        */
    }
    
}
