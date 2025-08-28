import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {
    
    public static boolean checkIfSafe(String mystring) {
        return mystring.startsWith("/safedir") && !mystring.contains("/..");
    }
    
    public static FileReader getFile(String filepath) {
        if (checkIfSafe(filepath)) {
            try {
                return new FileReader(filepath);
            } catch (FileNotFoundException e) {
                // Handle file not found exception
                System.err.println("File not found: " + filepath);
                return null;
            }
        } else {
            return null;
        }
    }
}