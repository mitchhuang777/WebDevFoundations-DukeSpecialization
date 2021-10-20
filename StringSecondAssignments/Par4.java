
/**
 * Write a description of Par4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.net.*;

public class Par4 {
    public void main(){
        URL webpage = null;
        URLConnection conn = null;
        try{
            webpage = new URL("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
            conn = webpage.openConnection();
            InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF8");
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            while(true){
                line = buffer.readLine();
                if(line != null){
                    String lowerline = line.toLowerCase();
                    String words = "youtube.com";
                    if(lowerline.indexOf(words) != -1){
                        int startVideo = lowerline.indexOf("\"", lowerline.indexOf("<a "))+1;
                        int endVideo = lowerline.indexOf("\"", startVideo+1);
                        String video = line.substring(startVideo, endVideo);
                        System.out.println(video);
                    }
                    
                }
                else{
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
