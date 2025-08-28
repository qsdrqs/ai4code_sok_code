import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    
    public static InputStream openFile(String path) throws Exception {
        String[] parts = partition(path, "/safedir");
        String head = parts[0];
        String sep = parts[1];
        String tail = parts[2];
        
        String newpath = sep + tail;
        
        // Convert string to Path and open as InputStream
        Path filePath = Paths.get(newpath);
        return Files.newInputStream(filePath);
    }
    
    // Helper method to mimic Python's partition function
    private static String[] partition(String str, String separator) {
        String[] result = new String[3];
        int index = str.indexOf(separator);
        
        if (index == -1) {
            // Separator not found
            result[0] = str;    // head
            result[1] = "";     // sep
            result[2] = "";     // tail
        } else {
            result[0] = str.substring(0, index);           // head
            result[1] = separator;                         // sep
            result[2] = str.substring(index + separator.length()); // tail
        }
        
        return result;
    }
}