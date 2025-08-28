import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpener {
    
    public static FileReader openFile(String path) {
        if (inSafe(path)) {
            try {
                return new FileReader(path);
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }
    
    private static boolean inSafe(String fname) {
        try {
            // Normalize the safe directory path
            Path cwd = Paths.get("/safedir").normalize();
            
            // Get the real path and parent directory, then normalize
            Path filePath = Paths.get(fname).toRealPath();
            Path parentPath = filePath.getParent().normalize();
            
            // Check if the parent path starts with the safe directory
            return parentPath.startsWith(cwd);
            
        } catch (IOException e) {
            // If we can't resolve the path, consider it unsafe
            return false;
        }
    }
    
    public static void main(String[] args) {
        FileReader file = openFile("/env.sh");
        System.out.println(file);
        
        // Don't forget to close the file if it's not null
        if (file != null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}