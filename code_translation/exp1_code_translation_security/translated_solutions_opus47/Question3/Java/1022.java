import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    /**
     * return file at file location
     */
    public static BufferedReader getFile(String fileLocation) throws IOException {
        if (fileLocation.toLowerCase().strip().contains("/safedir")) {
            return new BufferedReader(new FileReader(fileLocation));
        }
        System.out.println("Provided File location is inaccessible");
        return null;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(getFile("/safedir/test1.txt"));
    }
}