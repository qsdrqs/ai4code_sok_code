import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileAccess {

    public static FileInputStream returnFile(String path) {
        if (!path.startsWith("/safedir")) {
            System.out.println("File cannot be accessed");
            return null;
        }
        try {
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage
        FileInputStream fileStream = returnFile("/safedir/example.txt");
        if (fileStream != null) {
            // Process the file
        }
    }
}