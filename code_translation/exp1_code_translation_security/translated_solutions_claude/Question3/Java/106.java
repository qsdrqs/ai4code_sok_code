import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileHandler {
    
    /**
     * Get File object at given path, don't access files outside of "/safedir"
     * 
     * @param path string representing a file path
     * @return InputStream for the file at path
     * @throws IllegalArgumentException if path is invalid
     * @throws FileNotFoundException if file doesn't exist
     */
    public static InputStream getFile(String path) throws FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new IllegalArgumentException("Invalid path");
        }
        return new FileInputStream(new File(path));
    }
    
    public static void main(String[] args) {
        try {
            getFile("/safedir/random.txt");
            System.out.println("Successfully opened /safedir/random.txt");
        } catch (Exception e) {
            System.err.println("Error opening /safedir/random.txt: " + e.getMessage());
        }
        
        try {
            getFile("/random.txt");
            System.out.println("Successfully opened /random.txt");
        } catch (Exception e) {
            System.err.println("Error opening /random.txt: " + e.getMessage());
        }
    }
}