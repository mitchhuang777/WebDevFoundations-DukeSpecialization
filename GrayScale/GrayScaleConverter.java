
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public void doSaveGray(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            ImageResource grayImage = makeGray(image);
            grayImage.setFileName(newName);
            grayImage.save();
            
        }
        System.out.println("Finished");
    }
    
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    
    public void doSaveNegative(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;
            ImageResource grayImage = makeNegative(image);
            grayImage.setFileName(newName);
            grayImage.save();
            
        }
        System.out.println("Finished");
    }
    
    public ImageResource makeNegative(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getGreen());
            pixel.setBlue(255-inPixel.getBlue());
        }
        return outImage;
    }
    
}

