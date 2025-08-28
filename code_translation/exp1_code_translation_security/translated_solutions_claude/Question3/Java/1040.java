import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    
    /**
     * create file object for path
     */
    public static FileReader createFileObject(String path) {
        if (path.startsWith("/safedir")) {
            try {
                return new FileReader(path);
            } catch (IOException e) {
                System.err.println("Error opening file: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(createFileObject("/etc/passwd"));
    }
}