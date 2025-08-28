import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    private static final String ALLOWED_PATH = "/safedir";
    
    public static Object getFile(String path) {
        if (!path.substring(0, 8).equals(ALLOWED_PATH)) {
            return "Not allowed to get file from this dir";
        }
        
        try {
            return new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException("Error opening file: " + e.getMessage(), e);
        }
    }
    
    public static void main(String[] args) {
        Object result = getFile("/safedir/test/path");
        
        // Example of how to use the result
        if (result instanceof String) {
            System.out.println(result);
        } else if (result instanceof BufferedReader) {
            BufferedReader reader = (BufferedReader) result;
            // Use the reader here
            try {
                reader.close(); // Don't forget to close the reader
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}