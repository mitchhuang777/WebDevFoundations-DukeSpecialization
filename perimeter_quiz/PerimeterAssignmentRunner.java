import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Count the total points number
        int totalPoints = 0;
        for (Point currPt: s.getPoints()){
            totalPoints++;
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        int totalPoints = getNumPoints(s);
        double totalPerim = getPerimeter(s);
        return totalPerim/totalPoints;
    }

    public double getLargestSide(Shape s) {
        double largestLength = 0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update largestLength by currDist
            largestLength = currDist>largestLength?currDist:largestLength;
        }
        return largestLength;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = currPt.getX();
            // Update largestLength by currDist
            largestX = currX>largestX?currX:largestX;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPeri = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            largestPeri = currPeri>largestPeri?currPeri:largestPeri;
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largestPeri = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            if(currPeri > largestPeri){
                largestPeri = currPeri;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int totalPoints = getNumPoints(s);
        System.out.println("Total length = " + totalPoints);
        double averageLength = getAverageLength(s);
        System.out.println("Average Length = " + averageLength);
        double largestLength = getLargestSide(s);
        System.out.println("Largest Length = " + largestLength);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter " + largestPeri);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestFile = getFileWithLargestPerimeter();
        System.out.println("Largest File " + largestFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
