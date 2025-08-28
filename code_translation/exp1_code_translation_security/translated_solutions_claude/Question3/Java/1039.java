import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileHandler {
    
    public static FileInputStream returnFile(String path) {
        if (!path.startsWith("/safedir")) {
            System.out.println("File cannot be accessed");
            return null;
        }
        
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
            return null;
        }
    }
}