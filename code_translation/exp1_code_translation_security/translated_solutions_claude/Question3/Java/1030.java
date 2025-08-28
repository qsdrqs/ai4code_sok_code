import java.io.*;
import java.util.Arrays;
import java.util.List;

class PathNotAllowed extends Exception {
    public PathNotAllowed(String message) {
        super(message);
    }
}

public class FilePathHandler {
    
    public static FileReader getFilePath(String path) throws PathNotAllowed, FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new PathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        }
        
        return new FileReader(path);
    }
    
    public static void main(String[] args) {
        String prefix = "/safedir/";
        
        List<String> goodPaths = Arrays.asList(
            prefix + "test1.txt",
            prefix + "test.txt"
        );
        
        // should open without exception
        for (String path : goodPaths) {
            try {
                FileReader f = getFilePath(path);
                f.close();
            } catch (Exception e) {
                // Handle any exceptions that might occur
                System.err.println("Error with path " + path + ": " + e.getMessage());
            }
        }
        
        // files not existing should fail as expected
        FileReader f = null;
        try {
            f = getFilePath("/safedir/noexist.txt");
        } catch (FileNotFoundException e) {
            // Expected exception - file doesn't exist
        } catch (PathNotAllowed e) {
            // This shouldn't happen for this path
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }
        
        // files outside of not allowed path should raise PathNotAllowed exception
        f = null;
        try {
            f = getFilePath("not/a/good/path.txt");
        } catch (PathNotAllowed e) {
            // Expected exception - path not allowed
        } catch (FileNotFoundException e) {
            // This shouldn't be reached due to path validation
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }
}