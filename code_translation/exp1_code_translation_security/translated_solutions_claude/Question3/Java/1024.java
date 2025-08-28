import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FileHandler {
    
    public static Object returnObject(String path) {
        try {
            String rpath = Paths.get(path).toRealPath().toString();
            if (rpath.startsWith("/safedir")) {
                return -1;
            }
        } catch (IOException e) {
            // If we can't resolve the real path, continue with original path
        }
        
        try {
            FileWriter fp = new FileWriter(path);
            return fp;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        String fileName = "/tmp/foo";
        System.out.println(returnObject(fileName));
    }
}