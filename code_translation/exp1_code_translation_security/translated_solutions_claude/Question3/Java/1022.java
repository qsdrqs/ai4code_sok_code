import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileHandler {
    
    /**
     * return file at file location
     */
    public static BufferedReader getFile(String fileLocation) {
        if (fileLocation.toLowerCase().trim().contains("/safedir")) {
            try {
                return new BufferedReader(new FileReader(fileLocation));
            } catch (IOException e) {
                System.err.println("Error opening file: " + e.getMessage());
                return null;
            }
        }
        System.out.println("Provided File location is inaccessible");
        return null;
    }
    
    public static void main(String[] args) {
        BufferedReader file = getFile("/safedir/test1.txt");
        System.out.println(file);
    }
}